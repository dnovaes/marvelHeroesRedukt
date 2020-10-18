package com.dnovaes.marvelheroes.ui.activities

import com.dnovaes.marvelheroes.ui.activities.base.ReactiveActivity
import com.dnovaes.marvelheroes.ui.components.charactersScreen
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.size

class MainActivity : ReactiveActivity() {

    override fun content() {
        charactersScreen {
            size(MATCH, MATCH)
        }
    }
}
