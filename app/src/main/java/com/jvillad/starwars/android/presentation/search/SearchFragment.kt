package com.jvillad.starwars.android.presentation.search

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.ViewStub
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jvillad.starwars.android.R
import com.jvillad.starwars.android.commons.extensions.*
import com.jvillad.starwars.android.commons.presentation.state.UIState
import com.jvillad.starwars.android.commons.presentation.ui.BaseFragment
import com.jvillad.starwars.android.presentation.search.adapter.controller.CharactersController
import com.jvillad.starwars.android.presentation.search.model.CharacterUI
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
    private val searchViewModel by activityViewModels<SearchViewModel>()

    // Epoxy controller
    private val charactersController: CharactersController by lazy { CharactersController(this) }

    // No Results found
    private lateinit var noResultsViewStub: ViewStub
    private var noResultsInflated: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noResultsViewStub = view.findViewById(R.id.emptyCharactersSearchViewStub)
        setupSearchView()
        setupRecyclerView()

        // Listeners
        characterSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
        })

        observeFragment(searchViewModel.uiStateLiveData, ::onUIStateChange)
    }

    private fun setupSearchView() {
        Timber.d("setupSearchView")
        val face: Typeface = Typeface.createFromAsset(requireContext().assets, "fonts/open_sans_regular.ttf")
        (characterSearchView.findViewById<View>(androidx.appcompat.R.id.search_src_text) as TextView).apply {
            typeface = face
            textSize = 13F
        }
    }

    private fun setupRecyclerView() = charactersRecyclerView.apply {
        Timber.d("setupRecyclerView")
        layoutManager = LinearLayoutManager(context)
        setController(charactersController)
    }

    private fun onUIStateChange(uiState: UIState<SearchUIState>) = when (uiState) {
        is UIState.Loading -> showLoading(uiState.message?.let { getString(it) })
        is UIState.Data -> showData(uiState.data)
        is UIState.Error -> {
            // TODO: Create ErrorBanner component
            Timber.d("showErrorBanner")
        }
        else -> {
            // NO-OP
        }
    }

    private fun showData(searchUIState: SearchUIState) {
        Timber.d("showData")

        hideLoading()
        when (searchUIState) {
            is SearchUIState.SearchLoadedState -> showCharacterSearchResults(searchUIState.characters)
        }
    }

    private fun showCharacterSearchResults(characterSearchResults: List<CharacterUI>) {
        Timber.d("showCharacterSearchResults")
        if (characterSearchResults.isNotEmpty()) {
            noResultsInflated?.gone()
            charactersRecyclerView.visible()
            charactersController.setData(characterSearchResults)
        } else {
            if (noResultsInflated == null) {
                noResultsInflated = noResultsViewStub.inflate()
            }

            charactersRecyclerView.invisible()
            charactersController.setData(listOf())
            noResultsInflated?.visible()
        }
    }

    override fun onCharacterClicked(characterUI: CharacterUI) {
        TODO("Not yet implemented")
    }
}
