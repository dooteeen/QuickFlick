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
    var footerLevel = 2
        private set
    var heightLevel = 2
        private set

    /**
     * Constructs keyboard instance.
     * @param isRight whether or not keyboard layout is for right hand one.
     * @param footerLevel footer height level (1 ~ 5).
     * @param heightLevel height level (1 ~ 5). If value is out of this range, it will be 2.
     * @return Keyboard instance you wanted
     */
    fun inflateKeyboard(isRight: Boolean = this.isRight,
                        footerLevel: Int = this.footerLevel,
                        heightLevel: Int = this.heightLevel): Keyboard {
        this.isRight = isRight
        this.footerLevel = footerLevel
        this.heightLevel = heightLevel
        val keyboard = Keyboard(context, when(Triple(isRight, footerLevel, heightLevel)) {
            Triple(true, 1, 1) -> R.xml.keyboard_a1_right
            Triple(true, 1, 2) -> R.xml.keyboard_a2_right
            Triple(true, 1, 3) -> R.xml.keyboard_a3_right
            Triple(true, 1, 4) -> R.xml.keyboard_a4_right
            Triple(true, 1, 5) -> R.xml.keyboard_a5_right
            Triple(true, 2, 1) -> R.xml.keyboard_b1_right
            Triple(true, 2, 2) -> R.xml.keyboard_b2_right // default
            Triple(true, 2, 3) -> R.xml.keyboard_b3_right
            Triple(true, 2, 4) -> R.xml.keyboard_b4_right
            Triple(true, 2, 5) -> R.xml.keyboard_b5_right
            Triple(true, 3, 1) -> R.xml.keyboard_c1_right
            Triple(true, 3, 2) -> R.xml.keyboard_c2_right
            Triple(true, 3, 3) -> R.xml.keyboard_c3_right
            Triple(true, 3, 4) -> R.xml.keyboard_c4_right
            Triple(true, 3, 5) -> R.xml.keyboard_c5_right
            Triple(true, 4, 1) -> R.xml.keyboard_d1_right
            Triple(true, 4, 2) -> R.xml.keyboard_d2_right
            Triple(true, 4, 3) -> R.xml.keyboard_d3_right
            Triple(true, 4, 4) -> R.xml.keyboard_d4_right
            Triple(true, 4, 5) -> R.xml.keyboard_d5_right
            Triple(true, 5, 1) -> R.xml.keyboard_e1_right
            Triple(true, 5, 2) -> R.xml.keyboard_e2_right
            Triple(true, 5, 3) -> R.xml.keyboard_e3_right
            Triple(true, 5, 4) -> R.xml.keyboard_e4_right
            Triple(true, 5, 5) -> R.xml.keyboard_e5_right
            Triple(false, 1, 1) -> R.xml.keyboard_a1_left
            Triple(false, 1, 2) -> R.xml.keyboard_a2_left
            Triple(false, 1, 3) -> R.xml.keyboard_a3_left
            Triple(false, 1, 4) -> R.xml.keyboard_a4_left
            Triple(false, 1, 5) -> R.xml.keyboard_a5_left
            Triple(false, 2, 1) -> R.xml.keyboard_b1_left
            Triple(false, 2, 2) -> R.xml.keyboard_b2_left
            Triple(false, 2, 3) -> R.xml.keyboard_b3_left
            Triple(false, 2, 4) -> R.xml.keyboard_b4_left
            Triple(false, 2, 5) -> R.xml.keyboard_b5_left
            Triple(false, 3, 1) -> R.xml.keyboard_c1_left
            Triple(false, 3, 2) -> R.xml.keyboard_c2_left
            Triple(false, 3, 3) -> R.xml.keyboard_c3_left
            Triple(false, 3, 4) -> R.xml.keyboard_c4_left
            Triple(false, 3, 5) -> R.xml.keyboard_c5_left
            Triple(false, 4, 1) -> R.xml.keyboard_d1_left
            Triple(false, 4, 2) -> R.xml.keyboard_d2_left
            Triple(false, 4, 3) -> R.xml.keyboard_d3_left
            Triple(false, 4, 4) -> R.xml.keyboard_d4_left
            Triple(false, 4, 5) -> R.xml.keyboard_d5_left
            Triple(false, 5, 1) -> R.xml.keyboard_e1_left
            Triple(false, 5, 2) -> R.xml.keyboard_e2_left
            Triple(false, 5, 3) -> R.xml.keyboard_e3_left
            Triple(false, 5, 4) -> R.xml.keyboard_e4_left
            Triple(false, 5, 5) -> R.xml.keyboard_e5_left
            else -> R.xml.keyboard_b2_right
        })
        this.keyboard = keyboard
        arrowKey = findKey(KeyIndex.INDEX_ARROW)
        metaAltKey = findKey(KeyIndex.INDEX_META_ALT)
        ctrlAltKey = findKey(KeyIndex.INDEX_CTRL_ALT)
        return keyboard
    }

    fun findKey(index: Int): Key? {
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
                ArrowKey.Mode.DEFAULT   -> R.mipmap.keyicon_arrow_mode1
                ArrowKey.Mode.PAGE_MOVE -> R.mipmap.keyicon_arrow_mode2
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
        val metaCode = toCode(ModKeyInfo.Meta, mods)
        val ctrlCode = toCode(ModKeyInfo.Ctrl, mods)
        val altCode  = toCode(ModKeyInfo.Alt,  mods)
        metaAltKey?.apply {
            text = null
            icon = ContextCompat.getDrawable(context, when(10 * metaCode + altCode) {
                22 -> R.mipmap.keyicon_meta_alt_lock_lock
                21 -> R.mipmap.keyicon_meta_alt_lock_on
                20 -> R.mipmap.keyicon_meta_alt_lock_off
                12 -> R.mipmap.keyicon_meta_alt_on_lock
                11 -> R.mipmap.keyicon_meta_alt_on_on
                10 -> R.mipmap.keyicon_meta_alt_on_off
                 2 -> R.mipmap.keyicon_meta_alt_off_lock
                 1 -> R.mipmap.keyicon_meta_alt_off_on
                 0 -> R.mipmap.keyicon_meta_alt_off_off
                else -> R.mipmap.keyicon_meta_alt_off_off
            })
        }
        ctrlAltKey?.apply {
            text = null
            icon = ContextCompat.getDrawable(context, when(10 * ctrlCode + altCode) {
                22 -> R.mipmap.keyicon_ctrl_alt_lock_lock
                21 -> R.mipmap.keyicon_ctrl_alt_lock_on
                20 -> R.mipmap.keyicon_ctrl_alt_lock_off
                12 -> R.mipmap.keyicon_ctrl_alt_on_lock
                11 -> R.mipmap.keyicon_ctrl_alt_on_on
                10 -> R.mipmap.keyicon_ctrl_alt_on_off
                 2 -> R.mipmap.keyicon_ctrl_alt_off_lock
                 1 -> R.mipmap.keyicon_ctrl_alt_off_on
                 0 -> R.mipmap.keyicon_ctrl_alt_off_off
                else -> R.mipmap.keyicon_ctrl_alt_off_off
            })
        }
    }

    private fun toCode(key: ModKeyInfo, mods: Set<ModKeyInfo>): Int {
        val list = mods.filter { it.code == key.code }
        return if (list.isEmpty()) 0
               else (if (list.any { it.lockable }) 2 else 1)
    }

}