package com.dnovaes.marvelheroes.ui.components

import android.content.Context
import com.dnovaes.marvelheroes.R
import com.dnovaes.marvelheroes.extensions.color
import com.dnovaes.marvelheroes.extensions.dp
import com.dnovaes.marvelheroes.models.Character
import com.dnovaes.marvelheroes.ui.anvil.LinearLayoutComponent
import com.dnovaes.marvelheroes.ui.anvil.highOrderComponent
import com.dnovaes.marvelheroes.utils.GlideHelper.glideBitmap
import com.dnovaes.marvelheroes.utils.GlideHelper.replaceToHttps
import trikita.anvil.Anvil.currentView
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.margin
import trikita.anvil.BaseDSL.padding
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.backgroundColor
import trikita.anvil.DSL.imageView
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.view

inline fun characterDetailView(crossinline func: CharacterDetailView.() -> Unit) {
    highOrderComponent(func)
}

class CharacterDetailView(context: Context): LinearLayoutComponent(context) {

    private var character: Character? = null

    override fun view() {
        val character = character ?: return
        size(MATCH, MATCH)
        orientation(VERTICAL)

        renderDivider()
        renderExtendedImage(character)
        renderDetails(character)
    }

    private fun renderDivider() {
        view {
            size(MATCH, context.dp(R.dimen.divider_mainscreen))
            backgroundColor(context.color(R.color.colorPrimary))
        }
    }

    private fun renderExtendedImage(character: Character) {
        val thumbnail = character.thumbnail
        val filePath = replaceToHttps("${thumbnail.path}/portrait_uncanny.${thumbnail.extension}")
        imageView {
            size(WRAP, WRAP)
            glideBitmap(context, filePath, currentView())
            margin(context.dp(R.dimen.margin_large), 0)
            padding(0)
        }
    }

    private fun renderDetails(character: Character) {

    }

    fun character(character: Character) {
        this.character = character
        render()
    }
}
