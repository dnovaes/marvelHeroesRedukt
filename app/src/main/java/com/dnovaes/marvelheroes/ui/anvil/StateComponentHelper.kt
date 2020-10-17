package com.dnovaes.marvelheroes.ui.anvil

import com.dnovaes.marvelheroes.MarvelHeroesApplication
import com.dnovaes.marvelheroes.models.AppState
import com.github.raulccabreu.redukt.states.StateListener

class StateComponentHelper {

    private var isRegisteredOnStateChange = false

    fun onFocusChanged(hasFocus: Boolean, render: RenderListener, listener: StateListener<AppState>,
                       actualState: AppState?, newState: AppState) {
        if (hasFocus) {
            onRegisterOnStateChange(listener)
            if (actualState == null || listener.hasChanged(newState, actualState)) {
                listener.onChanged(newState)
                render.render()
            }
        }
    }

    fun onRegisterOnStateChange(listener: StateListener<AppState>) {
        if (isRegisteredOnStateChange) return

        isRegisteredOnStateChange = true
        MarvelHeroesApplication.redukt.listeners.add(listener)
    }

    fun onUnregisterOnStateChange(listener: StateListener<AppState>) {
        isRegisteredOnStateChange = false
        MarvelHeroesApplication.redukt.listeners.remove(listener)
    }
}