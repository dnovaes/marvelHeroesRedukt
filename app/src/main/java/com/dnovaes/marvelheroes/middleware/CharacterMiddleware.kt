package com.dnovaes.marvelheroes.middleware

import com.dnovaes.marvelheroes.actions.ActionCreator
import com.dnovaes.marvelheroes.actions.Actions.LOAD_CHARACTERS
import com.dnovaes.marvelheroes.models.AppState
import com.dnovaes.marvelheroes.models.Character
import com.dnovaes.marvelheroes.models.marvelApi.ServerResponse
import com.dnovaes.marvelheroes.payloads.CharacterPayload
import com.dnovaes.marvelheroes.services.MarvelServiceApi
import com.github.raulccabreu.redukt.actions.Action
import com.github.raulccabreu.redukt.middlewares.BaseAnnotatedMiddleware
import com.github.raulccabreu.redukt.middlewares.BeforeAction
import timber.log.Timber

class CharacterMiddleware : BaseAnnotatedMiddleware<AppState>() {

    @BeforeAction(LOAD_CHARACTERS)
    fun loadCharacters(state: AppState, action: Action<*>) {
        ActionCreator.instance.updateSync(true)
        val payload = action.payload as? CharacterPayload ?: return

        if (payload.searchFilter.isEmpty()) {
            MarvelServiceApi.getCharacters(payload.offset, { apiResponse ->
                saveCharactersResponse(apiResponse)
            }, { })
        } else {
            MarvelServiceApi.getCharactersStartingWith(payload.offset, payload.searchFilter,
                { apiResponse ->
                val characters = apiResponse.data.results
                Timber.v("${apiResponse.data.results.count()} characters loaded!")
                ActionCreator.instance.saveCharacters(characters)
            }, { })
        }
    }

    private fun saveCharactersResponse(apiResponse: ServerResponse<Character>) {
        val characters = apiResponse.data.results
        Timber.v("${apiResponse.data.results.count()} characters loaded!")
        ActionCreator.instance.saveCharacters(characters)
    }
}
