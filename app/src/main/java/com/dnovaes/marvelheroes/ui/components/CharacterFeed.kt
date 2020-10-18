package com.dnovaes.marvelheroes.ui.components

import android.content.Context
import com.dnovaes.marvelheroes.R
import com.dnovaes.marvelheroes.extensions.dp
import com.dnovaes.marvelheroes.ui.anvil.highOrderComponent
import com.dnovaes.marvelheroes.ui.components.base.FeedLayout
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.margin
import trikita.anvil.BaseDSL.padding
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.linearLayout
import trikita.anvil.DSL.orientation
import com.dnovaes.marvelheroes.models.Character
import trikita.anvil.BaseDSL.text
import trikita.anvil.DSL.textView

inline fun characterFeed(crossinline func: CharacterFeed.() -> Unit) {
    highOrderComponent(func)
}

class CharacterFeed(context: Context): FeedLayout<Character>(context) {

    override fun renderCard(linePos: Int) {
        linearLayout {
            size(MATCH, WRAP)
            orientation(HORIZONTAL)
            margin(context.dp(R.dimen.margin_large), 0)
            padding(context.dp(R.dimen.padding_default))

            renderPoster(linePos)
            renderCharacterInfo(linePos)
        }
    }

    private fun renderPoster(linePos: Int) {
/*
        imageView {
            size(WRAP, context.dp(R.dimen.movie_feed_element_image_height))
            glideBitmap(context, movies[linePos].poster, currentView())

            onClickInit {
                onClickMovie?.invoke(linePos)
            }
        }
*/
    }

    private fun renderCharacterInfo(linePos: Int) {
        val character = items.elementAt(linePos) as? Character
        textView {
            size(MATCH, WRAP)
            text(character?.name ?: context.getString(R.string.character_not_found))
        }
    }

}
