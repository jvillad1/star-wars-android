package com.jvillad.starwars.android.presentation

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jvillad.starwars.android.R
import com.jvillad.starwars.android.presentation.search.viewmodel.SearchViewModel

/**
 * Activity for the Home / Search feature.
 *
 * @author juan.villada
 */
class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    // ViewModel
    private val searchViewModel by viewModels<SearchViewModel>()
}
