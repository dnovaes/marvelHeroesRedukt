package com.dnovaes.marvelheroes.models

data class AppState(
    val characters: Map<Int, Character> = LinkedHashMap(),
    val syncRunning: Boolean = false,
    val stateLoaded: Boolean = false
)
