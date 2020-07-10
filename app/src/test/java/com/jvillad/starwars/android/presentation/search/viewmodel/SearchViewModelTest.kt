package com.jvillad.starwars.android.presentation.search.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jvillad.starwars.android.R
import com.jvillad.starwars.android.commons.data.Output
import com.jvillad.starwars.android.commons.presentation.state.UIState
import com.jvillad.starwars.android.domain.search.model.Character
import com.jvillad.starwars.android.domain.search.usecase.SearchUseCases
import com.jvillad.starwars.android.presentation.search.mapper.CharacterDomainToUI
import com.jvillad.starwars.android.presentation.search.state.SearchUIState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Test class for [SearchViewModel].
 *
 * @author juan.villada
 */
@RunWith(JUnit4::class)
class SearchViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var searchUseCases: SearchUseCases

    lateinit var searchViewModel: SearchViewModel

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)

        searchViewModel = SearchViewModel(searchUseCases)
        searchViewModel.uiStateLiveData.observeForever {}
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Success with SearchLoadedState when searchCharacters`() {
        // Given
        val listOfCharacters = listOf(
            Character(CHARACTER_1_NAME, CHARACTER_1_BIRTH_YEAR, CHARACTER_1_GENDER, ""),
            Character(CHARACTER_2_NAME, CHARACTER_2_BIRTH_YEAR, CHARACTER_2_GENDER, ""),
            Character(CHARACTER_3_NAME, CHARACTER_3_BIRTH_YEAR, CHARACTER_3_GENDER, "")
        )

        coEvery { searchUseCases.searchCharacters(SEARCH_SUCCESS_QUERY) } returns Output.success(listOfCharacters)

        // When
        searchViewModel.searchCharacters(SEARCH_SUCCESS_QUERY)

        // Then
        assert(searchViewModel.uiStateLiveData.value != null)

        when (val uiState = searchViewModel.uiStateLiveData.value) {
            is UIState.Data -> assert(uiState.data == SearchUIState.SearchLoadedState(CharacterDomainToUI.map(listOfCharacters)))
        }
    }

    @Test
    fun `Error with null message when searchCharacters`() {
        // Given
        coEvery { searchUseCases.searchCharacters(SEARCH_ERROR_QUERY) } returns Output.error("Error retrieving the Characters Search")

        // When
        searchViewModel.searchCharacters(SEARCH_ERROR_QUERY)

        // Then
        assert(searchViewModel.uiStateLiveData.value != null)

        when (val uiState = searchViewModel.uiStateLiveData.value) {
            is UIState.Error -> assert(uiState.message == R.string.general_error_message)
        }
    }

    @Test
    fun `SearchClosedState when clearCharactersSearch`() {
        // Given

        // When
        searchViewModel.uiStateLiveData.observeForever {}
        searchViewModel.clearCharactersSearch()

        // Then
        assert(searchViewModel.uiStateLiveData.value != null)

        when (val uiState = searchViewModel.uiStateLiveData.value) {
            is UIState.Data -> assert(uiState.data == SearchUIState.SearchClosedState)
        }
    }

    companion object {

        // Characters constants
        private const val CHARACTER_1_NAME = "Luke Skywalker"
        private const val CHARACTER_1_BIRTH_YEAR = "19BBY"
        private const val CHARACTER_1_GENDER = "male"
        private const val CHARACTER_2_NAME = "Leia Organa"
        private const val CHARACTER_2_BIRTH_YEAR = "19BBY"
        private const val CHARACTER_2_GENDER = "female"
        private const val CHARACTER_3_NAME = "Jabba Desilijic Tiure"
        private const val CHARACTER_3_BIRTH_YEAR = "600BBY"
        private const val CHARACTER_3_GENDER = "hermaphrodite"

        // Query constants
        private const val SEARCH_SUCCESS_QUERY = "L"
        private const val SEARCH_ERROR_QUERY = "***"
    }
}
