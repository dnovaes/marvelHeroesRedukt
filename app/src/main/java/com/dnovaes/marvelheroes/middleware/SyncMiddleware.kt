package com.dnovaes.marvelheroes.middleware

import com.dnovaes.marvelheroes.actions.ActionCreator
import com.dnovaes.marvelheroes.actions.Actions.LOAD_STATE
import com.dnovaes.marvelheroes.actions.Actions.SAVE_CHARACTERS
import com.dnovaes.marvelheroes.models.AppState
import com.dnovaes.marvelheroes.services.MarvelServiceApi
import com.github.raulccabreu.redukt.actions.Action
import com.github.raulccabreu.redukt.middlewares.AfterAction
import com.github.raulccabreu.redukt.middlewares.BaseAnnotatedMiddleware
import com.github.raulccabreu.redukt.middlewares.BeforeAction
import timber.log.Timber

class SyncMiddleware : BaseAnnotatedMiddleware<AppState>() {

    @BeforeAction(LOAD_STATE)
    fun loadState(state: AppState, action: Action<*>) {
        if (state.characters.isNotEmpty()) return
        ActionCreator.instance.updateSync(true)

        MarvelServiceApi.getCharacters(0, { apiResponse ->
            val characters = apiResponse.data.results
            Timber.v("${apiResponse.data.results.count()} characters loaded!")
            ActionCreator.instance.saveCharacters(characters)
        }, { })
    }

    @AfterAction(SAVE_CHARACTERS)
    fun finishSyncAfterSave(state: AppState, action: Action<*>) {
        ActionCreator.instance.updateSync(false)
    }
}