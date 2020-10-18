package com.dnovaes.marvelheroes.ui.activities

import android.graphics.Color.BLACK
import com.dnovaes.marvelheroes.R
import com.dnovaes.marvelheroes.extensions.dp
import com.dnovaes.marvelheroes.models.AppState
import com.dnovaes.marvelheroes.ui.activities.base.StateActivity
import com.dnovaes.marvelheroes.ui.anvil.onClickInit
import com.dnovaes.marvelheroes.ui.components.horizontalProgressBar
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.dip
import trikita.anvil.BaseDSL.size
import trikita.anvil.BaseDSL.visibility
import trikita.anvil.DSL.backgroundColor
import trikita.anvil.DSL.imageView
import trikita.anvil.DSL.indeterminate
import trikita.anvil.DSL.text
import trikita.anvil.DSL.textView

class MainActivity : StateActivity() {

    var message: String = "Hello World"

    override fun initialState() { }

    override fun content() {
        horizontalProgressBar {
            size(MATCH, this.dp(R.dimen.sync_bar))
            indeterminate(true)
            visibility(state.syncing)
        }

        textView {
            text(message)
        }

        imageView {
            size(dip(200), dip(200))
            backgroundColor(BLACK)
            onClickInit {
            }
        }
    }

    override fun hasChanged(newState: AppState, oldState: AppState): Boolean {
        return newState.syncing != oldState.syncing
    }

    override fun onChanged(state: AppState) {
        val charsLoaded = state.characters.count()
        message = if (charsLoaded == 0)
            "No characters found or still loading: ${state.syncing}"
        else
            "Characters loaded! -> ${state.characters.count()}"
        layout?.render()
    }
}
