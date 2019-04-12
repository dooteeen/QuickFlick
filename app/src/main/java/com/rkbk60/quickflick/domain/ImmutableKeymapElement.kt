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
                result.put(it, value)
            }
        }
    } else if (this.containsKey(direction)) {
        val index = flick.distance - 1
        val current = this[direction]?.getOrNull(index)
        if (current is KeyInfo && current !== KeyInfo.Null) {
            result.put(Flick.Direction.NONE, current)
        }
        val greater = this[direction]?.getOrNull(index + 1)
        if (greater is KeyInfo && greater !== KeyInfo.Null) {
            result.put(direction, greater)
        }
        val less = if (flick.distance > 1) {
                       this[direction]?.getOrNull(index - 1)
                   } else {
                       this[Flick.Direction.NONE]?.getOrNull(0)
                   }
        if (less is KeyInfo && less !== KeyInfo.Null) {
            result.put(direction.invert(), less)
        }
    } else {
        val center = this[Flick.Direction.NONE]?.getOrNull(0)
        if (center is KeyInfo && center !== KeyInfo.Null) {
            result.put(direction.invert(), center)
        }
    }
    return result.toMap()
}
