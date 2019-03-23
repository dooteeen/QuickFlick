package com.rkbk60.quickflick.model

/**
 * KeyInfo to control application with keyboard action.
 */
sealed class TriggerKeyInfo: KeyInfo() {
    final override val mods = Null.mods

    object ArrowKeyMode : TriggerKeyInfo()

    object KeyboardLayout : TriggerKeyInfo()

    object KeyboardApp : TriggerKeyInfo()
}
