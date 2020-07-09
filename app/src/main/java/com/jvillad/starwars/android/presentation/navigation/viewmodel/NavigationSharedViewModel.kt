package com.jvillad.starwars.android.presentation.navigation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jvillad.starwars.android.commons.extensions.asLiveData
import com.jvillad.starwars.android.commons.presentation.state.NavigationProvider
import com.jvillad.starwars.android.presentation.navigation.state.NavigationState

/**
 * [ViewModel] for the fragment navigation.
 *
 * @author juan.villada
 */
class NavigationSharedViewModel @ViewModelInject constructor() : ViewModel(), NavigationProvider<NavigationState> {

    // Navigation state LiveData
    private val navigationStateMutableLiveData = MutableLiveData<Pair<NavigationState, NavigationState>>()
    val navigationStateLiveData = navigationStateMutableLiveData.asLiveData()

    override fun navigateTo(origin: NavigationState, destination: NavigationState) {
        navigationStateMutableLiveData.value = Pair(origin, destination)
    }
}
