package com.jvillad.starwars.android.presentation.search

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jvillad.starwars.android.R
import com.jvillad.starwars.android.commons.extensions.hideKeyboard
import com.jvillad.starwars.android.commons.extensions.observeFragment
import com.jvillad.starwars.android.commons.presentation.state.UIState
import com.jvillad.starwars.android.commons.presentation.ui.BaseFragment
import com.jvillad.starwars.android.presentation.navigation.state.DetailsNavigationState
import com.jvillad.starwars.android.presentation.navigation.state.NavigationState
import com.jvillad.starwars.android.presentation.navigation.state.SearchNavigationState
import com.jvillad.starwars.android.presentation.navigation.viewmodel.NavigationSharedViewModel
import com.jvillad.starwars.android.presentation.search.adapter.controller.CharactersController
import com.jvillad.starwars.android.presentation.search.model.CharacterUI
import com.jvillad.starwars.android.presentation.search.model.SearchDataWrapper
import com.jvillad.starwars.android.presentation.search.state.SearchUIState
import com.jvillad.starwars.android.presentation.search.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import timber.log.Timber

/**
 * Fragment for the Search view.
 *
 * @author juan.villada
 */
class SearchFragment : BaseFragment(R.layout.fragment_search), CharactersController.CharacterItemListener {

    // ViewModel
    private val searchViewModel by viewModels<SearchViewModel>()
    private val navigationSharedViewModel by activityViewModels<NavigationSharedViewModel>()

    // Epoxy controller
    private val charactersController: CharactersController by lazy { CharactersController(this) }

    // Search text listener
    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            Timber.d("onQueryTextSubmit: $query")
            hideKeyboard()

            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            Timber.d("onQueryTextChange: $newText")

            if (newText.isEmpty()) {
                searchViewModel.clearCharactersSearch()
            } else {
                searchViewModel.searchCharacters(newText)
            }

            return true
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchView()
        setupRecyclerView()

        // Listeners
        characterSearchView.setOnQueryTextListener(queryTextListener)

        observeFragment(searchViewModel.uiStateLiveData, ::onUIStateChange)
    }

    private fun setupSearchView() {
        Timber.d("setupSearchView")
        val openSansRegularTypeface: Typeface = Typeface.createFromAsset(requireContext().assets, "fonts/open_sans_regular.ttf")
        (characterSearchView.findViewById(androidx.appcompat.R.id.search_src_text) as TextView).apply {
            typeface = openSansRegularTypeface
            textSize = 13F
        }
    }

    private fun setupRecyclerView() = charactersRecyclerView.apply {
        Timber.d("setupRecyclerView")
        layoutManager = LinearLayoutManager(context)
        setController(charactersController)
        charactersController.setData(SearchDataWrapper(showSearchInfoCard = true))
    }

    private fun onUIStateChange(uiState: UIState<SearchUIState>) = when (uiState) {
        is UIState.Loading -> showLoading(uiState.message)
        is UIState.Data -> showData(uiState.data)
        is UIState.Error -> {
            // TODO: Create ErrorBanner component
            Timber.d("showErrorBanner")
        }
    }

    private fun showLoading(message: Int?) {
        Timber.d("showData")

        charactersController.setData(SearchDataWrapper())
        showLoading(message?.let { getString(it) })
    }

    private fun showData(searchUIState: SearchUIState) {
        Timber.d("showData")

        hideLoading()
        when (searchUIState) {
            is SearchUIState.SearchLoadedState -> showCharacterSearchResults(searchUIState.characters)
            is SearchUIState.SearchClosedState -> {
                charactersController.setData(SearchDataWrapper(showSearchInfoCard = true))
            }
        }
    }

    private fun showCharacterSearchResults(characterSearchResults: List<CharacterUI>) {
        Timber.d("showCharacterSearchResults")

        if (characterSearchResults.isNotEmpty()) {
            charactersController.setData(SearchDataWrapper(characterSearchResults))
        } else {
            charactersController.setData(SearchDataWrapper(showEmptyCharactersSearch = true))
        }
    }

    override fun onCharacterClicked(characterUI: CharacterUI) {
        hideKeyboard()
        // TODO: Navigate to the CharacterDetailsFragment
        navigationSharedViewModel.navigateTo(
            SearchNavigationState.SearchFragment,
            DetailsNavigationState.CharacterDetailsFragment
        )
    }
}
