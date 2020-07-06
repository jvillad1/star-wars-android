package com.jvillad.starwars.android.presentation.search.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jvillad.starwars.android.commons.data.Output
import com.jvillad.starwars.android.commons.extensions.asLiveData
import com.jvillad.starwars.android.commons.presentation.state.NavigationProvider
import com.jvillad.starwars.android.commons.presentation.state.UIState
import com.jvillad.starwars.android.commons.presentation.state.UIStateProvider
import com.jvillad.starwars.android.domain.search.usecase.SearchUseCases
import com.jvillad.starwars.android.presentation.search.model.CharacterUI
import com.jvillad.starwars.android.presentation.search.state.SearchNavigationState
import com.jvillad.starwars.android.presentation.search.state.SearchUIState
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * [ViewModel] for the Search feature.
 *
 * @author juan.villada
 */
class SearchViewModel @ViewModelInject constructor(
    private val searchUseCases: SearchUseCases
) : ViewModel(), NavigationProvider<SearchNavigationState>, UIStateProvider<UIState<SearchUIState>> {

    // Navigation state LiveData
    private val navigationStateMutableLiveData = MutableLiveData<Pair<SearchNavigationState, SearchNavigationState>>()
    val navigationStateLiveData = navigationStateMutableLiveData.asLiveData()

    // UI state LiveData
    private val uiStateMutableLiveData = MutableLiveData<UIState<SearchUIState>>()
    val uiStateLiveData = uiStateMutableLiveData.asLiveData()

    // Variables
    private var characters: List<CharacterUI> = listOf()
    private var characterSearchResults: List<CharacterUI> = listOf()

    override fun navigateTo(origin: SearchNavigationState, destination: SearchNavigationState) {
        updateUIState(UIState.Idle)
        navigationStateMutableLiveData.value = Pair(origin, destination)
    }

    override fun updateUIState(newUIState: UIState<SearchUIState>) {
        uiStateMutableLiveData.value = newUIState
    }

    fun searchCharacters(query: String) = viewModelScope.launch {
        Timber.d("searchRecipes")
        updateUIState(UIState.Loading())

        fun getQueryString() = "%$query%"

        when (val output = searchUseCases.searchCharacters(getQueryString())) {
            is Output.Success -> {
                characterSearchResults = output.data
                updateUIState(UIState.Data(SearchUIState.SearchLoadedState(characterSearchResults)))
            }
            is Output.Error -> updateUIState(UIState.Error())
        }
    }

    fun clearCharactersSearch() {
        updateUIState(UIState.Data(SearchUIState.SearchLoadedState(characters)))
    }
}
