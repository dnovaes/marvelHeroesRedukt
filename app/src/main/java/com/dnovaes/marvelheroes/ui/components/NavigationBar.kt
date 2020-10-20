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
import trikita.anvil.BaseDSL.alignParentLeft
import trikita.anvil.BaseDSL.alignParentRight
import trikita.anvil.BaseDSL.margin
import trikita.anvil.BaseDSL.size
import trikita.anvil.BaseDSL.textSize
import trikita.anvil.BaseDSL.toLeftOf
import trikita.anvil.BaseDSL.toRightOf
import trikita.anvil.DSL.backgroundColor
import trikita.anvil.DSL.backgroundResource
import trikita.anvil.DSL.backgroundTintList
import trikita.anvil.DSL.gravity
import trikita.anvil.DSL.id
import trikita.anvil.DSL.imageView
import trikita.anvil.DSL.linearLayout
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.relativeLayout
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

    companion object {
        val arrowLeftId = generateViewId()
        val arrowRightId = generateViewId()
        val stepsGroupId = generateViewId()
    }

    override fun view() {
        size(MATCH, WRAP)
        orientation(VERTICAL)

        relativeLayout {
            size(MATCH, WRAP)

            renderArrow(true)
            renderStepsGroup()
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

    private fun renderStepsGroup() {
        linearLayout {
            id(stepsGroupId)
            toRightOf(arrowLeftId)
            toLeftOf(arrowRightId)
            size(MATCH, WRAP)
            orientation(HORIZONTAL)
            gravity(CENTER)

            renderStepButtons()
        }
    }

    private fun renderStepButtons() {
        val stepLimits = prepareSteps()
        for (stepShown in stepLimits.first..stepLimits.second) {
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
                textSize(context.sp(R.dimen.body_size))
                margin(context.dp(R.dimen.margin_default), context.dp(R.dimen.margin_l_medium))
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
            if (left) {
                id(arrowLeftId)
                backgroundResource(R.drawable.ic_arrow_left_play_24)
                alignParentLeft()
            } else {
                id(arrowRightId)
                backgroundResource(R.drawable.ic_arrow_right_play_24)
                alignParentRight()
            }
            margin(context.dp(R.dimen.margin_l_large), context.dp(R.dimen.margin_l_medium))
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

    private fun prepareSteps(): Pair<Int, Int> {
        val initialStep = if (currentStep>1)
            currentStep-1
        else
            currentStep
        val finalStep = if (currentStep>1)
            currentStep+1
        else
            currentStep+2
        return Pair(initialStep, finalStep)
    }

    fun currentStep(currentStep: Int) {
        this.currentStep = currentStep
        hasChanged = true
    }

    fun onNavigate(callback: (Int)-> Unit) {
        this.onNavigate = callback
    }
}
