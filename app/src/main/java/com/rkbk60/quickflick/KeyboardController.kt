package com.rkbk60.quickflick

import android.content.Context
import android.inputmethodservice.Keyboard
import android.support.v4.content.ContextCompat
import com.rkbk60.quickflick.domain.ArrowKey
import com.rkbk60.quickflick.model.KeyIndex
import com.rkbk60.quickflick.model.ModKeyInfo

private typealias Key = Keyboard.Key

class KeyboardController(private val context: Context) {
    private var keyboard: Keyboard? = null

    private var arrowKey:   Key? = null
    private var metaAltKey: Key? = null
    private var ctrlAltKey: Key? = null

    var isRight = true
        private set
    var isPortrait = true
        private set
    var heightLevel = 3
        private set

    /**
     * Constructs keyboard instance.
     * @param isRight whether or not keyboard layout is for right hand one.
     * @param useFooter whether or not device orientation is portrait.
     * @param heightLevel height level (1 ~ 5). If value is out of this range, it will be 2.
     * @return Keyboard instance you wanted
     * TODO: change from Boolean "useFooter" to Int "footerLevel"
     */
    fun inflateKeyboard(isRight: Boolean    = this.isRight,
                        useFooter: Boolean  = this.isPortrait,
                        heightLevel: Int    = this.heightLevel): Keyboard {
        this.isRight = isRight
        this.isPortrait = useFooter
        this.heightLevel = heightLevel
        val intIsRight = if (isRight) 1 else 0
        val intUseFooter = if (useFooter) 1 else 0
        val fixedHeightLevel = if (heightLevel in 1..5) heightLevel else 3
        val keyboard = Keyboard(context, when(100 * intIsRight + 10 * intUseFooter + fixedHeightLevel) {
            115 -> R.xml.keyboard_rh5
            114 -> R.xml.keyboard_rh4
            113 -> R.xml.keyboard_rh3
            112 -> R.xml.keyboard_rh2
            111 -> R.xml.keyboard_rh1
            105 -> R.xml.keyboard_rl5
            104 -> R.xml.keyboard_rl4
            103 -> R.xml.keyboard_rl3
            102 -> R.xml.keyboard_rl2
            101 -> R.xml.keyboard_rl1
             15 -> R.xml.keyboard_lh5
             14 -> R.xml.keyboard_lh4
             13 -> R.xml.keyboard_lh3
             12 -> R.xml.keyboard_lh2
             11 -> R.xml.keyboard_lh1
              5 -> R.xml.keyboard_ll5
              4 -> R.xml.keyboard_ll4
              3 -> R.xml.keyboard_ll3
              2 -> R.xml.keyboard_ll2
              1 -> R.xml.keyboard_ll1
            else -> R.xml.keyboard_rl2 // default TODO: change to "keyboard_a2_right"
        })
        this.keyboard = keyboard
        arrowKey = findKey(KeyIndex.INDEX_ARROW)
        metaAltKey = findKey(KeyIndex.INDEX_META_ALT)
        ctrlAltKey = findKey(KeyIndex.INDEX_CTRL_ALT)
        return keyboard
    }

    private fun findKey(index: Int): Key? {
        return keyboard?.keys?.find { it.codes[0] == index }
    }

    /**
     * Updates arrow key face.
     * If you call this, you have to call [inflateKeyboard] before this,
     * call KeyboardView.draw or KeyboardView.invalidateKey(s) after this.
     * @param mode current arrow key mode
     */
    fun updateArrowKeyFace(mode: ArrowKey.Mode) {
        arrowKey?.apply {
            text = null
            icon = ContextCompat.getDrawable(context, when(mode) {
                ArrowKey.Mode.DEFAULT   -> R.drawable.keyicon_arrow_mode1
                ArrowKey.Mode.PAGE_MOVE -> R.drawable.keyicon_arrow_mode2
            })
        }
    }

    /**
     * Updates modifier keys face.
     * If you call this, you have to call [inflateKeyboard] before this,
     * call KeyboardView.draw or KeyboardView.invalidateKey(s) after this.
     * @param mods set of modifier keys info that are enable now
     */
    fun updateModKeyFace(mods: Set<ModKeyInfo>) {
        val metaCode = toCode(ModKeyInfo.META, mods)
        val ctrlCode = toCode(ModKeyInfo.CTRL, mods)
        val altCode  = toCode(ModKeyInfo.ALT,  mods)
        metaAltKey?.apply {
            text = null
            icon = ContextCompat.getDrawable(context, when(10 * metaCode + altCode) {
                22 -> R.drawable.keyicon_meta_alt_lock_lock
                21 -> R.drawable.keyicon_meta_alt_lock_on
                20 -> R.drawable.keyicon_meta_alt_lock_off
                12 -> R.drawable.keyicon_meta_alt_on_lock
                11 -> R.drawable.keyicon_meta_alt_on_on
                10 -> R.drawable.keyicon_meta_alt_on_off
                 2 -> R.drawable.keyicon_meta_alt_off_lock
                 1 -> R.drawable.keyicon_meta_alt_off_on
                 0 -> R.drawable.keyicon_meta_alt_off_off
                else -> R.drawable.keyicon_meta_alt_off_off
            })
        }
        ctrlAltKey?.apply {
            text = null
            icon = ContextCompat.getDrawable(context, when(10 * ctrlCode + altCode) {
                22 -> R.drawable.keyicon_ctrl_alt_lock_lock
                21 -> R.drawable.keyicon_ctrl_alt_lock_on
                20 -> R.drawable.keyicon_ctrl_alt_lock_off
                12 -> R.drawable.keyicon_ctrl_alt_on_lock
                11 -> R.drawable.keyicon_ctrl_alt_on_on
                10 -> R.drawable.keyicon_ctrl_alt_on_off
                 2 -> R.drawable.keyicon_ctrl_alt_off_lock
                 1 -> R.drawable.keyicon_ctrl_alt_off_on
                 0 -> R.drawable.keyicon_ctrl_alt_off_off
                else -> R.drawable.keyicon_ctrl_alt_off_off
            })
        }
    }

    private fun toCode(key: ModKeyInfo, mods: Set<ModKeyInfo>): Int {
        val list = mods.filter { it.code == key.code }
        return if (list.isEmpty()) 0
               else (if (list.any { it.lockable }) 2 else 1)
    }

}