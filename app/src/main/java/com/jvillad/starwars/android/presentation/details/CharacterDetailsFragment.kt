package com.jvillad.starwars.android.presentation.details

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.jvillad.starwars.android.R
import com.jvillad.starwars.android.commons.extensions.setOnDebouncedClickListener
import com.jvillad.starwars.android.presentation.navigation.state.SearchNavigationState
import com.jvillad.starwars.android.presentation.navigation.viewmodel.NavigationSharedViewModel
import kotlinx.android.synthetic.main.fragment_character_details.*

/**
 * Fragment for the Character details view.
 *
 * @author juan.villada
 */
class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details) {

    // ViewModel
    private val navigationSharedViewModel by activityViewModels<NavigationSharedViewModel>()

    // Navigation
    private val args: CharacterDetailsFragmentArgs by navArgs()
    private val characterUi by lazy { args.characterUI }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Character details
        characterNameTitleTextView.text = characterUi.name

        // On BackPressed
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navigateBackToSearchCharacters()
        }

        // Listeners
        backImageView.setOnDebouncedClickListener {
            navigateBackToSearchCharacters()
        }
    }

    private fun navigateBackToSearchCharacters() {
        navigationSharedViewModel.navigateBack(
            SearchNavigationState.SearchFragment
        )
    }
}
