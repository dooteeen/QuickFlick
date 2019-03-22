package com.rkbk60.quickflick

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.PreferenceFragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.PreferenceFragmentCompat
import android.widget.Toast

/**
 * Settings
 * TODO: replace to PreferenceFragmentCompat and getSupportFragmentManager
 */

private typealias KHeightPrefEnum = ResourceServerBase.PreferenceEnum<ResourceServer.KeyboardHeight>
private typealias FHeightPrefEnum = ResourceServerBase.PreferenceEnum<ResourceServer.FooterHeight>

class SettingsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, SettingsFragment())
                .commit()
    }

    class SettingsFragment:
            PreferenceFragment(),
            SharedPreferences.OnSharedPreferenceChangeListener {
        private val rServer by lazy { ResourceServer(activity.applicationContext) }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
//            PreferenceManager.getDefaultSharedPreferences(context)?.edit()?.clear()?.commit() // for debug
            addPreferencesFromResource(R.xml.preferences)
        }

        override fun onResume() {
            super.onResume()
            preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
            rServer.run { setOf(thresholdX1, thresholdX2, thresholdY1, thresholdY2) }.map {
                updateThresholdSummary(it)
            }
            updateThemeSummary()
            updateKeyboardHeightSummary(rServer.keyboardHeightPortrait)
            updateKeyboardHeightSummary(rServer.keyboardHeightLandscape)
            updateFooterHeightSummary(rServer.footerHeightPortrait)
            updateFooterHeightSummary(rServer.footerHeightLandscape)
        }

        override fun onPause() {
            super.onPause()
            preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
        }

        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
            with (rServer) {
                when (key) {
                    thresholdX1.key -> {
                        validateThreshold(thresholdX1)
                        updateThresholdSummary(thresholdX1)
                    }
                    thresholdX2.key -> {
                        validateThreshold(thresholdX2)
                        updateThresholdSummary(thresholdX2)
                    }
                    thresholdY1.key -> {
                        validateThreshold(thresholdY1)
                        updateThresholdSummary(thresholdY1)
                    }
                    thresholdY2.key -> {
                        validateThreshold(thresholdY2)
                        updateThresholdSummary(thresholdY2)
                    }
                    keyboardHeightPortrait.key -> {
                        updateKeyboardHeightSummary(keyboardHeightPortrait)
                    }
                    keyboardHeightLandscape.key -> {
                        updateKeyboardHeightSummary(keyboardHeightLandscape)
                    }
                    footerHeightPortrait.key -> {
                        updateFooterHeightSummary(footerHeightPortrait)
                    }
                    footerHeightLandscape.key -> {
                        updateFooterHeightSummary(footerHeightLandscape)
                    }
                    indicatorTheme.key -> {
                        updateThemeSummary()
                    }
                }
            }
        }

        private fun validateThreshold(target: ResourceServerBase.PreferenceIntText) {
            try {
                val newValue = target.getCurrentAsString().toInt()
                val minimal  = 10
                if (newValue < minimal) {
                    toast("Minimal value is $minimal thou.")
                    target.also { it.current = minimal }
                    (findPreference(target.key) as? EditTextPreference)?.text = minimal.toString()
                }
            } catch (_: java.lang.Exception) {
                toast("Set default value.")
                target.also { it.current = it.default }
                (findPreference(target.key) as? EditTextPreference)?.text = target.default.toString()
            }
        }

        private fun updateThresholdSummary(target: ResourceServerBase.PreferenceIntText) {
            findPreference(target.key).summary =
                    "${target.current} thou (Default:${target.default})"
        }

        private fun updateKeyboardHeightSummary(target: KHeightPrefEnum = rServer.keyboardHeightPortrait) {
            val current = target.current
            val value = target.currentValue.toLowerCase()
            findPreference(target.key).summary = "Level ${current.toInt()} ($value)"
        }

        private fun updateFooterHeightSummary(target: FHeightPrefEnum = rServer.footerHeightPortrait) {
            val current = target.current
            val value = target.currentValue
            findPreference(target.key).summary = "Level ${current.toInt()} ($value)"
        }

        private fun updateThemeSummary() {
            findPreference(rServer.indicatorTheme.key).summary =
                    rServer.indicatorTheme.current.toString()
        }

        private fun toast(s: String) {
            Toast.makeText(activity.applicationContext, s, Toast.LENGTH_LONG).show()
        }

    }

}

