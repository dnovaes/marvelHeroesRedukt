package com.dnovaes.marvelheroes.ui.components

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color.WHITE
import android.view.Gravity.CENTER
import com.dnovaes.marvelheroes.R
import com.dnovaes.marvelheroes.extensions.color
import com.dnovaes.marvelheroes.extensions.dp
import com.dnovaes.marvelheroes.extensions.sp
import com.dnovaes.marvelheroes.ui.anvil.LinearLayoutComponent
import com.dnovaes.marvelheroes.ui.anvil.highOrderComponent
import com.dnovaes.marvelheroes.ui.anvil.onClick
import com.dnovaes.marvelheroes.ui.anvil.onClickInit
import com.dnovaes.marvelheroes.utils.Font.fontWeight
import com.dnovaes.marvelheroes.utils.FontWeight
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.margin
import trikita.anvil.BaseDSL.size
import trikita.anvil.BaseDSL.textSize
import trikita.anvil.DSL.backgroundColor
import trikita.anvil.DSL.backgroundResource
import trikita.anvil.DSL.backgroundTintList
import trikita.anvil.DSL.gravity
import trikita.anvil.DSL.imageView
import trikita.anvil.DSL.linearLayout
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.text
import trikita.anvil.DSL.textColor
import trikita.anvil.DSL.textView
import trikita.anvil.DSL.view

inline fun navigationBar(crossinline func: NavigationBar.() -> Unit) {
    highOrderComponent(func)
}

class NavigationBar(context: Context): LinearLayoutComponent(context) {

    private var currentStep = 1
    private var onNavigate: ((Int) -> Unit)? = null

    override fun view() {
        size(MATCH, WRAP)
        orientation(VERTICAL)

        linearLayout {
            size(MATCH, WRAP)
            orientation(HORIZONTAL)
            gravity(CENTER)

            renderArrow(true)
            renderStepButtons()
            renderArrow(false)
        }
        renderBottomDivider()
    }

    private fun renderBottomDivider() {
        view {
            size(MATCH, context.dp(R.dimen.divider_footer))
            backgroundColor(context.color(R.color.colorPrimary))
        }
    }

    private fun renderStepButtons() {
        val initialStep = if (currentStep>1)
            currentStep-1
        else
            currentStep
        val finalStep = if (currentStep>1)
            currentStep+1
        else
            currentStep+2
        for (stepShown in initialStep..finalStep) {
            textView {
                size(context.dp(R.dimen.icon_medium), context.dp(R.dimen.icon_medium))
                backgroundResource(R.drawable.background_round_icon)
                if (currentStep == stepShown) {
                    backgroundTintList(ColorStateList.valueOf(context.color(R.color.colorPrimary)))
                    textColor(WHITE)
                } else {
                    backgroundTintList(ColorStateList.valueOf(WHITE))
                    textColor(context.color(R.color.colorPrimary))
                }
                text(stepShown.toString())
                textSize(context.sp(R.dimen.subheading_text_size))
                gravity(CENTER)
                fontWeight(context, FontWeight.W500)
                onClick {
                    onNavigate?.invoke(stepShown)
                    currentStep = stepShown
                    render()
                }
            }
        }
    }

    private fun renderArrow(left: Boolean) {
        imageView {
            size(context.dp(R.dimen.icon_medium), context.dp(R.dimen.icon_medium))
            if (left)
                backgroundResource(R.drawable.ic_arrow_left_play_24)
            else
                backgroundResource(R.drawable.ic_arrow_right_play_24)
            margin(context.dp(R.dimen.margin_large), context.dp(R.dimen.margin_x_medium))
            onClickInit {
                if (left && currentStep > 1) {
                    onNavigate?.invoke(currentStep-1)
                    currentStep-=1
                } else if (!left) {
                    onNavigate?.invoke(currentStep+1)
                    currentStep+=1
                }
                render()
            }
        }
    }

    fun onNavigate(callback: (Int)-> Unit) {
        this.onNavigate = callback
    }
}
