package com.dnovaes.marvelheroes.ui.components.topBar

import android.content.Context
import com.dnovaes.marvelheroes.R
import com.dnovaes.marvelheroes.extensions.color
import com.dnovaes.marvelheroes.extensions.dp
import com.dnovaes.marvelheroes.extensions.sp
import com.dnovaes.marvelheroes.ui.anvil.LinearLayoutComponent
import com.dnovaes.marvelheroes.ui.anvil.highOrderComponent
import com.dnovaes.marvelheroes.utils.Font.fontWeight
import com.dnovaes.marvelheroes.utils.FontWeight
import trikita.anvil.BaseDSL.CENTER
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.margin
import trikita.anvil.BaseDSL.size
import trikita.anvil.BaseDSL.text
import trikita.anvil.BaseDSL.textSize
import trikita.anvil.DSL.backgroundColor
import trikita.anvil.DSL.gravity
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.textColor
import trikita.anvil.DSL.textView
import trikita.anvil.DSL.view

inline fun simpleTopBar(crossinline func: SimpleTopBar.() -> Unit) {
    highOrderComponent(func)
}

class SimpleTopBar(context: Context): LinearLayoutComponent(context) {

    private var title: String = ""

    override fun view() {
        size(MATCH, WRAP)
        orientation(VERTICAL)

        renderTitle()
        renderDivider()
    }

    private fun renderTitle() {
        textView {
            size(MATCH, WRAP)
            text(title)
            textSize(context.sp(R.dimen.title_size))
            fontWeight(context, FontWeight.W700)
            textColor(context.color(R.color.colorPrimary))
            margin(context.dp(R.dimen.margin_large), context.dp(R.dimen.margin_xx_default))
            gravity(CENTER)
        }
    }

    private fun renderDivider() {
        view {
            size(MATCH, context.dp(R.dimen.divider_mainscreen))
            backgroundColor(context.color(R.color.colorPrimary))
        }
    }


    fun title(content: String) {
        if (title == content) return
        this.title = content
        hasChanged = true
    }
}
