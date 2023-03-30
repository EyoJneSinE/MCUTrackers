package com.eniskaner.mcutrackers.ui.movie

import androidx.annotation.MenuRes
import androidx.lifecycle.ViewModel
import com.eniskaner.mcutrackers.data.repository.MovieRepository
import com.eniskaner.mcutrackers.database.model.Phase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class MovieViewModel @Inject constructor(
    repository: MovieRepository
): ViewModel() {
    var phase = MutableStateFlow(Phase.ALL)
        private set
    val movies = phase.flatMapLatest { phase ->
        when (phase) {
            Phase.ALL -> repository.getMoviesByPhase(phase.ordinal)
            Phase.ONE -> TODO()
            Phase.TWO -> TODO()
            Phase.THREE -> TODO()
            Phase.FOUR -> TODO()
        }
    }
    fun setPhase(@MenuRes menuId: Int) {
        Phase.map[menuId]?.let { phase.value = it }
    }
}