package com.dnovaes.marvelheroes.ui.components.base

import android.content.Context
import com.dnovaes.marvelheroes.R
import com.dnovaes.marvelheroes.extensions.color
import com.dnovaes.marvelheroes.extensions.dp
import com.dnovaes.marvelheroes.ui.activities.base.ReactiveActivity
import com.dnovaes.marvelheroes.ui.anvil.ReactiveRelativeComponent
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.below
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.backgroundColor
import trikita.anvil.DSL.id
import trikita.anvil.DSL.view
import java.lang.ref.WeakReference

abstract class ScreenLayout(context: Context): ReactiveRelativeComponent(context) {

    companion object {
        val mainTopBarId = generateViewId()
        val redDividerId = generateViewId()
    }

    protected var activity: WeakReference<ReactiveActivity>? = null

    override fun view() {
        size(MATCH, MATCH)

        renderTopBar()
        renderBody()
        renderFooter()
    }

    open fun renderTopBar() {
        renderRedDivider()
    }

    private fun renderRedDivider() {
        view {
            id(redDividerId)
            size(MATCH, context.dp(R.dimen.divider_mainscreen))
            below(mainTopBarId)
            backgroundColor(context.color(R.color.colorPrimary))
        }
    }

    abstract fun renderBody()

    abstract fun renderFooter()

    fun activity(activity: ReactiveActivity) {
        this.activity = WeakReference(activity)
    }
}
