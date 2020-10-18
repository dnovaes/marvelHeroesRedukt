package com.dnovaes.marvelheroes.actions

import com.dnovaes.marvelheroes.MarvelHeroesApplication
import com.dnovaes.marvelheroes.actions.Actions.LOAD_STATE
import com.dnovaes.marvelheroes.actions.Actions.SAVE_CHARACTERS
import com.dnovaes.marvelheroes.models.Character
import com.github.raulccabreu.redukt.actions.Action

class ActionCreator private constructor() {

    private object Holder {
        val INSTANCE = ActionCreator()
    }

    companion object {
        val instance: ActionCreator by lazy { Holder.INSTANCE }
    }

    fun loadState() {
        asyncDispatch(Action<Any>(LOAD_STATE))
    }

    fun saveCharacters(content: List<Character>) {
        asyncDispatch(Action<Any>(SAVE_CHARACTERS, content))
    }

    private fun asyncDispatch(action: Action<*>) {
        MarvelHeroesApplication.redukt.dispatch(action, true)
    }

}
