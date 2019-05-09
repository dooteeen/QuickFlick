package com.rkbk60.quickflick.domain

import com.rkbk60.quickflick.model.*

/**
 * Factory and controller for Keymap.
 */
class KeymapController {
    /**
     * Target keymap to manage by this.
     */
    private val keymap = Keymap()

    init {
        generateCharKeymap()
        generateUnCharKeymap()
        generateArrowKeymap(ArrowKey.Mode.DEFAULT)
        generateFnKeymap()
    }

    /**
     * Updates arrow key keymap with current mode.
     */
    fun updateArrowKeymap(mode: ArrowKey.Mode) {
        generateArrowKeymap(mode)
    }

    /**
     * Finds stored KeyInfo from keymap with index and Flick.
     * When keymap have not defined or can't find matching KeyInfo,
     * this method will return KeyInfo.Null.
     * @param index index code of keymap (equal to Keyboard.Key.codes)
     * @param flick flick object that has current flick information
     * @return KeyInfo matching [index] and [flick], or KeyInfo.Null
     * @see Keymap.getKey
     */
    fun getKey(index: Int, flick: Flick): KeyInfo =
            keymap.getKey(index, flick)

    /**
     * Returns max distance that can find KeyInfo in keymap matching parameters.
     * If there aren't matching keymap or KeyInfo, it will be return 0.
     * @param index index code of keymap (equal to Keyboard.Key.codes)
     * @param direction index direction
     * @return max distance
     * @see Keymap.getMaxDistance
     */
    fun getMaxDistance(index: Int, direction: Flick.Direction): Int =
            keymap.getMaxDistance(index, direction)

    /**
     * Returns max distance that can find any KeyInfo in all keymap.
     * @return max distance
     */
    fun getMaxDistance(): Int =
            keymap.getMaxDistance()

    fun getElement(index: Int): ImmutableKeymapElement =
            keymap.cloneElement(index) ?: mapOf()

    /**
     * Defines AsciiKeyInfo.CharKey to keymap.
     */
    private fun generateCharKeymap() {
        keymap.apply {
            clear(KeyIndex.D1)
            put(KeyIndex.D1, Flick.Direction.NONE,  AsciiKeyInfo.Num0)
            put(KeyIndex.D1, Flick.Direction.DOWN,  AsciiKeyInfo.Exclamation)
            put(KeyIndex.D1, Flick.Direction.LEFT,  AsciiKeyInfo.At)
            put(KeyIndex.D1, Flick.Direction.UP,    AsciiKeyInfo.Pound)
            put(KeyIndex.D1, Flick.Direction.RIGHT, AsciiKeyInfo.Dollar)
            clear(KeyIndex.E1)
            put(KeyIndex.E1, Flick.Direction.NONE,  AsciiKeyInfo.Space)
            put(KeyIndex.E1, Flick.Direction.DOWN,  AsciiKeyInfo.Percent)
            put(KeyIndex.E1, Flick.Direction.LEFT,  AsciiKeyInfo.Caret)
            put(KeyIndex.E1, Flick.Direction.UP,    AsciiKeyInfo.Ampersand)
            put(KeyIndex.E1, Flick.Direction.RIGHT, AsciiKeyInfo.Star)
            clear(KeyIndex.C2)
            put(KeyIndex.C2, Flick.Direction.NONE,  AsciiKeyInfo.Num1)
            put(KeyIndex.C2, Flick.Direction.DOWN,  AsciiKeyInfo.Period)
            put(KeyIndex.C2, Flick.Direction.LEFT,  AsciiKeyInfo.Comma)
            put(KeyIndex.C2, Flick.Direction.UP,    AsciiKeyInfo.Question)
            put(KeyIndex.C2, Flick.Direction.RIGHT, AsciiKeyInfo.Slash)
            clear(KeyIndex.D2)
            put(KeyIndex.D2, Flick.Direction.NONE,  AsciiKeyInfo.Num2)
            put(KeyIndex.D2, Flick.Direction.DOWN,  AsciiKeyInfo.LargeA)
            put(KeyIndex.D2, Flick.Direction.DOWN,  AsciiKeyInfo.SmallA)
            put(KeyIndex.D2, Flick.Direction.LEFT,  AsciiKeyInfo.LargeB)
            put(KeyIndex.D2, Flick.Direction.LEFT,  AsciiKeyInfo.SmallB)
            put(KeyIndex.D2, Flick.Direction.UP,    AsciiKeyInfo.LargeC)
            put(KeyIndex.D2, Flick.Direction.UP,    AsciiKeyInfo.SmallC)
            put(KeyIndex.D2, Flick.Direction.RIGHT, AsciiKeyInfo.Underscore)
            put(KeyIndex.D2, Flick.Direction.RIGHT, AsciiKeyInfo.Minus)
            clear(KeyIndex.E2)
            put(KeyIndex.E2, Flick.Direction.NONE,  AsciiKeyInfo.Num3)
            put(KeyIndex.E2, Flick.Direction.DOWN,  AsciiKeyInfo.LargeD)
            put(KeyIndex.E2, Flick.Direction.DOWN,  AsciiKeyInfo.SmallD)
            put(KeyIndex.E2, Flick.Direction.LEFT,  AsciiKeyInfo.LargeE)
            put(KeyIndex.E2, Flick.Direction.LEFT,  AsciiKeyInfo.SmallE)
            put(KeyIndex.E2, Flick.Direction.UP,    AsciiKeyInfo.LargeF)
            put(KeyIndex.E2, Flick.Direction.UP,    AsciiKeyInfo.SmallF)
            put(KeyIndex.E2, Flick.Direction.RIGHT, AsciiKeyInfo.Plus)
            put(KeyIndex.E2, Flick.Direction.RIGHT, AsciiKeyInfo.Equals)
            clear(KeyIndex.C3)
            put(KeyIndex.C3, Flick.Direction.NONE,  AsciiKeyInfo.Num4)
            put(KeyIndex.C3, Flick.Direction.DOWN,  AsciiKeyInfo.LargeG)
            put(KeyIndex.C3, Flick.Direction.DOWN,  AsciiKeyInfo.SmallG)
            put(KeyIndex.C3, Flick.Direction.LEFT,  AsciiKeyInfo.LargeH)
            put(KeyIndex.C3, Flick.Direction.LEFT,  AsciiKeyInfo.SmallH)
            put(KeyIndex.C3, Flick.Direction.UP,    AsciiKeyInfo.LargeI)
            put(KeyIndex.C3, Flick.Direction.UP,    AsciiKeyInfo.SmallI)
            put(KeyIndex.C3, Flick.Direction.RIGHT, AsciiKeyInfo.Colon)
            put(KeyIndex.C3, Flick.Direction.RIGHT, AsciiKeyInfo.Semicolon)
            clear(KeyIndex.D3)
            put(KeyIndex.D3, Flick.Direction.NONE,  AsciiKeyInfo.Num5)
            put(KeyIndex.D3, Flick.Direction.DOWN,  AsciiKeyInfo.LargeJ)
            put(KeyIndex.D3, Flick.Direction.DOWN,  AsciiKeyInfo.SmallJ)
            put(KeyIndex.D3, Flick.Direction.LEFT,  AsciiKeyInfo.LargeK)
            put(KeyIndex.D3, Flick.Direction.LEFT,  AsciiKeyInfo.SmallK)
            put(KeyIndex.D3, Flick.Direction.UP,    AsciiKeyInfo.LargeL)
            put(KeyIndex.D3, Flick.Direction.UP,    AsciiKeyInfo.SmallL)
            put(KeyIndex.D3, Flick.Direction.RIGHT, AsciiKeyInfo.Apostrophe)
            put(KeyIndex.D3, Flick.Direction.RIGHT, AsciiKeyInfo.Ditto)
            clear(KeyIndex.E3)
            put(KeyIndex.E3, Flick.Direction.NONE,  AsciiKeyInfo.Num6)
            put(KeyIndex.E3, Flick.Direction.DOWN,  AsciiKeyInfo.LargeM)
            put(KeyIndex.E3, Flick.Direction.DOWN,  AsciiKeyInfo.SmallM)
            put(KeyIndex.E3, Flick.Direction.LEFT,  AsciiKeyInfo.LargeN)
            put(KeyIndex.E3, Flick.Direction.LEFT,  AsciiKeyInfo.SmallN)
            put(KeyIndex.E3, Flick.Direction.UP,    AsciiKeyInfo.LargeO)
            put(KeyIndex.E3, Flick.Direction.UP,    AsciiKeyInfo.SmallO)
            put(KeyIndex.E3, Flick.Direction.RIGHT, AsciiKeyInfo.Bar)
            put(KeyIndex.E3, Flick.Direction.RIGHT, AsciiKeyInfo.Backslash)
            clear(KeyIndex.B4)
            put(KeyIndex.B4, Flick.Direction.NONE,  AsciiKeyInfo.ParenLeft)
            put(KeyIndex.B4, Flick.Direction.RIGHT, AsciiKeyInfo.CurlyLeft)
            put(KeyIndex.B4, Flick.Direction.RIGHT, AsciiKeyInfo.SquareLeft)
            put(KeyIndex.B4, Flick.Direction.RIGHT, AsciiKeyInfo.Less)
            clear(KeyIndex.C4)
            put(KeyIndex.C4, Flick.Direction.NONE,  AsciiKeyInfo.Num7)
            put(KeyIndex.C4, Flick.Direction.DOWN,  AsciiKeyInfo.LargeP)
            put(KeyIndex.C4, Flick.Direction.DOWN,  AsciiKeyInfo.SmallP)
            put(KeyIndex.C4, Flick.Direction.LEFT,  AsciiKeyInfo.LargeQ)
            put(KeyIndex.C4, Flick.Direction.LEFT,  AsciiKeyInfo.SmallQ)
            put(KeyIndex.C4, Flick.Direction.UP,    AsciiKeyInfo.LargeR)
            put(KeyIndex.C4, Flick.Direction.UP,    AsciiKeyInfo.SmallR)
            put(KeyIndex.C4, Flick.Direction.RIGHT, AsciiKeyInfo.LargeS)
            put(KeyIndex.C4, Flick.Direction.RIGHT, AsciiKeyInfo.SmallS)
            clear(KeyIndex.D4)
            put(KeyIndex.D4, Flick.Direction.NONE,  AsciiKeyInfo.Num8)
            put(KeyIndex.D4, Flick.Direction.DOWN,  AsciiKeyInfo.LargeT)
            put(KeyIndex.D4, Flick.Direction.DOWN,  AsciiKeyInfo.SmallT)
            put(KeyIndex.D4, Flick.Direction.LEFT,  AsciiKeyInfo.LargeU)
            put(KeyIndex.D4, Flick.Direction.LEFT,  AsciiKeyInfo.SmallU)
            put(KeyIndex.D4, Flick.Direction.UP,    AsciiKeyInfo.LargeV)
            put(KeyIndex.D4, Flick.Direction.UP,    AsciiKeyInfo.SmallV)
            put(KeyIndex.D4, Flick.Direction.RIGHT, AsciiKeyInfo.Tilda)
            put(KeyIndex.D4, Flick.Direction.RIGHT, AsciiKeyInfo.Grave)
            clear(KeyIndex.E4)
            put(KeyIndex.E4, Flick.Direction.NONE,  AsciiKeyInfo.Num9)
            put(KeyIndex.E4, Flick.Direction.DOWN,  AsciiKeyInfo.LargeW)
            put(KeyIndex.E4, Flick.Direction.DOWN,  AsciiKeyInfo.SmallW)
            put(KeyIndex.E4, Flick.Direction.LEFT,  AsciiKeyInfo.LargeX)
            put(KeyIndex.E4, Flick.Direction.LEFT,  AsciiKeyInfo.SmallX)
            put(KeyIndex.E4, Flick.Direction.UP,    AsciiKeyInfo.LargeY)
            put(KeyIndex.E4, Flick.Direction.UP,    AsciiKeyInfo.SmallY)
            put(KeyIndex.E4, Flick.Direction.RIGHT, AsciiKeyInfo.LargeZ)
            put(KeyIndex.E4, Flick.Direction.RIGHT, AsciiKeyInfo.SmallZ)
            clear(KeyIndex.F4)
            put(KeyIndex.F4, Flick.Direction.NONE,  AsciiKeyInfo.ParenRight)
            put(KeyIndex.F4, Flick.Direction.LEFT,  AsciiKeyInfo.CurlyRight)
            put(KeyIndex.F4, Flick.Direction.LEFT,  AsciiKeyInfo.SquareRight)
            put(KeyIndex.F4, Flick.Direction.LEFT,  AsciiKeyInfo.Greater)
        }
    }

    /**
     * Defines AsciiKeyInfo.UnCharKey(except directions, MoveHome/END, PageUp/END), KeyboardApp, and ModKeyInfo to keymap.
     */
    private fun generateUnCharKeymap() {
        keymap.apply {
            clear(KeyIndex.B1)
            put(KeyIndex.B1, Flick.Direction.NONE,  AsciiKeyInfo.Escape)
            put(KeyIndex.B1, Flick.Direction.UP,    TriggerKeyInfo.KeyboardApp)
            put(KeyIndex.B1, Flick.Direction.DOWN,  TriggerKeyInfo.KeyboardApp)
            clear(KeyIndex.F1)
            put(KeyIndex.F1, Flick.Direction.NONE,  AsciiKeyInfo.Tab)
            put(KeyIndex.F1, Flick.Direction.LEFT,  AsciiKeyInfo.ShiftTab)
            put(KeyIndex.F1, Flick.Direction.DOWN,  AsciiKeyInfo.Enter)
            put(KeyIndex.F1, Flick.Direction.UP,    AsciiKeyInfo.ShiftEnter)
            clear(KeyIndex.B2)
            put(KeyIndex.B2, Flick.Direction.NONE,  ModKeyInfo.Meta)
            put(KeyIndex.B2, Flick.Direction.RIGHT, ModKeyInfo.MetaLock)
            put(KeyIndex.B2, Flick.Direction.DOWN,  ModKeyInfo.Alt)
            put(KeyIndex.B2, Flick.Direction.UP,    ModKeyInfo.AltLock)
            clear(KeyIndex.F2)
            put(KeyIndex.F2, Flick.Direction.NONE,  AsciiKeyInfo.ForwardDelete)
            clear(KeyIndex.B3)
            put(KeyIndex.B3, Flick.Direction.NONE,  ModKeyInfo.Ctrl)
            put(KeyIndex.B3, Flick.Direction.RIGHT, ModKeyInfo.CtrlLock)
            put(KeyIndex.B3, Flick.Direction.DOWN,  ModKeyInfo.Alt)
            put(KeyIndex.B3, Flick.Direction.UP,    ModKeyInfo.AltLock)
            clear(KeyIndex.F3)
            put(KeyIndex.F3, Flick.Direction.NONE,  AsciiKeyInfo.BackDelete)
        }
    }

    /**
     * Defines Left, Right, Up, Down, MoveHome/END, and PageUp/END KeyInfo to keymap.
     * @param mode current arrow key mode
     */
    private fun generateArrowKeymap(mode: ArrowKey.Mode) {
        keymap.apply {
            clear(KeyIndex.C1)
            put(KeyIndex.C1, Flick.Direction.NONE, TriggerKeyInfo.ArrowKeyMode)
            when (mode) {
                ArrowKey.Mode.DEFAULT -> {
                    put(KeyIndex.C1, Flick.Direction.DOWN,  AsciiKeyInfo.Down)
                    put(KeyIndex.C1, Flick.Direction.LEFT,  AsciiKeyInfo.Left)
                    put(KeyIndex.C1, Flick.Direction.UP,    AsciiKeyInfo.Up)
                    put(KeyIndex.C1, Flick.Direction.RIGHT, AsciiKeyInfo.Right)
                }
                ArrowKey.Mode.PAGE_MOVE -> {
                    put(KeyIndex.C1, Flick.Direction.DOWN,  AsciiKeyInfo.PageDown)
                    put(KeyIndex.C1, Flick.Direction.LEFT,  AsciiKeyInfo.MoveHome)
                    put(KeyIndex.C1, Flick.Direction.UP,    AsciiKeyInfo.PageUp)
                    put(KeyIndex.C1, Flick.Direction.RIGHT, AsciiKeyInfo.MoveEnd)
                }
            }
        }
    }

    /**
     * Defines F1-F12 KeyInfo and TriggerKeyInfo to keymap.
     */
    private fun generateFnKeymap() {
        keymap.apply {
            clear(KeyIndex.A1)
            put(KeyIndex.A1, Flick.Direction.NONE,  AsciiKeyInfo.F1)
            put(KeyIndex.A1, Flick.Direction.RIGHT, AsciiKeyInfo.F2)
            put(KeyIndex.A1, Flick.Direction.RIGHT, AsciiKeyInfo.F3)
            put(KeyIndex.A1, Flick.Direction.UP,    TriggerKeyInfo.KeyboardLayout)
            put(KeyIndex.A1, Flick.Direction.DOWN,  TriggerKeyInfo.KeyboardLayout)
            clear(KeyIndex.G1)
            put(KeyIndex.G1, Flick.Direction.NONE,  AsciiKeyInfo.F1)
            put(KeyIndex.G1, Flick.Direction.LEFT,  AsciiKeyInfo.F2)
            put(KeyIndex.G1, Flick.Direction.LEFT,  AsciiKeyInfo.F3)
            put(KeyIndex.G1, Flick.Direction.UP,    TriggerKeyInfo.KeyboardLayout)
            put(KeyIndex.G1, Flick.Direction.DOWN,  TriggerKeyInfo.KeyboardLayout)
            clear(KeyIndex.A2)
            put(KeyIndex.A2, Flick.Direction.NONE,  AsciiKeyInfo.F4)
            put(KeyIndex.A2, Flick.Direction.RIGHT, AsciiKeyInfo.F5)
            put(KeyIndex.A2, Flick.Direction.RIGHT, AsciiKeyInfo.F6)
            put(KeyIndex.A2, Flick.Direction.UP,    TriggerKeyInfo.KeyboardLayout)
            put(KeyIndex.A2, Flick.Direction.DOWN,  TriggerKeyInfo.KeyboardLayout)
            clear(KeyIndex.G2)
            put(KeyIndex.G2, Flick.Direction.NONE,  AsciiKeyInfo.F4)
            put(KeyIndex.G2, Flick.Direction.LEFT,  AsciiKeyInfo.F5)
            put(KeyIndex.G2, Flick.Direction.LEFT,  AsciiKeyInfo.F6)
            put(KeyIndex.G2, Flick.Direction.UP,    TriggerKeyInfo.KeyboardLayout)
            put(KeyIndex.G2, Flick.Direction.DOWN,  TriggerKeyInfo.KeyboardLayout)
            clear(KeyIndex.A3)
            put(KeyIndex.A3, Flick.Direction.NONE,  AsciiKeyInfo.F7)
            put(KeyIndex.A3, Flick.Direction.RIGHT, AsciiKeyInfo.F8)
            put(KeyIndex.A3, Flick.Direction.RIGHT, AsciiKeyInfo.F9)
            put(KeyIndex.A3, Flick.Direction.UP,    TriggerKeyInfo.KeyboardLayout)
            put(KeyIndex.A3, Flick.Direction.DOWN,  TriggerKeyInfo.KeyboardLayout)
            clear(KeyIndex.G3)
            put(KeyIndex.G3, Flick.Direction.NONE,  AsciiKeyInfo.F7)
            put(KeyIndex.G3, Flick.Direction.LEFT,  AsciiKeyInfo.F8)
            put(KeyIndex.G3, Flick.Direction.LEFT,  AsciiKeyInfo.F9)
            put(KeyIndex.G3, Flick.Direction.UP,    TriggerKeyInfo.KeyboardLayout)
            put(KeyIndex.G3, Flick.Direction.DOWN,  TriggerKeyInfo.KeyboardLayout)
            clear(KeyIndex.A4)
            put(KeyIndex.A4, Flick.Direction.NONE,  AsciiKeyInfo.F10)
            put(KeyIndex.A4, Flick.Direction.RIGHT, AsciiKeyInfo.F11)
            put(KeyIndex.A4, Flick.Direction.RIGHT, AsciiKeyInfo.F12)
            put(KeyIndex.A4, Flick.Direction.UP,    TriggerKeyInfo.KeyboardLayout)
            put(KeyIndex.A4, Flick.Direction.DOWN,  TriggerKeyInfo.KeyboardLayout)
            clear(KeyIndex.G4)
            put(KeyIndex.G4, Flick.Direction.NONE,  AsciiKeyInfo.F10)
            put(KeyIndex.G4, Flick.Direction.LEFT,  AsciiKeyInfo.F11)
            put(KeyIndex.G4, Flick.Direction.LEFT,  AsciiKeyInfo.F12)
            put(KeyIndex.G4, Flick.Direction.UP,    TriggerKeyInfo.KeyboardLayout)
            put(KeyIndex.G4, Flick.Direction.DOWN,  TriggerKeyInfo.KeyboardLayout)
        }
    }
}
