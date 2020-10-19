package com.dnovaes.marvelheroes.ui.components.base

import android.content.Context
import com.dnovaes.marvelheroes.ui.anvil.LinearLayoutComponent
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.weightSum

abstract class FeedLayout<T>(context: Context): LinearLayoutComponent(context) {

    protected var items: List<T> = emptyList()
    protected var onClickItem: ((Int) -> Unit)? = null

    override fun view() {
        size(MATCH, MATCH)
        orientation(VERTICAL)
        weightSum(1f)

        if (items.isNotEmpty()) {
            for (i in 0 until items.count()) {
                renderCard(i)
            }
        }
    }

    abstract fun renderCard(linePos: Int)

    fun items(items: List<T>) {
        this.items = items
        hasChanged = true
    }

    fun onClickItem(callback: (Int)-> Unit) {
        this.onClickItem = callback
    }
}
