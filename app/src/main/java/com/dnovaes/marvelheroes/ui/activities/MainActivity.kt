package com.dnovaes.marvelheroes.ui.activities

import android.graphics.Color.BLACK
import com.dnovaes.marvelheroes.models.AppState
import com.dnovaes.marvelheroes.ui.activities.base.StateActivity
import com.dnovaes.marvelheroes.ui.anvil.onClickInit
import trikita.anvil.BaseDSL.dip
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.backgroundColor
import trikita.anvil.DSL.imageView
import trikita.anvil.DSL.text
import trikita.anvil.DSL.textView

class MainActivity : StateActivity() {

    var message: String = "Hello World"

    override fun initialState() { }

    override fun content() {
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
        return newState.characters != oldState.characters
    }

    override fun onChanged(state: AppState) {
        val charsLoaded = state.characters.count()
        message = if (charsLoaded == 0)
            "No characters found or still loading"
        else
            "Characters loaded! -> ${state.characters.count()}"
        layout?.render()
    }
}
