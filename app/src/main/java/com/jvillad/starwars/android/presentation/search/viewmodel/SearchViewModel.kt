package com.jvillad.starwars.android.presentation.search.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ServiceLifecycleDispatcher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jvillad.starwars.android.R
import com.jvillad.starwars.android.commons.data.Output
import com.jvillad.starwars.android.commons.extensions.asLiveData
import com.jvillad.starwars.android.commons.presentation.state.UIState
import com.jvillad.starwars.android.commons.presentation.state.UIStateProvider
import com.jvillad.starwars.android.domain.search.usecase.SearchUseCases
import com.jvillad.starwars.android.presentation.search.mapper.CharacterDomainToUI
import com.jvillad.starwars.android.presentation.search.model.CharacterUI
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
) : ViewModel(), UIStateProvider<UIState<SearchUIState>> {

    // UI state LiveData
    private val uiStateMutableLiveData = MutableLiveData<UIState<SearchUIState>>()
    val uiStateLiveData = uiStateMutableLiveData.asLiveData()

    // Variables
    private var characterSearchResults: List<CharacterUI> = listOf()

    override fun updateUIState(newUIState: UIState<SearchUIState>) {
        uiStateMutableLiveData.value = newUIState
    }

    fun searchCharacters(name: String) = viewModelScope.launch {
        Timber.d("searchCharacters")
        updateUIState(UIState.Loading())

        when (val output = searchUseCases.searchCharacters(name)) {
            is Output.Success -> {
                characterSearchResults = CharacterDomainToUI.map(output.data)
                updateUIState(UIState.Data(SearchUIState.SearchLoadedState(characterSearchResults)))
            }
            is Output.Error -> updateUIState(UIState.Error(R.string.general_error_message))
        }
    }

    fun clearCharactersSearch() {
        updateUIState(UIState.Data(SearchUIState.SearchClosedState))
    }
}
