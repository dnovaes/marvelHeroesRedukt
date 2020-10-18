package com.dnovaes.marvelheroes.ui.components

import android.content.Context
import com.dnovaes.marvelheroes.MarvelHeroesApplication
import com.dnovaes.marvelheroes.R
import com.dnovaes.marvelheroes.extensions.dp
import com.dnovaes.marvelheroes.models.AppState
import com.dnovaes.marvelheroes.ui.anvil.highOrderComponent
import com.dnovaes.marvelheroes.ui.components.base.ScreenLayout
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.indeterminate
import trikita.anvil.DSL.visibility

inline fun charactersScreen(crossinline func: CharactersScreen.() -> Unit) {
    highOrderComponent(func)
}

class CharactersScreen(context: Context): ScreenLayout(context) {

    override fun renderBody() {
        val state = MarvelHeroesApplication.redukt.state
        horizontalProgressBar {
            size(MATCH, context.dp(R.dimen.sync_bar))
            indeterminate(true)
            visibility(state.syncing)
        }

        characterFeed {
            size(MATCH, MATCH)
            items(state.characters.values.toList())
            renderIfChanged()
        }
    }

    override fun renderFooter() {

    }

    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        return newState.syncing != oldState.syncing ||
            oldState.characters != newState.characters
    }

    override fun onChanged(state: AppState) {
        render()
    }
}
