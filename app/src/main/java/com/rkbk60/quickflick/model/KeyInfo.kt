package com.rkbk60.quickflick.model

/**
 * Container of key information.
 */
abstract class KeyInfo {
    open val mods = listOf<ModKeyInfo>()
    open val preview = Preview("")

    data class Preview(val main: String, val sub: String = main)

    object Null: KeyInfo()
}
