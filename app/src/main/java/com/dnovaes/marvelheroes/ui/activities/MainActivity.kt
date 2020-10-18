package com.dnovaes.marvelheroes.ui.activities

import android.graphics.Color.BLACK
import com.dnovaes.marvelheroes.services.MarvelServiceApi
import com.dnovaes.marvelheroes.ui.activities.base.ReactiveActivity
import com.dnovaes.marvelheroes.ui.anvil.onClickInit
import trikita.anvil.BaseDSL.dip
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.backgroundColor
import trikita.anvil.DSL.imageView
import trikita.anvil.DSL.text
import trikita.anvil.DSL.textView

class MainActivity : ReactiveActivity() {

    var message: String = "Hello World"

    override fun content() {
        textView {
            text(message)
        }

        imageView {
            size(dip(200), dip(200))
            backgroundColor(BLACK)
            onClickInit {
                MarvelServiceApi.getHeroes (20, { apiResponse ->
                    message = "${apiResponse.data.results.count()} results found!"
                    layout?.render()
                }, { })
            }
        }
    }
}
