package com.dnovaes.marvelheroes.ui.components

import android.content.Context
import com.dnovaes.marvelheroes.MarvelHeroesApplication
import com.dnovaes.marvelheroes.R
import com.dnovaes.marvelheroes.extensions.dp
import com.dnovaes.marvelheroes.models.AppState
import com.dnovaes.marvelheroes.ui.anvil.highOrderComponent
import com.dnovaes.marvelheroes.ui.components.base.ScreenLayout
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.above
import trikita.anvil.BaseDSL.alignParentBottom
import trikita.anvil.BaseDSL.below
import trikita.anvil.BaseDSL.init
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.id
import trikita.anvil.DSL.indeterminate
import trikita.anvil.DSL.visibility

inline fun charactersScreen(crossinline func: CharactersScreen.() -> Unit) {
    highOrderComponent(func)
}

class CharactersScreen(context: Context): ScreenLayout(context) {

    companion object {
        val characterFeedId = generateViewId()
        val navigationFooterId = generateViewId()
    }

    override fun renderBody() {
        val state = MarvelHeroesApplication.redukt.state
        horizontalProgressBar {
            size(MATCH, context.dp(R.dimen.sync_bar))
            indeterminate(true)
            below(redDividerId)
            visibility(state.syncing)
        }

        characterFeed {
            id(characterFeedId)
            size(MATCH, MATCH)
            below(redDividerId)
            above(navigationFooterId)
            items(state.characters.values.toList())
            renderIfChanged()
        }
    }

    override fun renderFooter() {
        navigationBar {
            id(navigationFooterId)
            size(MATCH, WRAP)
            alignParentBottom()
            init {
                onNavigate { }
            }
            renderIfChanged()
        }
    }

    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        return newState.syncing != oldState.syncing ||
            oldState.characters != newState.characters
    }

    override fun onChanged(state: AppState) {
        render()
    }
}
