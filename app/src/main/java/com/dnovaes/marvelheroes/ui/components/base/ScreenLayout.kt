package com.dnovaes.marvelheroes.ui.components.base

import android.content.Context
import android.graphics.Color.RED
import com.dnovaes.marvelheroes.R
import com.dnovaes.marvelheroes.extensions.dp
import com.dnovaes.marvelheroes.ui.anvil.ReactiveLinearComponent
import com.dnovaes.marvelheroes.ui.components.mainTopBar
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.backgroundColor
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.view

abstract class ScreenLayout(context: Context): ReactiveLinearComponent(context) {

    override fun view() {
        size(MATCH, MATCH)
        orientation(VERTICAL)

        renderTopBar()
        renderBody()
        renderFooter()
    }

    private fun renderTopBar() {
        mainTopBar {
            size(MATCH, WRAP)
        }
        renderRedDivider()
    }

    private fun renderRedDivider() {
        view {
            size(MATCH, context.dp(R.dimen.divider_mainscreen))
            backgroundColor(RED)
        }
    }

    abstract fun renderBody()

    abstract fun renderFooter()

}
