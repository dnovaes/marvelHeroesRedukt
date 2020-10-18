package com.dnovaes.marvelheroes.reducers

import com.dnovaes.marvelheroes.actions.Actions.SAVE_CHARACTERS
import com.dnovaes.marvelheroes.models.AppState
import com.dnovaes.marvelheroes.models.Character
import com.github.raulccabreu.redukt.actions.Reduce
import com.github.raulccabreu.redukt.reducers.BaseAnnotatedReducer

class CharacterReducer : BaseAnnotatedReducer<AppState>() {

    @Reduce(SAVE_CHARACTERS)
    fun saveCharacters(state: AppState, characters: List<Character>): AppState {
        val charactersMap = mutableMapOf<Int, Character>()
        characters.forEach { character ->
            charactersMap[character.id] = character
        }
        return state.copy(characters = LinkedHashMap(charactersMap))
    }
}
