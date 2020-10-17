package com.dnovaes.marvelheroes.ui.activities.base

import android.os.Bundle
import com.dnovaes.marvelheroes.MarvelHeroesApplication
import com.dnovaes.marvelheroes.models.AppState
import com.github.raulccabreu.redukt.states.StateListener

abstract class StateActivity : ReactiveActivity(), StateListener<AppState> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialState()
    }

    override fun onStart() {
        super.onStart()
        MarvelHeroesApplication.redukt.listeners.add(this)
        onChanged(state)
    }

    override fun onStop() {
        MarvelHeroesApplication.redukt.listeners.remove(this)
        super.onStop()
    }

    override fun hasChanged(newState: AppState, oldState: AppState): Boolean = newState != oldState

    abstract fun initialState()
}
