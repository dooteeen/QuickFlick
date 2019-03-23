package com.rkbk60.quickflick.model

import android.view.KeyEvent

/**
 * Key Info object which has ASCII code except modifier one.
 */
sealed class AsciiKeyInfo : KeyInfo() {
    /**
     * Tags to show what implemented classes can modify.
     * It is equals to that classes which implemented this interface
     * can convert to {@code KeyEvent.KEYCODE}.
     * What one key has corresponding key code means to can construct KeyEvent instance with
     * modifier key, like Ctrl or Alt.
     */
    interface Modifiable {
        /**
         * Value of corresponding key code(KeyEvent.KEYCODE_XXX).
         */
        val code: Int
    }

    /**
     * Character information object.
     */
    abstract class CharKey : AsciiKeyInfo() {
        /**
         * Character that object shows.
         */
        abstract val char: Char
    }

    object Num0 : CharKey(), Modifiable {
        override val char = '0'
        override val code = KeyEvent.KEYCODE_0
    }

    object Num1 : CharKey(), Modifiable {
        override val char = '1'
        override val code = KeyEvent.KEYCODE_1
    }

    object Num2 : CharKey(), Modifiable {
        override val char = '2'
        override val code = KeyEvent.KEYCODE_2
    }

    object Num3 : CharKey(), Modifiable {
        override val char = '3'
        override val code = KeyEvent.KEYCODE_3
    }

    object Num4 : CharKey(), Modifiable {
        override val char = '4'
        override val code = KeyEvent.KEYCODE_4
    }

    object Num5 : CharKey(), Modifiable {
        override val char = '5'
        override val code = KeyEvent.KEYCODE_5
    }

    object Num6 : CharKey(), Modifiable {
        override val char = '6'
        override val code = KeyEvent.KEYCODE_6
    }

    object Num7 : CharKey(), Modifiable {
        override val char = '7'
        override val code = KeyEvent.KEYCODE_7
    }

    object Num8 : CharKey(), Modifiable {
        override val char = '8'
        override val code = KeyEvent.KEYCODE_8
    }

    object Num9 : CharKey(), Modifiable {
        override val char = '9'
        override val code = KeyEvent.KEYCODE_9
    }

    object SmallA : CharKey(), Modifiable {
        override val char = 'a'
        override val code = KeyEvent.KEYCODE_A
    }

    object LargeA : CharKey(), Modifiable {
        override val char = 'A'
        override val code = KeyEvent.KEYCODE_A
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallB : CharKey(), Modifiable {
        override val char = 'b'
        override val code = KeyEvent.KEYCODE_B
    }

    object LargeB : CharKey(), Modifiable {
        override val char = 'B'
        override val code = KeyEvent.KEYCODE_B
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallC : CharKey(), Modifiable {
        override val char = 'c'
        override val code = KeyEvent.KEYCODE_C
    }

    object LargeC : CharKey(), Modifiable {
        override val char = 'C'
        override val code = KeyEvent.KEYCODE_C
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallD : CharKey(), Modifiable {
        override val char = 'd'
        override val code = KeyEvent.KEYCODE_D
    }

    object LargeD : CharKey(), Modifiable {
        override val char = 'D'
        override val code = KeyEvent.KEYCODE_D
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallE : CharKey(), Modifiable {
        override val char = 'e'
        override val code = KeyEvent.KEYCODE_E
    }

    object LargeE : CharKey(), Modifiable {
        override val char = 'E'
        override val code = KeyEvent.KEYCODE_E
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallF : CharKey(), Modifiable {
        override val char = 'f'
        override val code = KeyEvent.KEYCODE_F
    }

    object LargeF : CharKey(), Modifiable {
        override val char = 'F'
        override val code = KeyEvent.KEYCODE_F
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallG : CharKey(), Modifiable {
        override val char = 'g'
        override val code = KeyEvent.KEYCODE_G
    }

    object LargeG : CharKey(), Modifiable {
        override val char = 'G'
        override val code = KeyEvent.KEYCODE_G
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallH : CharKey(), Modifiable {
        override val char = 'h'
        override val code = KeyEvent.KEYCODE_H
    }

    object LargeH : CharKey(), Modifiable {
        override val char = 'H'
        override val code = KeyEvent.KEYCODE_H
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallI : CharKey(), Modifiable {
        override val char = 'i'
        override val code = KeyEvent.KEYCODE_I
    }

    object LargeI : CharKey(), Modifiable {
        override val char = 'I'
        override val code = KeyEvent.KEYCODE_I
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallJ : CharKey(), Modifiable {
        override val char = 'j'
        override val code = KeyEvent.KEYCODE_J
    }

    object LargeJ : CharKey(), Modifiable {
        override val char = 'J'
        override val code = KeyEvent.KEYCODE_J
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallK : CharKey(), Modifiable {
        override val char = 'k'
        override val code = KeyEvent.KEYCODE_K
    }

    object LargeK : CharKey(), Modifiable {
        override val char = 'K'
        override val code = KeyEvent.KEYCODE_K
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallL : CharKey(), Modifiable {
        override val char = 'l'
        override val code = KeyEvent.KEYCODE_L
    }

    object LargeL : CharKey(), Modifiable {
        override val char = 'L'
        override val code = KeyEvent.KEYCODE_L
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallM : CharKey(), Modifiable {
        override val char = 'm'
        override val code = KeyEvent.KEYCODE_M
    }

    object LargeM : CharKey(), Modifiable {
        override val char = 'M'
        override val code = KeyEvent.KEYCODE_M
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallN : CharKey(), Modifiable {
        override val char = 'n'
        override val code = KeyEvent.KEYCODE_N
    }

    object LargeN : CharKey(), Modifiable {
        override val char = 'N'
        override val code = KeyEvent.KEYCODE_N
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallO : CharKey(), Modifiable {
        override val char = 'o'
        override val code = KeyEvent.KEYCODE_O
    }

    object LargeO : CharKey(), Modifiable {
        override val char = 'O'
        override val code = KeyEvent.KEYCODE_O
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallP : CharKey(), Modifiable {
        override val char = 'p'
        override val code = KeyEvent.KEYCODE_P
    }

    object LargeP : CharKey(), Modifiable {
        override val char = 'P'
        override val code = KeyEvent.KEYCODE_P
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallQ : CharKey(), Modifiable {
        override val char = 'q'
        override val code = KeyEvent.KEYCODE_Q
    }

    object LargeQ : CharKey(), Modifiable {
        override val char = 'Q'
        override val code = KeyEvent.KEYCODE_Q
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallR : CharKey(), Modifiable {
        override val char = 'r'
        override val code = KeyEvent.KEYCODE_R
    }

    object LargeR : CharKey(), Modifiable {
        override val char = 'R'
        override val code = KeyEvent.KEYCODE_R
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallS : CharKey(), Modifiable {
        override val char = 's'
        override val code = KeyEvent.KEYCODE_S
    }

    object LargeS : CharKey(), Modifiable {
        override val char = 'S'
        override val code = KeyEvent.KEYCODE_S
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallT : CharKey(), Modifiable {
        override val char = 't'
        override val code = KeyEvent.KEYCODE_T
    }

    object LargeT : CharKey(), Modifiable {
        override val char = 'T'
        override val code = KeyEvent.KEYCODE_T
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallU : CharKey(), Modifiable {
        override val char = 'u'
        override val code = KeyEvent.KEYCODE_U
    }

    object LargeU : CharKey(), Modifiable {
        override val char = 'U'
        override val code = KeyEvent.KEYCODE_U
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallV : CharKey(), Modifiable {
        override val char = 'v'
        override val code = KeyEvent.KEYCODE_V
    }

    object LargeV : CharKey(), Modifiable {
        override val char = 'V'
        override val code = KeyEvent.KEYCODE_V
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallW : CharKey(), Modifiable {
        override val char = 'w'
        override val code = KeyEvent.KEYCODE_W
    }

    object LargeW : CharKey(), Modifiable {
        override val char = 'W'
        override val code = KeyEvent.KEYCODE_W
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallX : CharKey(), Modifiable {
        override val char = 'x'
        override val code = KeyEvent.KEYCODE_X
    }

    object LargeX : CharKey(), Modifiable {
        override val char = 'X'
        override val code = KeyEvent.KEYCODE_X
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallY : CharKey(), Modifiable {
        override val char = 'y'
        override val code = KeyEvent.KEYCODE_Y
    }

    object LargeY : CharKey(), Modifiable {
        override val char = 'Y'
        override val code = KeyEvent.KEYCODE_Y
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallZ : CharKey(), Modifiable {
        override val char = 'z'
        override val code = KeyEvent.KEYCODE_Z
    }

    object LargeZ : CharKey(), Modifiable {
        override val char = 'Z'
        override val code = KeyEvent.KEYCODE_Z
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object Space : CharKey(), Modifiable {
        override val char = ' '
        override val code = KeyEvent.KEYCODE_SPACE
    }

    object Exclamation : CharKey() {
        override val char = '!'
    }

    object At : CharKey(), Modifiable {
        override val char = '@'
        override val code = KeyEvent.KEYCODE_AT
    }

    object Pound : CharKey(), Modifiable {
        override val char = '#'
        override val code = KeyEvent.KEYCODE_POUND
    }

    object Dollar : CharKey() {
        override val char = '$'
    }

    object Percent : CharKey() {
        override val char = '%'
    }

    object Caret : CharKey() {
        override val char = '^'
    }

    object Ampersand : CharKey() {
        override val char = '&'
    }

    object Star : CharKey(), Modifiable {
        override val char = '*'
        override val code = KeyEvent.KEYCODE_STAR
    }

    object Period : CharKey(), Modifiable {
        override val char = '.'
        override val code = KeyEvent.KEYCODE_PERIOD
    }

    object Comma : CharKey(), Modifiable {
        override val char = ','
        override val code = KeyEvent.KEYCODE_COMMA
    }

    object Question : CharKey() {
        override val char = '?'
    }

    object Slash : CharKey(), Modifiable {
        override val char = '/'
        override val code = KeyEvent.KEYCODE_SLASH
    }

    object Underscore : CharKey() {
        override val char = '_'
    }

    object Minus : CharKey(), Modifiable {
        override val char = '-'
        override val code = KeyEvent.KEYCODE_MINUS
    }

    object Plus : CharKey(), Modifiable {
        override val char = '+'
        override val code = KeyEvent.KEYCODE_PLUS
    }

    object Equals : CharKey(), Modifiable {
        override val char = '='
        override val code = KeyEvent.KEYCODE_EQUALS
    }

    object Colon : CharKey() {
        override val char = ':'
    }

    object Semicolon : CharKey(), Modifiable {
        override val char = ';'
        override val code = KeyEvent.KEYCODE_SEMICOLON
    }

    object Ditto : CharKey() {
        override val char = '"'
    }

    object Apostrophe : CharKey(), Modifiable {
        override val char = '\''
        override val code = KeyEvent.KEYCODE_APOSTROPHE
    }

    object Bar : CharKey() {
        override val char = '|'
    }

    object Backslash : CharKey(), Modifiable {
        override val char = '\\'
        override val code = KeyEvent.KEYCODE_BACKSLASH
    }

    object Tilda : CharKey() {
        override val char = '~'
    }

    object Grave : CharKey(), Modifiable {
        override val char = '`'
        override val code = KeyEvent.KEYCODE_GRAVE
    }

    object ParenLeft : CharKey() {
        override val char = '('
    }

    object ParenRight : CharKey() {
        override val char = ')'
    }

    object CurlyLeft : CharKey() {
        override val char = '{'
    }

    object CurlyRight : CharKey() {
        override val char = '}'
    }

    object SquareLeft : CharKey(), Modifiable {
        override val char = '['
        override val code = KeyEvent.KEYCODE_LEFT_BRACKET
    }

    object SquareRight : CharKey(), Modifiable {
        override val char = ']'
        override val code = KeyEvent.KEYCODE_RIGHT_BRACKET
    }

    object Less : CharKey() {
        override val char = '<'
    }

    object Greater : CharKey() {
        override val char = '>'
    }

    /**
     * Non character key information object.
     * All these keys has KeyEvent.KEYCODE, so classes extended this
     * have to define [code] equal to KeyEvent.KEYCODE_XXX.
     */
    abstract class UnCharKey : AsciiKeyInfo(), Modifiable

    object Escape : UnCharKey() {
        override val code = KeyEvent.KEYCODE_ESCAPE
    }

    object Tab : UnCharKey() {
        override val code = KeyEvent.KEYCODE_TAB
    }

    object ShiftTab : UnCharKey() {
        override val code = Tab.code
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object Enter : UnCharKey() {
        override val code = KeyEvent.KEYCODE_ENTER
    }

    object ShiftEnter : UnCharKey() {
        override val code = Enter.code
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object ForwardDelete : UnCharKey() {
        override val code = KeyEvent.KEYCODE_FORWARD_DEL
    }

    object BackDelete : UnCharKey() {
        override val code = KeyEvent.KEYCODE_DEL
    }

    abstract class DirectionKey : UnCharKey()

    object Left : DirectionKey() {
        override val code = KeyEvent.KEYCODE_DPAD_LEFT
    }

    object Right : DirectionKey() {
        override val code = KeyEvent.KEYCODE_DPAD_RIGHT
    }

    object Up : DirectionKey() {
        override val code = KeyEvent.KEYCODE_DPAD_UP
    }

    object Down : DirectionKey() {
        override val code = KeyEvent.KEYCODE_DPAD_DOWN
    }

    object MoveHome : UnCharKey() {
        override val code = KeyEvent.KEYCODE_MOVE_HOME
    }

    object MoveEnd : UnCharKey() {
        override val code = KeyEvent.KEYCODE_MOVE_END
    }

    object PageUp : UnCharKey() {
        override val code = KeyEvent.KEYCODE_PAGE_UP
    }

    object PageDown : UnCharKey() {
        override val code = KeyEvent.KEYCODE_PAGE_DOWN
    }

    object Insert : UnCharKey() {
        override val code = KeyEvent.KEYCODE_INSERT
    }

    object F1 : UnCharKey() {
        override val code = KeyEvent.KEYCODE_F1
    }

    object F2 : UnCharKey() {
        override val code = KeyEvent.KEYCODE_F2
    }

    object F3 : UnCharKey() {
        override val code = KeyEvent.KEYCODE_F3
    }

    object F4 : UnCharKey() {
        override val code = KeyEvent.KEYCODE_F4
    }

    object F5 : UnCharKey() {
        override val code = KeyEvent.KEYCODE_F5
    }

    object F6 : UnCharKey() {
        override val code = KeyEvent.KEYCODE_F6
    }

    object F7 : UnCharKey() {
        override val code = KeyEvent.KEYCODE_F7
    }

    object F8 : UnCharKey() {
        override val code = KeyEvent.KEYCODE_F8
    }

    object F9 : UnCharKey() {
        override val code = KeyEvent.KEYCODE_F9
    }

    object F10 : UnCharKey() {
        override val code = KeyEvent.KEYCODE_F10
    }

    object F11 : UnCharKey() {
        override val code = KeyEvent.KEYCODE_F11
    }

    object F12 : UnCharKey() {
        override val code = KeyEvent.KEYCODE_F12
    }
}
