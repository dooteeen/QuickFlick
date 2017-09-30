package com.rkbk60.quickflick

/**
 * 
 */

internal class KeymapController {

    private val keymap = Keymap()

    private val NONE  = Flick.Direction.NONE
    private val LEFT  = Flick.Direction.LEFT
    private val UP    = Flick.Direction.UP
    private val DOWN  = Flick.Direction.DOWN
    private val RIGHT = Flick.Direction.RIGHT

    fun createInitialKeymap(): Keymap {
        generateBasicKeymap()
        generateAllowKeymap()
        generateCharKeymap()
        return keymap
    }

    fun updateArrowKeymap(state: ArrowKey.State) {
        generateAllowKeymap(state)
    }

    private fun generateBasicKeymap() {
        keymap.apply {
            clear(1)
            put(1, SpecialKeyCode.ESCAPE, NONE)
            clear(5)
            put(5, SpecialKeyCode.TAB, NONE)
            put(5, SpecialKeyCode.ENTER, DOWN)
            clear(6)
            put(6, SpecialKeyCode.META, NONE)
            put(6, SpecialKeyCode.META_LOCK, RIGHT)
            put(6, SpecialKeyCode.ALT, DOWN)
            put(6, SpecialKeyCode.ALT_LOCK, UP)
            clear(10)
            put(10, SpecialKeyCode.DELETE, NONE)
            clear(11)
            put(11, SpecialKeyCode.CTRL, NONE)
            put(11, SpecialKeyCode.CTRL_LOCK, RIGHT)
            put(11, SpecialKeyCode.ALT, DOWN)
            put(11, SpecialKeyCode.ALT_LOCK, UP)
            clear(15)
            put(15, SpecialKeyCode.BACKSPACE, NONE)
        }
    }

    private fun generateAllowKeymap(state: ArrowKey.State = ArrowKey.State.DEFAULT) {
        keymap.apply {
            clear(2)
            when (state) {
               ArrowKey.State.PAGE_MOVE -> {
                   put(2, SpecialKeyCode.PAGE_DOWN, DOWN)
                   put(2, SpecialKeyCode.HOME, LEFT)
                   put(2, SpecialKeyCode.PAGE_UP, UP)
                   put(2, SpecialKeyCode.END, RIGHT)
               }
               else -> {
                   put(2, SpecialKeyCode.DOWN, DOWN)
                   put(2, SpecialKeyCode.LEFT, LEFT)
                   put(2, SpecialKeyCode.UP, UP)
                   put(2, SpecialKeyCode.RIGHT, RIGHT)
               }
            }
        }
    }

    private fun generateCharKeymap() {
        keymap.apply {
            clear(3)
            put(3, '0', NONE)
            put(3, '!', DOWN)
            put(3, '@', LEFT)
            put(3, '#', UP)
            put(3, '$', RIGHT)
            clear(4)
            put(4, ' ', NONE)
            put(4, '%', DOWN)
            put(4, '^', LEFT)
            put(4, '&', UP)
            put(4, '*', RIGHT)
            clear(7)
            put(7, '1', NONE)
            put(7, '.', DOWN)
            put(7, ',', LEFT)
            put(7, '?', UP)
            put(7, '/', RIGHT)
            clear(8)
            put(8, '2', NONE)
            put(8, 'A', DOWN)
            put(8, 'a', DOWN)
            put(8, 'B', LEFT)
            put(8, 'b', LEFT)
            put(8, 'C', UP)
            put(8, 'c', UP)
            put(8, '_', RIGHT)
            put(8, '-', RIGHT)
            clear(9)
            put(9, '3', NONE)
            put(9, 'D', DOWN)
            put(9, 'd', DOWN)
            put(9, 'E', LEFT)
            put(9, 'e', LEFT)
            put(9, 'F', UP)
            put(9, 'f', UP)
            put(9, '+', RIGHT)
            put(9, '=', RIGHT)
            clear(12)
            put(12, '4', NONE)
            put(12, 'G', DOWN)
            put(12, 'g', DOWN)
            put(12, 'H', LEFT)
            put(12, 'h', LEFT)
            put(12, 'I', UP)
            put(12, 'i', UP)
            put(12, ':', RIGHT)
            put(12, ';', RIGHT)
            clear(13)
            put(13, '5', NONE)
            put(13, 'J', DOWN)
            put(13, 'j', DOWN)
            put(13, 'K', LEFT)
            put(13, 'k', LEFT)
            put(13, 'L', UP)
            put(13, 'l', UP)
            put(13, '\'', RIGHT)
            put(13, '"', RIGHT)
            clear(14)
            put(14, '6', NONE)
            put(14, 'M', DOWN)
            put(14, 'm', DOWN)
            put(14, 'N', LEFT)
            put(14, 'n', LEFT)
            put(14, 'O', UP)
            put(14, 'o', UP)
            put(14, '|', RIGHT)
            put(14, '\\', RIGHT)
            clear(16)
            put(16, '(', NONE)
            put(16, '{', RIGHT)
            put(16, '[', RIGHT)
            put(16, '<', RIGHT)
            clear(17)
            put(17, '7', NONE)
            put(17, 'P', DOWN)
            put(17, 'p', DOWN)
            put(17, 'Q', LEFT)
            put(17, 'q', LEFT)
            put(17, 'R', UP)
            put(17, 'r', UP)
            put(17, 'S', RIGHT)
            put(17, 's', RIGHT)
            clear(18)
            put(18, '8', NONE)
            put(18, 'T', DOWN)
            put(18, 't', DOWN)
            put(18, 'U', LEFT)
            put(18, 'u', LEFT)
            put(18, 'V', UP)
            put(18, 'v', UP)
            put(18, '~', RIGHT)
            put(18, '`', RIGHT)
            clear(19)
            put(19, '9', NONE)
            put(19, 'W', DOWN)
            put(19, 'w', DOWN)
            put(19, 'X', LEFT)
            put(19, 'x', LEFT)
            put(19, 'Y', UP)
            put(19, 'y', UP)
            put(19, 'Z', RIGHT)
            put(19, 'z', RIGHT)
            clear(20)
            put(20, ')', NONE)
            put(20, '}', LEFT)
            put(20, ']', LEFT)
            put(20, '>', LEFT)
        }
    }

}
