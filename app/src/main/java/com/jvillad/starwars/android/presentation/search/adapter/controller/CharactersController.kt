package com.jvillad.starwars.android.presentation.search.adapter.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.jvillad.starwars.android.presentation.search.adapter.model.characterItem
import com.jvillad.starwars.android.presentation.search.adapter.model.emptyCharactersSearchItem
import com.jvillad.starwars.android.presentation.search.adapter.model.searchInfoItem
import com.jvillad.starwars.android.presentation.search.model.CharacterUI
import com.jvillad.starwars.android.presentation.search.model.SearchDataWrapper

/**
 * Epoxy Controller Class for the Character list.
 *
 * @author juan.villada
 */
class CharactersController(
    private val itemListener: CharacterItemListener
) : TypedEpoxyController<SearchDataWrapper>() {

    override fun buildModels(searchScreenDataWrapper: SearchDataWrapper) = with(searchScreenDataWrapper) {
        if (characters.isNotEmpty()) {
            characters.forEach {
                characterItem {
                    id(it.name)
                    characterUI(it)
                    itemListener(itemListener)
                }
            }
        }

        if (showSearchInfoCard) {
            searchInfoItem {
                id(SEARCH_INFO_ID)
            }
        }

        if (showEmptyCharactersSearch) {
            emptyCharactersSearchItem {
                id(EMPTY_CHARACTERS_SEARCH_ID)
            }
        }
    }

    interface CharacterItemListener {
        fun onCharacterClicked(characterUI: CharacterUI)
    }

    companion object {
        private const val SEARCH_INFO_ID = "SEARCH_INFO_ID"
        private const val EMPTY_CHARACTERS_SEARCH_ID = "EMPTY_CHARACTERS_SEARCH_ID"
    }
}
