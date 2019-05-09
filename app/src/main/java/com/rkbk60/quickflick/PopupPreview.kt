package com.rkbk60.quickflick

import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.os.Build
import android.text.Html
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.PopupWindow
import com.rkbk60.quickflick.domain.ImmutableKeymapElement
import com.rkbk60.quickflick.domain.PreviewLocator
import com.rkbk60.quickflick.domain.showCurrentInfo
import com.rkbk60.quickflick.model.*
import org.jetbrains.anko.textColor
import org.jetbrains.anko.wrapContent

class PopupPreview {
    private val popup = PopupWindow().apply {
        width  = wrapContent
        height = wrapContent
        inputMethodMode = PopupWindow.INPUT_METHOD_NOT_NEEDED
        isClippingEnabled = true
        isFocusable = false
        isTouchable = false
    }

    private val ui = PopupPreviewUi()

    private var doneSetup = false
    private var doneStandby = false

    private lateinit var anchorView: View
    private val locator = PreviewLocator()

    private var keymap: ImmutableKeymapElement = mapOf()
    private var modSet: Set<ModKeyInfo> = setOf()
    private var lastPID = -1
    private var lastFlick = Flick.NONE
    private var flickCount = 0
    private var isRightHandMode = true

    fun setup(keyboardView: KeyboardView, colorSet: PopupPreviewUi.ColorSet, isRightHandMode: Boolean) {
        anchorView = keyboardView
        ui.color = colorSet
        this.isRightHandMode = isRightHandMode
        locator.isRightHandMode = isRightHandMode

        val topLeftKey     = keyboardView.keyboard.keys.find     { KeyIndex.isValid(it.codes[0]) }
        val bottomRightKey = keyboardView.keyboard.keys.findLast { KeyIndex.isValid(it.codes[0]) }
        val absoluteLocation = IntArray(2)
        keyboardView.getLocationInWindow(absoluteLocation)
        locator.setKeyboardSize(
                absoluteX = absoluteLocation[0] + (topLeftKey?.x ?: 0),
                absoluteY = absoluteLocation[1] + (topLeftKey?.y ?: 0),
                width     = bottomRightKey?.run { x + width }  ?: keyboardView.width,
                height    = bottomRightKey?.run { y + height } ?: keyboardView.height)

        if (!ui.isInflated) {
            popup.apply {
                contentView = ui.createView(keyboardView.context)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    elevation = ui.elevation
                }
            }
        }

        doneSetup = true
    }

    fun standby(key: Keyboard.Key, iKeymapElement: ImmutableKeymapElement, currentMods: Set<ModKeyInfo>) {
        locator.setKeySize(key.x, key.y, key.width, key.height)
        keymap = iKeymapElement
        modSet = currentMods
        lastPID = -1
        lastFlick = Flick.NONE
        flickCount = 0
        doneStandby = true
    }

    fun show(flick: Flick, tapX: Int, tapY: Int) {
        if (!(doneSetup && doneStandby)) {
            return
        }

        if (flick != lastFlick) {
            lastFlick = flick
            flickCount += 1
        }

        // set text to each TextViews
        val infoPackage = keymap.showCurrentInfo(flick).also {
            if (it.isEmpty()) return
        }
        ui.textViews.map { it.value.text = "" }
        enumValues<Flick.Direction>().map { direction ->
            ui.textViews[direction]?.also {
                val info = infoPackage[direction] ?: KeyInfo.Null
                val isMainView = direction == Flick.Direction.NONE
                if (isMainView) {
                    it.text = info.preview.main
                    it.textColor = ui.color.mainText
                } else {
                    it.text = info.preview.sub
                    it.textColor = ui.color.subText
                }
                it.rotationX = 0f
                it.rotationY = 0f
                it.typeface = ui.font.robotoMonoNerd
                when (info) {
                    TriggerKeyInfo.ArrowKeyMode -> {
                        if (flickCount > 1) {
                            it.text = info.preview.sub
                            it.textColor = ui.color.subText
                        }
                    }
                    TriggerKeyInfo.KeyboardLayout -> {
                        if (isRightHandMode) {
                            it.rotationY = 180f
                        }
                    }
                    AsciiKeyInfo.Escape,
                    AsciiKeyInfo.Space,
                    AsciiKeyInfo.MoveHome,
                    AsciiKeyInfo.MoveEnd,
                    AsciiKeyInfo.PageUp,
                    AsciiKeyInfo.PageDown,
                    is AsciiKeyInfo.FunctionKey -> {
                        it.typeface = ui.font.roboto
                    }
                    AsciiKeyInfo.Tab, AsciiKeyInfo.ShiftTab -> {
                        if (isMainView) {
                            it.typeface = ui.font.roboto
                        } else if (info.mods.contains(ModKeyInfo.Shift)) {
                            it.rotationY = 180f
                        }
                    }
                    AsciiKeyInfo.Enter, AsciiKeyInfo.ShiftEnter -> {
                        if (isMainView) {
                            it.typeface = ui.font.roboto
                        } else if (info.mods.contains(ModKeyInfo.Shift)) {
                            it.rotationX = 180f
                        }
                    }
                    AsciiKeyInfo.ForwardDelete -> {
                        it.rotationY = 180f
                    }
                    is ModKeyInfo -> {
                        if (isMainView) {
                            val state = modSet.find { mod -> mod.code == info.code }?.state
                                        ?: ModKeyInfo.State.OFF
                            val trueText = "${info.preview.main} ${info.updateFrom(state)}"
                            it.text = trueText
                            it.typeface = ui.font.roboto
                        } else if (info.lockable) {
                            val underlined = "<u>${info.preview.sub}</u>"
                            it.textColor = ui.color.mainText
                            it.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                Html.fromHtml(underlined, Html.FROM_HTML_MODE_LEGACY)
                            } else {
                                @Suppress("DEPRECATION")
                                Html.fromHtml(underlined)
                            }
                        }
                    }
                }
                // fix popup width
                if (info == KeyInfo.Null && direction in setOf(Flick.Direction.LEFT, Flick.Direction.RIGHT)) {
                    it.text = ui.textViews[direction.inverted]?.text
                    it.textColor = ui.color.background
                }
            }
        }

        // scale popup width/height
        popup.contentView.also {
            val spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            it.measure(spec, spec)
            locator.setPopupSize(it.measuredWidth, it.measuredHeight)
        }

        if (locator.isValid()) {
            val (gravity, x, y, positionID) = locator.getLocation(tapX, tapY)
            if (positionID != lastPID) {
                lastPID = positionID
                popup.dismiss()
            }
            popup.showAtLocation(anchorView, gravity, x, y)
        }
    }

    fun dismiss() {
        lastPID = -1
        lastFlick = Flick.NONE
        flickCount = 0
        if (popup.isShowing) popup.dismiss()
    }
}