package com.rkbk60.quickflick.domain

import com.rkbk60.quickflick.model.Flick
import com.rkbk60.quickflick.model.KeyInfo

typealias ImmutableKeymapElement = Map<Flick.Direction, List<KeyInfo>>

fun ImmutableKeymapElement.showCurrentInfo(flick: Flick): Map<Flick.Direction, KeyInfo> {
    val result = mutableMapOf<Flick.Direction, KeyInfo>()
    val direction = flick.direction
    if (flick == Flick.NONE) {
        enumValues<Flick.Direction>().map {
            val value = this[it]?.getOrNull(0)
            if (value is KeyInfo && value !== KeyInfo.Null) {
                result[it] = value
            }
        }
    } else if (this.keys == setOf(Flick.Direction.NONE)) {
        val current = this[Flick.Direction.NONE]?.getOrNull(0)
        if (current is KeyInfo && current !== KeyInfo.Null) {
            result[Flick.Direction.NONE] = current
        }
    } else if (this.containsKey(direction)) {
        val index = Math.min(flick.distance - 1, this[direction]?.lastIndex ?: 0)

        val current = this[direction]?.getOrNull(index)
        if (current is KeyInfo && current !== KeyInfo.Null) {
            result[Flick.Direction.NONE] = current
        }
        val greater = this[direction]?.getOrNull(index + 1)
        if (greater is KeyInfo && greater !== KeyInfo.Null) {
            result[direction] = greater
        }
        val less = if (index > 0) {
                       this[direction]?.getOrNull(index - 1)
                   } else {
                       this[Flick.Direction.NONE]?.getOrNull(0)
                   }
        if (less is KeyInfo && less !== KeyInfo.Null) {
            result[direction.inverted] = less
        }
    } else {
        // same to flick == Flick.Direction.NONE
        enumValues<Flick.Direction>().map {
            val value = this[it]?.getOrNull(0)
            if (value is KeyInfo && value !== KeyInfo.Null) {
                result[it] = value
            }
        }
    }
    return result.toMap()
}
