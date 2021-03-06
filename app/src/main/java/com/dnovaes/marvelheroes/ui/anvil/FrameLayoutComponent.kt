package com.dnovaes.marvelheroes.ui.anvil

import android.content.Context
import android.widget.FrameLayout
import trikita.anvil.Anvil

/**
 * We should not call the general Anvil.render() calls in every state changed.
 * Now each individual component should call ReactiveView#render()
 * when appropriated.
 *
 * Note: Recycler view doesn't work with individual render calls, so for screens
 * with recycler view, we should call the general Anvil.render() method.
 *
 * Remove native Anvil callbacks from the components, because those
 * callbacks calls Anvil.render() by default, and this can reduce the
 * performance a lot.
 */
abstract class FrameLayoutComponent(context: Context) : FrameLayout(context), Anvil.Renderable,
        RenderListener {

    private val layoutComponent = UiRenderHandler()
    protected var hasChanged = false

    override fun render() {
        layoutComponent.render(this)
    }

    public override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Anvil.mount(this, this)
    }

    public override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Anvil.unmount(this, false)
    }

    fun close() {
        Anvil.unmount(this, true)
    }

    fun renderIfChanged() {
        if(!this.hasChanged) return
        this.hasChanged = false
        render()
    }
}
