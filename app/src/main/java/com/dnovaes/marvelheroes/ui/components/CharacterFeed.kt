package com.dnovaes.marvelheroes.ui.components

import android.content.Context
import com.dnovaes.marvelheroes.R
import com.dnovaes.marvelheroes.extensions.color
import com.dnovaes.marvelheroes.extensions.dp
import com.dnovaes.marvelheroes.extensions.sp
import com.dnovaes.marvelheroes.ui.anvil.highOrderComponent
import com.dnovaes.marvelheroes.ui.components.base.FeedLayout
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.margin
import trikita.anvil.BaseDSL.padding
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.linearLayout
import trikita.anvil.DSL.orientation
import com.dnovaes.marvelheroes.models.Character
import com.dnovaes.marvelheroes.utils.Font.fontWeight
import com.dnovaes.marvelheroes.utils.FontWeight
import com.dnovaes.marvelheroes.utils.GlideHelper.glideBitmap
import trikita.anvil.Anvil.currentView
import trikita.anvil.BaseDSL.CENTER_VERTICAL
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.text
import trikita.anvil.BaseDSL.textSize
import trikita.anvil.BaseDSL.weight
import trikita.anvil.DSL.backgroundColor
import trikita.anvil.DSL.gravity
import trikita.anvil.DSL.imageView
import trikita.anvil.DSL.textColor
import trikita.anvil.DSL.textView
import trikita.anvil.DSL.view

inline fun characterFeed(crossinline func: CharacterFeed.() -> Unit) {
    highOrderComponent(func)
}

class CharacterFeed(context: Context): FeedLayout<Character>(context) {

    override fun renderCard(linePos: Int) {
        linearLayout {
            size(MATCH, 0)
            weight(0.25f)
            orientation(HORIZONTAL)
            margin(context.dp(R.dimen.margin_large), 0)
            gravity(CENTER_VERTICAL)

            val character = items.elementAt(linePos) as? Character
            renderThumbnail(character)
            renderCharacterInfo(character)
        }
        renderDivider()
    }

    private fun renderThumbnail(character: Character?) {
        val thumbnail = character?.thumbnail
        var filePath = "${thumbnail?.path}/portrait_medium.${thumbnail?.extension}"
        filePath = filePath.replace("http:", "https:")
        imageView {
            size(WRAP, MATCH)
            margin(context.dp(R.dimen.margin_l_medium))
            glideBitmap(context, filePath, currentView())
        }
    }

    private fun renderCharacterInfo(character: Character?) {
        textView {
            size(MATCH, MATCH)
            text(character?.name ?: context.getString(R.string.character_not_found))
            gravity(CENTER_VERTICAL)
            textSize(context.sp(R.dimen.body_size))
            fontWeight(this.context, FontWeight.W500)
            textColor(context.color(R.color.colorPrimary))
            padding(context.dp(R.dimen.padding_l_medium))
        }
    }

    private fun renderDivider() {
        view {
            size(MATCH, context.dp(R.dimen.one_dip))
            backgroundColor(context.color(R.color.colorPrimary))
        }
    }
}
