package com.dnovaes.marvelheroes.reducers

import com.dnovaes.marvelheroes.actions.Actions.UPDATE_SYNC
import com.dnovaes.marvelheroes.models.AppState
import com.github.raulccabreu.redukt.actions.Reduce
import com.github.raulccabreu.redukt.reducers.BaseAnnotatedReducer

class SyncReducer : BaseAnnotatedReducer<AppState>() {

    @Reduce(UPDATE_SYNC)
    fun startSync(state: AppState, status: Boolean): AppState {
        return state.copy(syncing = status)
    }
}
