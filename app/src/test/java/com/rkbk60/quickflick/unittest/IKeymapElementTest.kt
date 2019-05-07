package com.rkbk60.quickflick.unittest

import com.rkbk60.quickflick.domain.ImmutableKeymapElement
import com.rkbk60.quickflick.domain.showCurrentInfo
import com.rkbk60.quickflick.model.AsciiKeyInfo
import com.rkbk60.quickflick.model.Flick
import com.rkbk60.quickflick.model.KeyInfo
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldEqual
import org.junit.Test

class IKeymapElementTest {
    private val empty1: ImmutableKeymapElement = mapOf()
    private val empty2: ImmutableKeymapElement = mapOf(
            Flick.Direction.NONE to listOf()
    )
    private val empty3: ImmutableKeymapElement = mapOf(
            Flick.Direction.NONE  to listOf(KeyInfo.Null),
            Flick.Direction.LEFT  to listOf(KeyInfo.Null, KeyInfo.Null),
            Flick.Direction.RIGHT to listOf(KeyInfo.Null, KeyInfo.Null),
            Flick.Direction.UP    to listOf(KeyInfo.Null, KeyInfo.Null),
            Flick.Direction.DOWN  to listOf(KeyInfo.Null, KeyInfo.Null)
    )

    private val charKey: ImmutableKeymapElement = mapOf(
            Flick.Direction.NONE  to listOf<KeyInfo>(AsciiKeyInfo.SmallC),
            Flick.Direction.LEFT  to listOf<KeyInfo>(AsciiKeyInfo.LargeL, AsciiKeyInfo.SmallL),
            Flick.Direction.RIGHT to listOf<KeyInfo>(AsciiKeyInfo.LargeR, AsciiKeyInfo.SmallR),
            Flick.Direction.UP    to listOf<KeyInfo>(AsciiKeyInfo.LargeU, AsciiKeyInfo.SmallU),
            Flick.Direction.DOWN  to listOf<KeyInfo>(AsciiKeyInfo.LargeD, AsciiKeyInfo.SmallD)
    )

    private val bracketKey: ImmutableKeymapElement = mapOf(
            Flick.Direction.NONE to listOf<KeyInfo>(AsciiKeyInfo.ParenLeft),
            Flick.Direction.LEFT to listOf<KeyInfo>(
                    AsciiKeyInfo.CurlyLeft,
                    AsciiKeyInfo.SquareLeft,
                    AsciiKeyInfo.Less
            )
    )

    private val deleteKey: ImmutableKeymapElement = mapOf(
            Flick.Direction.NONE to listOf<KeyInfo>(AsciiKeyInfo.ForwardDelete)
    )

    @Test
    fun `on Tap`() {
        val flick = Flick.NONE
        charKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.SmallC,
                Flick.Direction.LEFT  to AsciiKeyInfo.LargeL,
                Flick.Direction.RIGHT to AsciiKeyInfo.LargeR,
                Flick.Direction.UP    to AsciiKeyInfo.LargeU,
                Flick.Direction.DOWN  to AsciiKeyInfo.LargeD
        )
        bracketKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE to AsciiKeyInfo.ParenLeft,
                Flick.Direction.LEFT to AsciiKeyInfo.CurlyLeft
        )
        deleteKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE to AsciiKeyInfo.ForwardDelete
        )
        empty1.showCurrentInfo(flick).shouldBeEmpty()
        empty2.showCurrentInfo(flick).shouldBeEmpty()
        empty3.showCurrentInfo(flick).shouldBeEmpty()
    }

    @Test
    fun `on Left(1)`() {
        // should NOT has UP or DOWN
        val flick = onLeft(1)
        flick shouldEqual Flick(Flick.Direction.LEFT, 1)
        charKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.LargeL,
                Flick.Direction.LEFT  to AsciiKeyInfo.SmallL,
                Flick.Direction.RIGHT to AsciiKeyInfo.SmallC
        )
        bracketKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.CurlyLeft,
                Flick.Direction.LEFT  to AsciiKeyInfo.SquareLeft,
                Flick.Direction.RIGHT to AsciiKeyInfo.ParenLeft
        )
        deleteKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.ForwardDelete
        )
        empty1.showCurrentInfo(flick).shouldBeEmpty()
        empty2.showCurrentInfo(flick).shouldBeEmpty()
        empty3.showCurrentInfo(flick).shouldBeEmpty()
    }

    @Test
    fun `on Left(2)`() {
        // should NOT has UP or DOWN
        val flick = onLeft(2)
        flick shouldEqual Flick(Flick.Direction.LEFT, 2)
        charKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.SmallL,
                Flick.Direction.RIGHT to AsciiKeyInfo.LargeL
        )
        bracketKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.SquareLeft,
                Flick.Direction.LEFT  to AsciiKeyInfo.Less,
                Flick.Direction.RIGHT to AsciiKeyInfo.CurlyLeft
        )
        deleteKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.ForwardDelete
        )
        empty1.showCurrentInfo(flick).shouldBeEmpty()
        empty2.showCurrentInfo(flick).shouldBeEmpty()
        empty3.showCurrentInfo(flick).shouldBeEmpty()
    }

    @Test
    fun `on Left(8)`() {
        val flick = onLeft(8)
        flick shouldEqual Flick(Flick.Direction.LEFT, 8)
        charKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.SmallL,
                Flick.Direction.RIGHT to AsciiKeyInfo.LargeL
        )
        bracketKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.Less,
                Flick.Direction.RIGHT to AsciiKeyInfo.SquareLeft
        )
        deleteKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.ForwardDelete
        )
        empty1.showCurrentInfo(flick).shouldBeEmpty()
        empty2.showCurrentInfo(flick).shouldBeEmpty()
        empty3.showCurrentInfo(flick).shouldBeEmpty()
    }

    @Test
    fun `on Right(1)`() {
        // should NOT has UP or DOWN
        val flick = onRight(1)
        flick shouldEqual Flick(Flick.Direction.RIGHT, 1)
        charKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.LargeR,
                Flick.Direction.LEFT  to AsciiKeyInfo.SmallC,
                Flick.Direction.RIGHT to AsciiKeyInfo.SmallR
        )
        bracketKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.ParenLeft,
                Flick.Direction.LEFT  to AsciiKeyInfo.CurlyLeft
        )
        deleteKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.ForwardDelete
        )
        empty1.showCurrentInfo(flick).shouldBeEmpty()
        empty2.showCurrentInfo(flick).shouldBeEmpty()
        empty3.showCurrentInfo(flick).shouldBeEmpty()
    }

    @Test
    fun `on Right(2)`() {
        // should NOT has UP or DOWN
        val flick = onRight(2)
        flick shouldEqual Flick(Flick.Direction.RIGHT, 2)
        charKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.SmallR,
                Flick.Direction.LEFT  to AsciiKeyInfo.LargeR
        )
        bracketKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.ParenLeft,
                Flick.Direction.LEFT  to AsciiKeyInfo.CurlyLeft
        )
        deleteKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.ForwardDelete
        )
        empty1.showCurrentInfo(flick).shouldBeEmpty()
        empty2.showCurrentInfo(flick).shouldBeEmpty()
        empty3.showCurrentInfo(flick).shouldBeEmpty()
    }

    @Test
    fun `on Right(8)`() {
        // should be same to `on Right(2)`
        val flick = onRight(8)
        flick shouldEqual Flick(Flick.Direction.RIGHT, 8)
        charKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.SmallR,
                Flick.Direction.LEFT  to AsciiKeyInfo.LargeR
        )
        bracketKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.ParenLeft,
                Flick.Direction.LEFT  to AsciiKeyInfo.CurlyLeft
        )
        deleteKey.showCurrentInfo(flick) shouldEqual mapOf(
                Flick.Direction.NONE  to AsciiKeyInfo.ForwardDelete
        )
        empty1.showCurrentInfo(flick).shouldBeEmpty()
        empty2.showCurrentInfo(flick).shouldBeEmpty()
        empty3.showCurrentInfo(flick).shouldBeEmpty()
    }
}