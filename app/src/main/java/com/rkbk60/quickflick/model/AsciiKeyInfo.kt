package com.rkbk60.quickflick.model

import android.view.KeyEvent

/**
 * Key Info object which has ASCII code except modifier one.
 */
sealed class AsciiKeyInfo : KeyInfo() {
    abstract override val preview: Preview

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
        override val preview = Preview("0")
    }

    object Num1 : CharKey(), Modifiable {
        override val char = '1'
        override val code = KeyEvent.KEYCODE_1
        override val preview = Preview("1")
    }

    object Num2 : CharKey(), Modifiable {
        override val char = '2'
        override val code = KeyEvent.KEYCODE_2
        override val preview = Preview("2")
    }

    object Num3 : CharKey(), Modifiable {
        override val char = '3'
        override val code = KeyEvent.KEYCODE_3
        override val preview = Preview("3")
    }

    object Num4 : CharKey(), Modifiable {
        override val char = '4'
        override val code = KeyEvent.KEYCODE_4
        override val preview = Preview("4")
    }

    object Num5 : CharKey(), Modifiable {
        override val char = '5'
        override val code = KeyEvent.KEYCODE_5
        override val preview = Preview("5")
    }

    object Num6 : CharKey(), Modifiable {
        override val char = '6'
        override val code = KeyEvent.KEYCODE_6
        override val preview = Preview("6")
    }

    object Num7 : CharKey(), Modifiable {
        override val char = '7'
        override val code = KeyEvent.KEYCODE_7
        override val preview = Preview("7")
    }

    object Num8 : CharKey(), Modifiable {
        override val char = '8'
        override val code = KeyEvent.KEYCODE_8
        override val preview = Preview("8")
    }

    object Num9 : CharKey(), Modifiable {
        override val char = '9'
        override val code = KeyEvent.KEYCODE_9
        override val preview = Preview("9")
    }

    object SmallA : CharKey(), Modifiable {
        override val char = 'a'
        override val code = KeyEvent.KEYCODE_A
        override val preview = Preview("a")
    }

    object LargeA : CharKey(), Modifiable {
        override val char = 'A'
        override val code = KeyEvent.KEYCODE_A
        override val preview = Preview("A")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallB : CharKey(), Modifiable {
        override val char = 'b'
        override val code = KeyEvent.KEYCODE_B
        override val preview = Preview("b")
    }

    object LargeB : CharKey(), Modifiable {
        override val char = 'B'
        override val code = KeyEvent.KEYCODE_B
        override val preview = Preview("B")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallC : CharKey(), Modifiable {
        override val char = 'c'
        override val code = KeyEvent.KEYCODE_C
        override val preview = Preview("c")
    }

    object LargeC : CharKey(), Modifiable {
        override val char = 'C'
        override val code = KeyEvent.KEYCODE_C
        override val preview = Preview("C")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallD : CharKey(), Modifiable {
        override val char = 'd'
        override val code = KeyEvent.KEYCODE_D
        override val preview = Preview("d")
    }

    object LargeD : CharKey(), Modifiable {
        override val char = 'D'
        override val code = KeyEvent.KEYCODE_D
        override val preview = Preview("D")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallE : CharKey(), Modifiable {
        override val char = 'e'
        override val code = KeyEvent.KEYCODE_E
        override val preview = Preview("e")
    }

    object LargeE : CharKey(), Modifiable {
        override val char = 'E'
        override val code = KeyEvent.KEYCODE_E
        override val preview = Preview("E")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallF : CharKey(), Modifiable {
        override val char = 'f'
        override val code = KeyEvent.KEYCODE_F
        override val preview = Preview("f")
    }

    object LargeF : CharKey(), Modifiable {
        override val char = 'F'
        override val code = KeyEvent.KEYCODE_F
        override val preview = Preview("F")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallG : CharKey(), Modifiable {
        override val char = 'g'
        override val code = KeyEvent.KEYCODE_G
        override val preview = Preview("g")
    }

    object LargeG : CharKey(), Modifiable {
        override val char = 'G'
        override val code = KeyEvent.KEYCODE_G
        override val preview = Preview("G")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallH : CharKey(), Modifiable {
        override val char = 'h'
        override val code = KeyEvent.KEYCODE_H
        override val preview = Preview("h")
    }

    object LargeH : CharKey(), Modifiable {
        override val char = 'H'
        override val code = KeyEvent.KEYCODE_H
        override val preview = Preview("H")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallI : CharKey(), Modifiable {
        override val char = 'i'
        override val code = KeyEvent.KEYCODE_I
        override val preview = Preview("i")
    }

    object LargeI : CharKey(), Modifiable {
        override val char = 'I'
        override val code = KeyEvent.KEYCODE_I
        override val preview = Preview("I")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallJ : CharKey(), Modifiable {
        override val char = 'j'
        override val code = KeyEvent.KEYCODE_J
        override val preview = Preview("j")
    }

    object LargeJ : CharKey(), Modifiable {
        override val char = 'J'
        override val code = KeyEvent.KEYCODE_J
        override val preview = Preview("J")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallK : CharKey(), Modifiable {
        override val char = 'k'
        override val code = KeyEvent.KEYCODE_K
        override val preview = Preview("k")
    }

    object LargeK : CharKey(), Modifiable {
        override val char = 'K'
        override val code = KeyEvent.KEYCODE_K
        override val preview = Preview("K")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallL : CharKey(), Modifiable {
        override val char = 'l'
        override val code = KeyEvent.KEYCODE_L
        override val preview = Preview("l")
    }

    object LargeL : CharKey(), Modifiable {
        override val char = 'L'
        override val code = KeyEvent.KEYCODE_L
        override val preview = Preview("L")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallM : CharKey(), Modifiable {
        override val char = 'm'
        override val code = KeyEvent.KEYCODE_M
        override val preview = Preview("m")
    }

    object LargeM : CharKey(), Modifiable {
        override val char = 'M'
        override val code = KeyEvent.KEYCODE_M
        override val preview = Preview("M")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallN : CharKey(), Modifiable {
        override val char = 'n'
        override val code = KeyEvent.KEYCODE_N
        override val preview = Preview("n")
    }

    object LargeN : CharKey(), Modifiable {
        override val char = 'N'
        override val code = KeyEvent.KEYCODE_N
        override val preview = Preview("N")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallO : CharKey(), Modifiable {
        override val char = 'o'
        override val code = KeyEvent.KEYCODE_O
        override val preview = Preview("o")
    }

    object LargeO : CharKey(), Modifiable {
        override val char = 'O'
        override val code = KeyEvent.KEYCODE_O
        override val preview = Preview("O")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallP : CharKey(), Modifiable {
        override val char = 'p'
        override val code = KeyEvent.KEYCODE_P
        override val preview = Preview("p")
    }

    object LargeP : CharKey(), Modifiable {
        override val char = 'P'
        override val code = KeyEvent.KEYCODE_P
        override val preview = Preview("P")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallQ : CharKey(), Modifiable {
        override val char = 'q'
        override val code = KeyEvent.KEYCODE_Q
        override val preview = Preview("q")
    }

    object LargeQ : CharKey(), Modifiable {
        override val char = 'Q'
        override val code = KeyEvent.KEYCODE_Q
        override val preview = Preview("Q")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallR : CharKey(), Modifiable {
        override val char = 'r'
        override val code = KeyEvent.KEYCODE_R
        override val preview = Preview("r")
    }

    object LargeR : CharKey(), Modifiable {
        override val char = 'R'
        override val code = KeyEvent.KEYCODE_R
        override val preview = Preview("R")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallS : CharKey(), Modifiable {
        override val char = 's'
        override val code = KeyEvent.KEYCODE_S
        override val preview = Preview("s")
    }

    object LargeS : CharKey(), Modifiable {
        override val char = 'S'
        override val code = KeyEvent.KEYCODE_S
        override val preview = Preview("S")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallT : CharKey(), Modifiable {
        override val char = 't'
        override val code = KeyEvent.KEYCODE_T
        override val preview = Preview("t")
    }

    object LargeT : CharKey(), Modifiable {
        override val char = 'T'
        override val code = KeyEvent.KEYCODE_T
        override val preview = Preview("T")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallU : CharKey(), Modifiable {
        override val char = 'u'
        override val code = KeyEvent.KEYCODE_U
        override val preview = Preview("u")
    }

    object LargeU : CharKey(), Modifiable {
        override val char = 'U'
        override val code = KeyEvent.KEYCODE_U
        override val preview = Preview("U")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallV : CharKey(), Modifiable {
        override val char = 'v'
        override val code = KeyEvent.KEYCODE_V
        override val preview = Preview("v")
    }

    object LargeV : CharKey(), Modifiable {
        override val char = 'V'
        override val code = KeyEvent.KEYCODE_V
        override val preview = Preview("V")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallW : CharKey(), Modifiable {
        override val char = 'w'
        override val code = KeyEvent.KEYCODE_W
        override val preview = Preview("w")
    }

    object LargeW : CharKey(), Modifiable {
        override val char = 'W'
        override val code = KeyEvent.KEYCODE_W
        override val preview = Preview("W")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallX : CharKey(), Modifiable {
        override val char = 'x'
        override val code = KeyEvent.KEYCODE_X
        override val preview = Preview("x")
    }

    object LargeX : CharKey(), Modifiable {
        override val char = 'X'
        override val code = KeyEvent.KEYCODE_X
        override val preview = Preview("X")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallY : CharKey(), Modifiable {
        override val char = 'y'
        override val code = KeyEvent.KEYCODE_Y
        override val preview = Preview("y")
    }

    object LargeY : CharKey(), Modifiable {
        override val char = 'Y'
        override val code = KeyEvent.KEYCODE_Y
        override val preview = Preview("Y")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object SmallZ : CharKey(), Modifiable {
        override val char = 'z'
        override val code = KeyEvent.KEYCODE_Z
        override val preview = Preview("z")
    }

    object LargeZ : CharKey(), Modifiable {
        override val char = 'Z'
        override val code = KeyEvent.KEYCODE_Z
        override val preview = Preview("Z")
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object Space : CharKey(), Modifiable {
        override val char = ' '
        override val code = KeyEvent.KEYCODE_SPACE
        override val preview = Preview("Space", "sp")
    }

    object Exclamation : CharKey() {
        override val char = '!'
        override val preview = Preview("!")
    }

    object At : CharKey(), Modifiable {
        override val char = '@'
        override val code = KeyEvent.KEYCODE_AT
        override val preview = Preview("@")
    }

    object Pound : CharKey(), Modifiable {
        override val char = '#'
        override val code = KeyEvent.KEYCODE_POUND
        override val preview = Preview("#")
    }

    object Dollar : CharKey() {
        override val char = '$'
        override val preview = Preview("$")
    }

    object Percent : CharKey() {
        override val char = '%'
        override val preview = Preview("%")
    }

    object Caret : CharKey() {
        override val char = '^'
        override val preview = Preview("^")
    }

    object Ampersand : CharKey() {
        override val char = '&'
        override val preview = Preview("&")
    }

    object Star : CharKey(), Modifiable {
        override val char = '*'
        override val code = KeyEvent.KEYCODE_STAR
        override val preview = Preview("*")
    }

    object Period : CharKey(), Modifiable {
        override val char = '.'
        override val code = KeyEvent.KEYCODE_PERIOD
        override val preview = Preview(".")
    }

    object Comma : CharKey(), Modifiable {
        override val char = ','
        override val code = KeyEvent.KEYCODE_COMMA
        override val preview = Preview(",")
    }

    object Question : CharKey() {
        override val char = '?'
        override val preview = Preview("?")
    }

    object Slash : CharKey(), Modifiable {
        override val char = '/'
        override val code = KeyEvent.KEYCODE_SLASH
        override val preview = Preview("/")
    }

    object Underscore : CharKey() {
        override val char = '_'
        override val preview = Preview("_")
    }

    object Minus : CharKey(), Modifiable {
        override val char = '-'
        override val code = KeyEvent.KEYCODE_MINUS
        override val preview = Preview("-")
    }

    object Plus : CharKey(), Modifiable {
        override val char = '+'
        override val code = KeyEvent.KEYCODE_PLUS
        override val preview = Preview("+")
    }

    object Equals : CharKey(), Modifiable {
        override val char = '='
        override val code = KeyEvent.KEYCODE_EQUALS
        override val preview = Preview("=")
    }

    object Colon : CharKey() {
        override val char = ':'
        override val preview = Preview(":")
    }

    object Semicolon : CharKey(), Modifiable {
        override val char = ';'
        override val code = KeyEvent.KEYCODE_SEMICOLON
        override val preview = Preview("\"")
    }

    object Ditto : CharKey() {
        override val char = '"'
        override val preview = Preview("\"")
    }

    object Apostrophe : CharKey(), Modifiable {
        override val char = '\''
        override val code = KeyEvent.KEYCODE_APOSTROPHE
        override val preview = Preview("'")
    }

    object Bar : CharKey() {
        override val char = '|'
        override val preview = Preview("|")
    }

    object Backslash : CharKey(), Modifiable {
        override val char = '\\'
        override val code = KeyEvent.KEYCODE_BACKSLASH
        override val preview = Preview("\\")
    }

    object Tilda : CharKey() {
        override val char = '~'
        override val preview = Preview("~")
    }

    object Grave : CharKey(), Modifiable {
        override val char = '`'
        override val code = KeyEvent.KEYCODE_GRAVE
        override val preview = Preview("`")
    }

    object ParenLeft : CharKey() {
        override val char = '('
        override val preview = Preview("(")
    }

    object ParenRight : CharKey() {
        override val char = ')'
        override val preview = Preview(")")
    }

    object CurlyLeft : CharKey() {
        override val char = '{'
        override val preview = Preview("{")
    }

    object CurlyRight : CharKey() {
        override val char = '}'
        override val preview = Preview("}")
    }

    object SquareLeft : CharKey(), Modifiable {
        override val char = '['
        override val code = KeyEvent.KEYCODE_LEFT_BRACKET
        override val preview = Preview("[")
    }

    object SquareRight : CharKey(), Modifiable {
        override val char = ']'
        override val code = KeyEvent.KEYCODE_RIGHT_BRACKET
        override val preview = Preview("]")
    }

    object Less : CharKey() {
        override val char = '<'
        override val preview = Preview("<")
    }

    object Greater : CharKey() {
        override val char = '>'
        override val preview = Preview(">")
    }

    /**
     * Non character key information object.
     * All these keys has KeyEvent.KEYCODE, so classes extended this
     * have to define [code] equal to KeyEvent.KEYCODE_XXX.
     */
    abstract class UnCharKey : AsciiKeyInfo(), Modifiable

    object Escape : UnCharKey() {
        override val code = KeyEvent.KEYCODE_ESCAPE
        override val preview = Preview("Esc")
    }

    object Tab : UnCharKey() {
        override val code = KeyEvent.KEYCODE_TAB
        override val preview = Preview("Tab", "\uf811") // keyboard_tab
    }

    object ShiftTab : UnCharKey() {
        override val code = Tab.code
        override val preview = Preview("Shift-Tab", Tab.preview.sub)
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object Enter : UnCharKey() {
        override val code = KeyEvent.KEYCODE_ENTER
        override val preview = Preview("Enter", "\uf810") // keyboard_return
    }

    object ShiftEnter : UnCharKey() {
        override val code = Enter.code
        override val preview = Preview("Shift-Enter", Enter.preview.sub)
        override val mods = listOf(ModKeyInfo.Shift)
    }

    object ForwardDelete : UnCharKey() {
        override val code = KeyEvent.KEYCODE_FORWARD_DEL
        override val preview = BackDelete.preview
    }

    object BackDelete : UnCharKey() {
        override val code = KeyEvent.KEYCODE_DEL
        override val preview = Preview("\uf56d") // backspace
    }

    abstract class DirectionKey : UnCharKey()

    object Left : DirectionKey() {
        override val code = KeyEvent.KEYCODE_DPAD_LEFT
        override val preview = Preview("\uf434") // arrow_left
    }

    object Right : DirectionKey() {
        override val code = KeyEvent.KEYCODE_DPAD_RIGHT
        override val preview = Preview("\uf432") // arrow_right
    }

    object Up : DirectionKey() {
        override val code = KeyEvent.KEYCODE_DPAD_UP
        override val preview = Preview("\uf431") // arrow_up
    }

    object Down : DirectionKey() {
        override val code = KeyEvent.KEYCODE_DPAD_DOWN
        override val preview = Preview("\uf433") // arrow_down
    }

    object MoveHome : UnCharKey() {
        override val code = KeyEvent.KEYCODE_MOVE_HOME
        override val preview = Preview("Home", "Hm")
    }

    object MoveEnd : UnCharKey() {
        override val code = KeyEvent.KEYCODE_MOVE_END
        override val preview = Preview("End", "En")
    }

    object PageUp : UnCharKey() {
        override val code = KeyEvent.KEYCODE_PAGE_UP
        override val preview = Preview("PageUp", "PgUp")
    }

    object PageDown : UnCharKey() {
        override val code = KeyEvent.KEYCODE_PAGE_DOWN
        override val preview = Preview("PageDown", "PgDn")
    }

    object Insert : UnCharKey() {
        override val code = KeyEvent.KEYCODE_INSERT
        override val preview = Preview("Insert")
    }

    abstract class FunctionKey : UnCharKey()

    object F1 : FunctionKey() {
        override val code = KeyEvent.KEYCODE_F1
        override val preview = Preview("F1")
    }

    object F2 : FunctionKey() {
        override val code = KeyEvent.KEYCODE_F2
        override val preview = Preview("F2")
    }

    object F3 : FunctionKey() {
        override val code = KeyEvent.KEYCODE_F3
        override val preview = Preview("F3")
    }

    object F4 : FunctionKey() {
        override val code = KeyEvent.KEYCODE_F4
        override val preview = Preview("F4")
    }

    object F5 : FunctionKey() {
        override val code = KeyEvent.KEYCODE_F5
        override val preview = Preview("F5")
    }

    object F6 : FunctionKey() {
        override val code = KeyEvent.KEYCODE_F6
        override val preview = Preview("F6")
    }

    object F7 : FunctionKey() {
        override val code = KeyEvent.KEYCODE_F7
        override val preview = Preview("F7")
    }

    object F8 : FunctionKey() {
        override val code = KeyEvent.KEYCODE_F8
        override val preview = Preview("F8")
    }

    object F9 : FunctionKey() {
        override val code = KeyEvent.KEYCODE_F9
        override val preview = Preview("F9")
    }

    object F10 : FunctionKey() {
        override val code = KeyEvent.KEYCODE_F10
        override val preview = Preview("F10")
    }

    object F11 : FunctionKey() {
        override val code = KeyEvent.KEYCODE_F11
        override val preview = Preview("F11")
    }

    object F12 : FunctionKey() {
        override val code = KeyEvent.KEYCODE_F12
        override val preview = Preview("F12")
    }
}
