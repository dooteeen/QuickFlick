package com.rkbk60.quickflick

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.support.annotation.ColorInt
import android.support.v4.content.res.ResourcesCompat
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.rkbk60.quickflick.model.Flick
import org.jetbrains.anko.*

class PopupPreviewUi : AnkoComponent<Context> {
    val isInflated: Boolean
        get() = (::textViews.isInitialized && elevation > 0 && ::font.isInitialized)

    lateinit var textViews: Map<Flick.Direction, TextView>

    var elevation = 0f
        private set

    var color = ColorSet(Color.WHITE, Color.GRAY, Color.BLACK)
    lateinit var font: PreviewFont

    private val mainFontSize  = 8f // unit: sp
    private val subFontSize   = 5f // unit: sp
    private val paddingSize   = 4  // unit: dp
    private val radiusSize    = 4f // unit: dp
    private val elevationSize = 12 // unit: dp

    data class ColorSet(@ColorInt val mainText:   Int,
                        @ColorInt val subText:    Int,
                        @ColorInt val background: Int)

    interface PreviewFont {
        val roboto: Typeface?
        val robotoMonoNerd: Typeface?
    }

    fun createView(context: Context): View {
        return createView(context.UI {})
    }

    override fun createView(ui: AnkoContext<Context>): View {
        val preTextViews = mutableMapOf<Flick.Direction, TextView>()
        val view = ui.linearLayout {
            lateInitVars()

            customVerticalView {
                preTextViews[Flick.Direction.LEFT] = customTextView()
            }

            customVerticalView {
                preTextViews[Flick.Direction.UP]   = customTextView()
                preTextViews[Flick.Direction.NONE] = customTextView {
                    textSize = sp(mainFontSize).toFloat()
                    minWidth = 5 * dip(mainFontSize)
                }
                preTextViews[Flick.Direction.DOWN] = customTextView()
            }

            customVerticalView {
                preTextViews[Flick.Direction.RIGHT] = customTextView()
            }

            background = GradientDrawable().apply {
                setColor(this@PopupPreviewUi.color.background)
                cornerRadius = dip(radiusSize).toFloat()
            }
        }
        textViews = preTextViews.toMap()
        return view
    }

    private fun _LinearLayout.lateInitVars() {
        this@PopupPreviewUi.also {
            it.elevation = dip(it.elevationSize).toFloat()
            it.font = object : PreviewFont {
                override val roboto = Typeface.DEFAULT
                override val robotoMonoNerd = ResourcesCompat.getFont(context, R.font.roboto_mono_nerdfont)
            }
        }
    }

    private fun _LinearLayout.customTextView(
            init: (@AnkoViewDslMarker TextView).() -> Unit = {}): TextView = textView {
        textSize  = sp(subFontSize).toFloat()
        textColor = color.mainText
        typeface  = font.robotoMonoNerd
        gravity   = Gravity.CENTER
        padding   = dip(paddingSize)
        init()
    }.lparams {
        width        = wrapContent
        minimumWidth = 5 * dip(subFontSize)
        height       = matchParent
        gravity      = Gravity.CENTER
        weight       = 1f
    }

    private fun _LinearLayout.customVerticalView(
            init: (@AnkoViewDslMarker _LinearLayout).() -> Unit): LinearLayout {
        return verticalLayout(theme = 0, init = init).lparams {
            width   = matchParent
            height  = wrapContent
            gravity = Gravity.CENTER
        }
    }
}