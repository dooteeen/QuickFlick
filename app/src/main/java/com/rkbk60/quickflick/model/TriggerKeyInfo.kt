package com.rkbk60.quickflick.model

/**
 * KeyInfo to control application with keyboard action.
 */
sealed class TriggerKeyInfo: KeyInfo() {
    final override val mods = Null.mods
    abstract override val preview: Preview

    object ArrowKeyMode : TriggerKeyInfo() {
        override val preview = Preview("\uf021", "\uf04c") // refresh, pause
    }

    object KeyboardLayout : TriggerKeyInfo() {
        override val preview = Preview("\uf100") // angle-double-left
    }

    object KeyboardApp : TriggerKeyInfo() {
        override val preview = Preview("\uf80b") // keyboard
    }
}
