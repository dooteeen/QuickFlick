package com.rkbk60.quickflick.domain

import android.view.Gravity
import com.os.operando.guild.kt.Quartet

class PreviewLocator {
    private data class Box(val x: Int, val y: Int, val w: Int, val h: Int) {
        val halfW = w / 2
        val halfH = h / 2
        val centerY = y + halfH

        constructor(): this(0, 0, 0, 0)

        fun isValid(): Boolean {
            return w > 0 || h > 0
        }

        fun isInside(pointX: Int, pointY: Int): Boolean {
            return pointX in x .. x + w && pointY in y .. y + h
        }

        override fun toString(): String = "x:$x y:$y w:$w h:$h"
    }

    private var keyboard = Box()
    private var key      = Box()
    private var preview  = Box()

    var isRightHandMode = true

    fun setKeyboardSize(absoluteX: Int, absoluteY: Int, width: Int, height: Int) {
        keyboard = Box(absoluteX, absoluteY, width, height)
    }

    fun setKeySize(relativeX: Int, relativeY: Int, width: Int, height: Int) {
        key = Box(relativeX, relativeY, width, height)
    }

    fun setPopupSize(width: Int, height: Int) {
        preview = Box(0, 0, width, height)
    }

    fun isValid(): Boolean {
        return keyboard.isValid() && key.isValid() && preview.isValid()
    }

    fun getLocation(tapX: Int, tapY: Int): Quartet<Int, Int, Int, Int> {
        val gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
        if (!isValid()) return Quartet(gravity, 0, 0, -1)

        // set default x & y
        val a = if (isRightHandMode) -1 else 1
        var x = a * keyboard.halfW / 2
        var y = keyboard.centerY - (1.2 * preview.halfH).toInt()
        var positionID = 0

        // if it's hard to see, move preview out of the way.
        val marginX = preview.halfW / 2
        val marginY = preview.halfH / 4
        var p = Box( // Preview With Margin
                keyboard.halfW + x - marginX - preview.halfW,
                y - marginY,
                preview.w + 2 * marginX,
                preview.h + 2 * marginY)
        val tooNearPreview = p.isInside(tapX, p.y)
        val tooNearEdge    = if (isRightHandMode) {
                                 Math.min(tapX, key.x) <= p.x + p.w
                             } else {
                                 Math.max(tapX, key.x + key.w) >= p.x
                             }
        if (tooNearPreview || tooNearEdge) {
            x *= -1
            y = Math.max(y - preview.halfH, marginY)
            positionID = 1
            p = p.copy(
                    x = keyboard.halfW + x - marginX - preview.halfW,
                    y = y - marginY)
            if (p.isInside(tapX, tapY)) {
                x *= -1
                positionID = 2
            }
        }

        return Quartet(gravity, x, keyboard.y + y, positionID)
    }
}