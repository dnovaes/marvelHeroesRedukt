package com.dnovaes.marvelheroes.ui.components

import android.content.Context
import com.dnovaes.marvelheroes.R
import com.dnovaes.marvelheroes.extensions.color
import com.dnovaes.marvelheroes.extensions.dp
import com.dnovaes.marvelheroes.extensions.sp
import com.dnovaes.marvelheroes.ui.anvil.LinearLayoutComponent
import com.dnovaes.marvelheroes.ui.anvil.highOrderComponent
import com.dnovaes.marvelheroes.ui.anvil.onTextChangedInit
import com.dnovaes.marvelheroes.utils.Font.fontWeight
import com.dnovaes.marvelheroes.utils.FontWeight
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.alignLeft
import trikita.anvil.BaseDSL.alignRight
import trikita.anvil.BaseDSL.below
import trikita.anvil.BaseDSL.margin
import trikita.anvil.BaseDSL.padding
import trikita.anvil.BaseDSL.size
import trikita.anvil.BaseDSL.text
import trikita.anvil.BaseDSL.textSize
import trikita.anvil.BaseDSL.toRightOf
import trikita.anvil.DSL.backgroundColor
import trikita.anvil.DSL.backgroundResource
import trikita.anvil.DSL.editText
import trikita.anvil.DSL.id
import trikita.anvil.DSL.linearLayout
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.relativeLayout
import trikita.anvil.DSL.textColor
import trikita.anvil.DSL.textView
import trikita.anvil.DSL.view

inline fun mainTopBar(crossinline func: MainTopBar.() -> Unit) {
    highOrderComponent(func)
}

class MainTopBar(context: Context): LinearLayoutComponent(context) {

    companion object {
        val titleSearchLabelId = generateViewId()
        val titleMarvelLabelId = generateViewId()
    }

    override fun view() {
        size(MATCH, WRAP)
        orientation(VERTICAL)
        margin(context.dp(R.dimen.margin_large), 0)

        renderTitleField()
        renderSearchField()
    }

    private fun renderTitleField() {
        relativeLayout {
            size(MATCH, WRAP)
            margin(0, context.dp(R.dimen.margin_xx_default))

            textView {
                id(titleSearchLabelId)
                size(WRAP, WRAP)
                text(context.getString(R.string.title_search_label))
                textSize(context.sp(R.dimen.subheading_text_size))
                fontWeight(context, FontWeight.W700)
                textColor(context.color(R.color.colorPrimary))
                margin(0, 0, 0, context.dp(R.dimen.padding_title_search_label))
            }

            view {
                size(MATCH, context.dp(R.dimen.two_dip))
                alignLeft(titleSearchLabelId)
                alignRight(titleSearchLabelId)
                below(titleSearchLabelId)
                backgroundColor(context.color(R.color.colorPrimary))
            }

            textView {
                id(titleMarvelLabelId )
                size(WRAP, WRAP)
                toRightOf(titleSearchLabelId)
                text(context.getString(R.string.title_marvel_label))
                textSize(context.sp(R.dimen.subheading_text_size))
                fontWeight(context, FontWeight.W700)
                textColor(context.color(R.color.colorPrimary))
                margin(context.dp(R.dimen.padding_title_search_label), 0)
            }

            textView {
                size(MATCH, WRAP)
                toRightOf(titleMarvelLabelId)
                text(context.getString(R.string.title_content_label))
                textSize(context.sp(R.dimen.subheading_text_size))
                textColor(context.color(R.color.colorPrimary))
                fontWeight(context, FontWeight.W400)
            }
        }
    }

    private fun renderSearchField() {
        textView {
            size(MATCH, WRAP)
            text(context.getString(R.string.character_name_label))
            textSize(context.sp(R.dimen.subheading_text_size))
            textColor(context.color(R.color.colorPrimary))
            fontWeight(context, FontWeight.W400)
        }

        linearLayout {
            size(MATCH, WRAP)
            backgroundResource(R.drawable.background_rounded)
            margin(0, 0, 0, context.dp(R.dimen.margin_xx_default))

            editText {
                size(MATCH, WRAP)
                textSize(context.sp(R.dimen.subheading_text_size))
                textColor(context.color(R.color.colorPrimary))
                backgroundColor(android.R.color.transparent)
                padding(context.dp(R.dimen.padding_default))

                onTextChangedInit {
/*
                    if (it.isEmpty()) {
                        fieldContent = null
                        onFinishTyping?.invoke(it)
                    } else {
                        fieldContent = it
                        onFinishTyping?.invoke(it)
                    }
*/
                }
            }
        }
    }
}
