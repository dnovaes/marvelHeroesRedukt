package com.dnovaes.marvelheroes.middleware

import com.dnovaes.marvelheroes.actions.ActionCreator
import com.dnovaes.marvelheroes.actions.Actions.LOAD_CHARACTERS
import com.dnovaes.marvelheroes.models.AppState
import com.dnovaes.marvelheroes.services.MarvelServiceApi
import com.github.raulccabreu.redukt.actions.Action
import com.github.raulccabreu.redukt.middlewares.BaseAnnotatedMiddleware
import com.github.raulccabreu.redukt.middlewares.BeforeAction
import timber.log.Timber

class CharacterMiddleware : BaseAnnotatedMiddleware<AppState>() {

    @BeforeAction(LOAD_CHARACTERS)
    fun loadCharacters(state: AppState, action: Action<*>) {
        ActionCreator.instance.updateSync(true)
        val offset = action.payload as Int

        MarvelServiceApi.getCharacters(offset, { apiResponse ->
            val characters = apiResponse.data.results
            Timber.v("${apiResponse.data.results.count()} characters loaded!")
            ActionCreator.instance.saveCharacters(characters)
        }, { })
    }
}
