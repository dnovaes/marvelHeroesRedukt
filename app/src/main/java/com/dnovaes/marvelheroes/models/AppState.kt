package com.dnovaes.marvelheroes.models

data class AppState(
    val syncRunning: Boolean = false,
    val stateLoaded: Boolean = false
)

