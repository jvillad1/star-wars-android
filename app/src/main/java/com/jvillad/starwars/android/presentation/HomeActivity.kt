package com.jvillad.starwars.android.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.jvillad.starwars.android.R
import com.jvillad.starwars.android.commons.extensions.observe
import com.jvillad.starwars.android.commons.presentation.ui.BaseActivity
import com.jvillad.starwars.android.presentation.details.CharacterDetailsFragmentDirections
import com.jvillad.starwars.android.presentation.navigation.state.DetailsNavigationState
import com.jvillad.starwars.android.presentation.navigation.state.NavigationState
import com.jvillad.starwars.android.presentation.navigation.state.SearchNavigationState
import com.jvillad.starwars.android.presentation.navigation.viewmodel.NavigationSharedViewModel
import com.jvillad.starwars.android.presentation.search.SearchFragmentDirections
import com.jvillad.starwars.android.presentation.search.model.CharacterUI
import timber.log.Timber

/**
 * Activity for the Home / Search feature.
 *
 * @author juan.villada
 */

class HomeActivity : BaseActivity(R.layout.activity_home) {

    // ViewModel
    private val navigationSharedViewModel by viewModels<NavigationSharedViewModel>()

    // Navigation
    private val searchNavController: NavController by lazy { findNavController(R.id.searchNavHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observe(navigationSharedViewModel.navigationStateLiveData, ::onNavigationChange)
    }

    private fun onNavigationChange(navigationEntry: Pair<NavigationState?, NavigationState>) {
        Timber.d("onSearchNavigationChange")

        val (origin, destination) = navigationEntry
        when (destination) {
            is SearchNavigationState.SearchFragment -> showSearchFragment()
            is DetailsNavigationState.CharacterDetailsFragment -> showCharacterDetailsFragment(destination.characterUI)
        }
    }

    private fun showSearchFragment() {
        Timber.d("showSearchFragment")
        searchNavController.navigate(CharacterDetailsFragmentDirections.actionCharacterDetailsFragmentToSearchFragment())
    }

    private fun showCharacterDetailsFragment(characterUI: CharacterUI) {
        Timber.d("showCharacterDetailsFragment")
        searchNavController.navigate(SearchFragmentDirections.actionSearchFragmentToCharacterDetailsFragment(characterUI))
    }
}
