package com.eniskaner.mcutrackers.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.mcutrackers.data.repository.FavouriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    repository: FavouriteRepository
): ViewModel() {
    val favourites = repository.getFavourites().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}