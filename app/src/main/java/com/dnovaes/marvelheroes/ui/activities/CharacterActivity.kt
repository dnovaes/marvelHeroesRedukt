package com.dnovaes.marvelheroes.ui.activities

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.dnovaes.marvelheroes.models.Character
import com.dnovaes.marvelheroes.ui.activities.base.ReactiveActivity
import com.dnovaes.marvelheroes.ui.components.characterDetailView
import com.dnovaes.marvelheroes.ui.components.topBar.simpleTopBar
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.scrollView

class CharacterActivity : ReactiveActivity() {

    companion object {
        fun open(context: Context, id: Int, currentActivity: ReactiveActivity) {
            val options = ActivityOptions.makeSceneTransitionAnimation(currentActivity)
            context.startActivity(Intent(context, CharacterActivity::class.java).apply {
                putExtra("id", id)
            }, options.toBundle())
        }
    }

    lateinit var character: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val characterId = intent.extras?.getInt("id", 0) ?: 0
        character = state.characters[characterId] ?: return
    }

    override fun content() {
        simpleTopBar {
            title(character.name)
        }

        scrollView {
            size(MATCH, MATCH)
            characterDetailView {
                size(MATCH, MATCH)
                character(character)
            }
        }
    }
}
