package com.jvillad.starwars.android.presentation.details.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jvillad.starwars.android.commons.extensions.asLiveData
import com.jvillad.starwars.android.commons.presentation.state.UIState
import com.jvillad.starwars.android.commons.presentation.state.UIStateProvider
import com.jvillad.starwars.android.domain.details.DetailsUseCases
import com.jvillad.starwars.android.presentation.details.state.DetailsUIState

/**
 * [ViewModel] for the Character details feature.
 *
 * @author juan.villada
 */
class CharacterDetailsViewModel @ViewModelInject constructor(
    private val characterDetailsUseCases: DetailsUseCases
) : ViewModel(), UIStateProvider<UIState<DetailsUIState>> {

    // UI state LiveData
    private val uiStateMutableLiveData = MutableLiveData<UIState<DetailsUIState>>()
    val uiStateLiveData = uiStateMutableLiveData.asLiveData()

    override fun updateUIState(newUIState: UIState<DetailsUIState>) {
        uiStateMutableLiveData.value = newUIState
    }
}
