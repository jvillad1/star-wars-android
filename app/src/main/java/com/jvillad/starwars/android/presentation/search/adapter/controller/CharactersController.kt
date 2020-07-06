package com.jvillad.starwars.android.presentation.search.adapter.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.jvillad.starwars.android.presentation.search.adapter.model.characterItem
import com.jvillad.starwars.android.presentation.search.model.CharacterUI

/**
 * Epoxy Controller Class for the Character list.
 *
 * @author juan.villada
 */
class CharactersController(
    private val itemListener: CharacterItemListener
) : TypedEpoxyController<List<CharacterUI>>() {

    override fun buildModels(characters: List<CharacterUI>) {
        characters.forEach {
            characterItem {
                id(it.name)
                characterUI(it)
                itemListener(itemListener)
            }
        }
    }

    interface CharacterItemListener {
        fun onCharacterClicked(characterUI: CharacterUI)
    }
}
