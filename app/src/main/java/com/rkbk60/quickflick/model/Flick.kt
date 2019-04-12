package com.rkbk60.quickflick.model

/**
 * Multi-phases-fling object.
 * This class shows fling state with [direction] and [distance].
 * @param direction fling direction
 * @param distance fling distance level. it have to more than 0.
 */
data class Flick(val direction: Direction, val distance: Int) {
    /**
     * Elements that show fling direction.
     */
    enum class Direction {
        NONE, LEFT, RIGHT, UP, DOWN;

        fun invert(): Direction = when(this) {
            NONE -> NONE
            LEFT -> RIGHT
            RIGHT -> LEFT
            UP -> DOWN
            DOWN -> UP
        }
    }

    /**
     * flag that this [direction] and [distance] are valid values.
     * @return these value are valid, then returns true
     */
    val isValid = when (direction) {
        Direction.NONE -> distance == 0
        else -> distance > 0
    }

    companion object {
        val NONE = Flick(Direction.NONE, 0)
    }

    override fun toString(): String = "Flick($direction, $distance)"
}
