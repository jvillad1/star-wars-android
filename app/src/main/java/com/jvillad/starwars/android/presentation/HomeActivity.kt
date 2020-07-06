package com.jvillad.starwars.android.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.jvillad.starwars.android.R
import com.jvillad.starwars.android.commons.extensions.observe
import com.jvillad.starwars.android.presentation.search.state.SearchNavigationState
import com.jvillad.starwars.android.presentation.search.viewmodel.SearchViewModel
import timber.log.Timber

/**
 * Activity for the Home / Search feature.
 *
 * @author juan.villada
 */
class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    // ViewModel
    private val searchViewModel by viewModels<SearchViewModel>()

    // Navigation
    private val searchNavController: NavController by lazy { findNavController(R.id.searchNavHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observe(searchViewModel.navigationStateLiveData, ::onSearchNavigationChange)
    }

    private fun onSearchNavigationChange(navigationEntry: Pair<SearchNavigationState, SearchNavigationState>) {
        Timber.d("onSearchNavigationChange")

        val (origin, destination) = navigationEntry
        when (destination) {
            is SearchNavigationState.SearchFragment -> showSearchFragment()
            is SearchNavigationState.CharacterDetailsFragment -> showCharacterDetailsFragment()
        }
    }

    private fun showSearchFragment() {
        Timber.d("showSearchFragment")
        searchNavController.navigate(R.id.searchFragment)
    }

    private fun showCharacterDetailsFragment() {
        Timber.d("showCharacterDetailsFragment")
        searchNavController.navigate(R.id.searchFragment)
    }
}
