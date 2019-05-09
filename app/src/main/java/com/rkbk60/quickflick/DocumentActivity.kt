package com.rkbk60.quickflick

import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class DocumentActivity : AppCompatActivity() {

    private var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document)

        val (title, file, zoomable) = when (intent.dataString) {
            baseContext.getString(R.string.intent_wiki) ->
                Triple("QuickFlick Document", "src/main/assets/Home.html", true)
            baseContext.getString(R.string.intent_license) ->
                Triple("License", "src/main/assets/licenses.html", false)
            else ->
                Triple("Error", "404", false)
        }

        webView = findViewById<WebView>(R.id.document_view)?.apply {
            setTitle(title)
            settings.builtInZoomControls = zoomable
            webViewClient = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                object : WebViewClient() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                        val url = request?.url?.toString() ?: return true
                        if (url.startsWith("http")) {
                            openDefaultBrowser(url)
                        } else {
                            view?.loadUrl(url)
                        }
                        return true
                    }
                }
            } else {
                object : WebViewClient() {
                    @Suppress("OverridingDeprecatedMember")
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        if (url?.startsWith("http") ?: return true) {
                            openDefaultBrowser(url)
                        } else {
                            view?.loadUrl(url)
                        }
                        return true
                    }
                }
            }
            loadUrl("file:///android_asset/$file")
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        webView?.let {
            if (keyCode == KeyEvent.KEYCODE_BACK && it.canGoBack()) {
                it.goBack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun openDefaultBrowser(url: String) {
        val browser = Intent(Intent.ACTION_VIEW, Uri.parse("https://"))
        val defaultResInfo = packageManager.resolveActivity(browser, PackageManager.MATCH_DEFAULT_ONLY)
        if (defaultResInfo is ResolveInfo) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.setPackage(defaultResInfo.activityInfo.packageName)
            try {
                startActivity(intent)
            } catch (_: Exception) {
                return
            }
        }
    }
}
