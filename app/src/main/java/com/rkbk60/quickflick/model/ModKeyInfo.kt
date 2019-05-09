package com.rkbk60.quickflick.model

import android.view.KeyEvent

/**
 * Modifier key information.
 */
sealed class ModKeyInfo: KeyInfo() {
    abstract override val preview: Preview

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
    val lockable: Boolean
        get() = state == State.LOCK

    open val state = State.ON

    enum class State {
        OFF, ON, LOCK;

        override fun toString(): String = when (this) {
            OFF  -> "off"
            ON   -> "On"
            LOCK -> "Lock"
        }
    }

    fun updateFrom(currentState: State): State = when (this) {
        Shift, Ctrl, Alt, Meta -> when (currentState) {
            State.OFF  -> State.ON
            State.ON   -> State.OFF
            State.LOCK -> State.OFF
        }
        ShiftLock, CtrlLock, AltLock, MetaLock -> when (currentState) {
            State.OFF  -> State.LOCK
            State.ON   -> State.LOCK
            State.LOCK -> State.OFF
        }
    }

    object Shift : ModKeyInfo() {
        override val code = KeyEvent.KEYCODE_SHIFT_LEFT
        override val meta = KeyEvent.META_SHIFT_ON or KeyEvent.META_SHIFT_LEFT_ON
        override val preview = Preview("Shift", "\ufb35") // apple-keyboard-shift
    }

    object ShiftLock : ModKeyInfo() {
        override val code = Shift.code
        override val meta = Shift.meta
        override val state = State.LOCK
        override val preview = Shift.preview
    }

    object Ctrl : ModKeyInfo() {
        override val code = KeyEvent.KEYCODE_CTRL_LEFT
        override val meta = KeyEvent.META_CTRL_ON or KeyEvent.META_CTRL_LEFT_ON
        override val preview = Preview("Ctrl", "\ufb32") // apple-keyboard-command
    }

    object CtrlLock : ModKeyInfo() {
        override val code = Ctrl.code
        override val meta = Ctrl.meta
        override val state = State.LOCK
        override val preview = Ctrl.preview
    }

    object Alt : ModKeyInfo() {
        override val code = KeyEvent.KEYCODE_ALT_LEFT
        override val meta = KeyEvent.META_ALT_ON or KeyEvent.META_ALT_LEFT_ON
        override val preview = Preview("Alt", "\ufb34") // apple-keyboard-option
    }

    object AltLock : ModKeyInfo() {
        override val code = Alt.code
        override val meta = Alt.meta
        override val state = State.LOCK
        override val preview = Alt.preview
    }

    object Meta : ModKeyInfo() {
        override val code = KeyEvent.KEYCODE_META_LEFT
        override val meta = KeyEvent.META_META_ON or KeyEvent.META_META_LEFT_ON
        override val preview = Preview("Meta", "\ufc0a") // rhombus-outline
    }

    object MetaLock : ModKeyInfo() {
        override val code = Meta.code
        override val meta = Meta.meta
        override val state = State.LOCK
        override val preview = Meta.preview
    }

}
