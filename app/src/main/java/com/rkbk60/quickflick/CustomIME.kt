package com.rkbk60.quickflick

import android.annotation.SuppressLint
import android.content.Context
import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputMethodManager
import com.rkbk60.quickflick.domain.*
import com.rkbk60.quickflick.model.*

/**
 * main input method service
 */

class CustomIME : InputMethodService(), KeyboardView.OnKeyboardActionListener {
    private lateinit var keyboardView: CustomKeyboardView
    private val keyboardController by lazy { KeyboardController(this) }

    private val rServer by lazy { ResourceServer(this) }

    private val keymap = KeymapController()
    private val modStorage = ModKeyStorage()
    private lateinit var flickFactory: FlickFactory
    private lateinit var multiTapManager: MultiTapManager
    private lateinit var arrowKey: ArrowKey
    private val preview = PopupPreview()

    // repeating input helper for backspace/delete
    private var doRepeatingDelete = false
    private val deleteInputRunner = RepeatingInputRunner(inputAtCalling = false) { keyEventOrder ->
        sendModifiableKeys(currentInputConnection ?: return@RepeatingInputRunner, keyEventOrder)
        doRepeatingDelete = true
    }

    // current keyboard information
    private var isRight
        get() = rServer.keyboardIsRight.current
        set(value) { rServer.keyboardIsRight.current = value }
    private var footerLevel
        get()  = rServer.footerHeight.current.toInt()
        set(_) = Unit
    private var heightLevel
        get()  = rServer.keyboardHeight.current.toInt()
        set(_) = Unit

    // current action information
    private var tapX = -1
    private var tapY = -1
    private var lastAction = MotionEvent.ACTION_UP
    private var onPressCode = KeyIndex.NOTHING
    private var flick = Flick.NONE
    private var canInput = true

    private lateinit var indicatorFactory: IndicatorFactory

    private lateinit var editorInfo: EditorInfo

    @SuppressLint("InflateParams")
    override fun onCreateInputView(): View {
        keyboardView = layoutInflater.inflate(R.layout.keyboardview, null) as CustomKeyboardView
        return keyboardView.apply {
            setKeyboardWith(keyboardController, isRight, footerLevel, heightLevel, rServer.keyText)
            isPreviewEnabled = false
            setOnCloseListener {
                arrowKey.stopInput()
                deleteInputRunner.stopInput()
                preview.dismiss()
                lastAction = MotionEvent.ACTION_UP
                tapX = -1
                tapY = -1
                onPressCode = KeyIndex.NOTHING
                canInput = true
                flick = Flick.NONE
                indicateFlickState()
            }
            setOnKeyboardActionListener(this@CustomIME)
            setOnTouchListener Listener@ { _, event ->
                val x = event.x.toInt()
                val y = event.y.toInt()
                val action = event.action and MotionEvent.ACTION_MASK
                lastAction = action
                when (action) {
                    MotionEvent.ACTION_DOWN -> {
                        tapX = x
                        tapY = y
                        keyboard.keys.find {
                            it.isInside(x, y) && KeyIndex.isValid(it.codes[0])
                        }?.also {
                            val key = keymap.getKey(it.codes[0], Flick.NONE)
                            when (key) {
                                AsciiKeyInfo.ForwardDelete,
                                AsciiKeyInfo.BackDelete -> {
                                    deleteInputRunner.startInput(key, modStorage.toSet())
                                }
                            }
                        }
                        return@Listener false
                    }
                    MotionEvent.ACTION_POINTER_DOWN -> {
                        multiTapManager.addTapCount()
                        when {
                            multiTapManager.canCancelInput() -> {
                                arrowKey.stopInput()
                                tapX = -1
                                tapY = -1
                                canInput = false
                                onPressCode = KeyIndex.NOTHING
                                flick = Flick.NONE
                                preview.dismiss()
                            }
                            multiTapManager.canCancelFlick() -> {
                                arrowKey.stopInput()
                                tapX = x
                                tapY = y
                                flick = Flick.NONE
                                preview.show(Flick.NONE, x, y)
                            }
                        }
                        indicateFlickState()
                        return@Listener true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        flick = flickFactory.makeWith(tapX, tapY, x, y)
                        if (onPressCode == KeyIndex.INDEX_ARROW && arrowKey.mode == ArrowKey.Mode.DEFAULT) {
                            if (flick == Flick.NONE) {
                                arrowKey.stopInput()
                            } else {
                                val key = keymap.getKey(KeyIndex.INDEX_ARROW, flick)
                                if (key is AsciiKeyInfo.DirectionKey) {
                                    if (arrowKey.isStandby) {
                                        arrowKey.changeKeyInfo(key)
                                    } else {
                                        arrowKey.startInput(key, modStorage.toSet())
                                    }
                                }
                            }

                        }
                        if (!multiTapManager.canCancelInput()) {
                            preview.show(flick, x, y)
                        }
                        indicateFlickState()
                        return@Listener true
                    }
                    MotionEvent.ACTION_UP -> {
                        arrowKey.stopInput()
                        deleteInputRunner.stopInput()
                        preview.dismiss()
                        indicateFlickState()
                        multiTapManager.resetTapCount()
                        return@Listener false
                    }
                }
                return@Listener true
            }
        }
    }

    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
        editorInfo = info ?: EditorInfo()
        flickFactory = FlickFactory(rServer.thresholdX1.current,
                                    rServer.thresholdX2.current,
                                    rServer.thresholdY1.current,
                                    rServer.thresholdY2.current)
        multiTapManager = MultiTapManager(rServer.canCancelFlick.current,
                                          rServer.canCancelInput.current)
        indicatorFactory = IndicatorFactory(rServer.supplyIndicatorBackground())
        setInputView(onCreateInputView())
        indicateFlickState()
        arrowKey = ArrowKey { order ->
            val ic = currentInputConnection ?: return@ArrowKey
            if (order.mainKey is AsciiKeyInfo.DirectionKey) {
                sendModifiableKeys(ic, order)
            }
        }

        // keyboardView still not be inflated, so set observer to setup preview
        keyboardView.viewTreeObserver?.apply {
            addOnGlobalLayoutListener {
                preview.setup(keyboardView, rServer.supplyPreviewColorSet(), isRight)
            }
        }
    }

    override fun onPress(primaryCode: Int) {
        onPressCode = primaryCode
        canInput = KeyIndex.isValid(primaryCode)
        if (canInput) {
            preview.standby(
                    keyboardController.findKey(primaryCode) as Keyboard.Key,
                    keymap.getElement(primaryCode),
                    modStorage.toSet())
            preview.show(flick, tapX, tapY)
        }
        indicateFlickState()
    }

    override fun onRelease(primaryCode: Int) {
        arrowKey.stopInput()
        tapX = -1
        tapY = -1
        onPressCode = KeyIndex.NOTHING
        canInput = true
        flick = Flick.NONE
        preview.dismiss()
        indicateFlickState()
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        if (!canInput) {
            return
        }
        val key = keymap.getKey(onPressCode, flick)
        if (key is ModKeyInfo) {
            modStorage.update(key, isSubMod = false)
            keyboardController.updateModKeyFace(modStorage.toSet())
            keyboardView.invalidateAllKeys()
            return
        }
        if (key.mods.isNotEmpty()) {
            key.mods.map { modStorage.update(it, isSubMod = true) }
        }
        sendKey(key, modStorage.toSet())
    }

    private fun sendKey(key: KeyInfo, mods: Set<ModKeyInfo>) {
        if (key === AsciiKeyInfo.Enter && mods.isEmpty()) {
            val editorAction = editorInfo.actionId.and(
                    EditorInfo.IME_MASK_ACTION or EditorInfo.IME_FLAG_NO_ENTER_ACTION)
            when (editorAction) {
                EditorInfo.IME_ACTION_GO,
                EditorInfo.IME_ACTION_DONE,
                EditorInfo.IME_ACTION_NEXT,
                EditorInfo.IME_ACTION_SEARCH,
                EditorInfo.IME_ACTION_SEND -> {
                    currentInputConnection?.performEditorAction(editorAction)
                    return
                }
            }
        }

        if (key is AsciiKeyInfo.DirectionKey) {
            if (arrowKey.isStandby || lastAction == MotionEvent.ACTION_UP) {
                arrowKey.stopInput()
                return
            }
        }

        if (key === AsciiKeyInfo.ForwardDelete || key === AsciiKeyInfo.BackDelete) {
            if (doRepeatingDelete && lastAction == MotionEvent.ACTION_UP) {
                doRepeatingDelete = false
                deleteInputRunner.stopInput()
                return
            }
        }

        if (key is AsciiKeyInfo && key is AsciiKeyInfo.Modifiable) {
            val ic = currentInputConnection ?: return
            sendModifiableKeys(ic, KeyEventOrder(key, mods))
            modStorage.resetUnlessLock()
            keyboardController.updateModKeyFace(modStorage.toSet())
            keyboardView.invalidateAllKeys()
            return
        }

        if (key is AsciiKeyInfo.CharKey && key !is AsciiKeyInfo.Modifiable) {
            super.sendKeyChar(key.char)
            modStorage.resetUnlessLock()
            keyboardController.updateModKeyFace(modStorage.toSet())
            keyboardView.invalidateAllKeys()
            return
        }

        if (key === TriggerKeyInfo.ArrowKeyMode) {
            arrowKey.toggleMode()
            keymap.updateArrowKeymap(arrowKey.mode)
            keyboardController.updateArrowKeyFace(arrowKey.mode)
        }

        if (key === TriggerKeyInfo.KeyboardLayout) {
            isRight = !isRight
            setInputView(onCreateInputView())
            return
        }

        if (key === TriggerKeyInfo.KeyboardApp) {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showInputMethodPicker()
            return
        }
    }

    private fun sendModifiableKeys(ic: InputConnection, order: KeyEventOrder) {
        val time = System.currentTimeMillis()
        order.toKeyEventList(time).map { ic.sendKeyEvent(it) }
    }

    private fun indicateFlickState() {
        val key = keyboardView.keyboard.keys.first { it.codes[0] == KeyIndex.INDICATOR } ?: return
        indicatorFactory.also {
            it.enable = canInput
            it.isDuringInput = lastAction != MotionEvent.ACTION_UP
            it.left = key.x
            it.right = key.x + key.width
            it.top = key.y
            it.bottom = key.y + key.height
            it.direction = flick.direction
            it.currentDistance = flick.distance
            it.maxDistance = keymap.getMaxDistance(onPressCode, flick.direction)
        }
        keyboardView.apply {
            indicator = indicatorFactory.makeIndicator()
            invalidateKey(KeyIndex.INDICATOR)
        }
    }

    override fun swipeLeft() {}

    override fun swipeRight() {}

    override fun swipeUp() {}

    override fun swipeDown() {}

    override fun onText(text: CharSequence?) {}
}
