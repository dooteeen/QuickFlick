package com.rkbk60.quickflick.model

import android.view.KeyEvent

/**
 * Modifier key information.
 */
sealed class ModKeyInfo: KeyInfo() {
    /**
     * Int code equaled to KeyEvent.mCode.
     * Use left modifier code. It equals to KeyEvent.KEYCODE_XXX_LEFT.
     */
    abstract val code: Int

    /**
     * Int code equaled to KeyEvent.mMeta.
     * Use left modifier code. It equals to (KeyEvent.META_XXX_ON or KeyEvent.META_XXX_LEFT_ON)
     */
    abstract val meta: Int

    /**
     * Flag of whether is Lock mode.
     * If it would be true, the key can toggle LOCK mode.
     */
    open val lockable = false

    object Shift : ModKeyInfo() {
        override val code = KeyEvent.KEYCODE_SHIFT_LEFT
        override val meta = KeyEvent.META_SHIFT_ON or KeyEvent.META_SHIFT_LEFT_ON
    }

    object ShiftLock : ModKeyInfo() {
        override val code = Shift.code
        override val meta = Shift.meta
        override val lockable = true
    }

    object Ctrl : ModKeyInfo() {
        override val code = KeyEvent.KEYCODE_CTRL_LEFT
        override val meta = KeyEvent.META_CTRL_ON or KeyEvent.META_CTRL_LEFT_ON
    }

    object CtrlLock : ModKeyInfo() {
        override val code = Ctrl.code
        override val meta = Ctrl.meta
        override val lockable = true
    }

    object Alt : ModKeyInfo() {
        override val code = KeyEvent.KEYCODE_ALT_LEFT
        override val meta = KeyEvent.META_ALT_ON or KeyEvent.META_ALT_LEFT_ON
    }

    object AltLock : ModKeyInfo() {
        override val code = Alt.code
        override val meta = Alt.meta
        override val lockable = true
    }

    object Meta : ModKeyInfo() {
        override val code = KeyEvent.KEYCODE_META_LEFT
        override val meta = KeyEvent.META_META_ON or KeyEvent.META_META_LEFT_ON
    }

    object MetaLock : ModKeyInfo() {
        override val code = Meta.code
        override val meta = Meta.meta
        override val lockable = true
    }

}
