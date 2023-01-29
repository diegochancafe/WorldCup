package com.diegochancafe.worldcup.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegochancafe.worldcup.domain.model.MatchModelDomain
import com.diegochancafe.worldcup.domain.usecase.GetMatchByGroupUseCase
import com.diegochancafe.worldcup.domain.usecase.GetMatchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchDialogViewModel @Inject constructor(
    // -- Injects
    private val getMatchByGroupUseCase: GetMatchByGroupUseCase
) : ViewModel() {
    // --
    var matchModelDomain: MutableLiveData<List<MatchModelDomain>> = MutableLiveData()
    var errorMessage = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()

    // --
    fun getMatchByGroup(group: String) {
        // --
        isLoading.postValue(true) // -- Loading...
        // --
        viewModelScope.launch {
            // --
            try {
                // --
                val result = getMatchByGroupUseCase.invoke(group)
                matchModelDomain.postValue(result)
                // --
                isLoading.postValue(false) // -- Finish...

            } catch (e: Exception) {
                // --
                errorMessage.postValue(e.message)
                isLoading.postValue(false) // -- Finish...
            }
        }
    }
}