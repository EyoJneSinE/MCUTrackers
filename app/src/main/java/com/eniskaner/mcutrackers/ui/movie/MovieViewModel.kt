package com.eniskaner.mcutrackers.ui.movie

import androidx.annotation.MenuRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.mcutrackers.data.repository.MovieRepository
import com.eniskaner.mcutrackers.database.model.Phase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val key = "Phase"
    private val _phase = MutableStateFlow(savedStateHandle[key] ?: Phase.ALL)
        val phase = _phase.asStateFlow()

    val movies = _phase.flatMapLatest { phase ->
        when (phase) {
            Phase.ALL -> repository.getMovies()
            else -> repository.getMoviesByPhase(phase.ordinal)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    fun setPhase(@MenuRes menuId: Int) {
        Phase.map[menuId]?.let {
            _phase.value = it
            savedStateHandle[key] = it
        }
    }
}