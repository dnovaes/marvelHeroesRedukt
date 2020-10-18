package com.dnovaes.marvelheroes.ui.components.base

import android.content.Context
import com.dnovaes.marvelheroes.R
import com.dnovaes.marvelheroes.extensions.dp
import com.dnovaes.marvelheroes.extensions.sp
import com.dnovaes.marvelheroes.ui.anvil.LinearLayoutComponent
import com.dnovaes.marvelheroes.ui.anvil.ReactiveLinearComponent
import com.dnovaes.marvelheroes.utils.Font.fontWeight
import com.dnovaes.marvelheroes.utils.FontWeight
import trikita.anvil.BaseDSL.CENTER
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.margin
import trikita.anvil.BaseDSL.padding
import trikita.anvil.BaseDSL.size
import trikita.anvil.BaseDSL.textSize
import trikita.anvil.DSL.gravity
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.text
import trikita.anvil.DSL.textView
import trikita.anvil.DSL.visibility

abstract class FeedLayout<T>(context: Context): LinearLayoutComponent(context) {

    protected var items: List<T> = emptyList()
    protected var onClickItem: ((Int) -> Unit)? = null

    override fun view() {
        size(MATCH, MATCH)
        orientation(VERTICAL)
        padding(0, context.dp(R.dimen.padding_default))

        textView {
            size(MATCH, WRAP)
            //text(context.getString(R.string.no_movies_found))
            textSize(context.sp(R.dimen.subheading_text_size))
            visibility(items.isEmpty())
            fontWeight(this.context, FontWeight.W400)
            gravity(CENTER)
            margin(0, context.dp(R.dimen.padding_default))
        }

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
