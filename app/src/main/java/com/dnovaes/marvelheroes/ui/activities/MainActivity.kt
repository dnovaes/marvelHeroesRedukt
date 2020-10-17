package com.dnovaes.marvelheroes.ui.activities

import com.dnovaes.marvelheroes.ui.activities.base.ReactiveActivity
import trikita.anvil.DSL.text
import trikita.anvil.DSL.textView

class MainActivity : ReactiveActivity() {

    override fun content() {
        textView {
            text("Hello World")
        }
    }
}
