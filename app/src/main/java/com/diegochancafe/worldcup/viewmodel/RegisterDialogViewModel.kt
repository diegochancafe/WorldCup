package com.diegochancafe.worldcup.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegochancafe.worldcup.data.model.request.RegisterModelRequest
import com.diegochancafe.worldcup.domain.model.LoginModelDomain
import com.diegochancafe.worldcup.domain.usecase.PostRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// --
@HiltViewModel
class RegisterDialogViewModel @Inject constructor(
    // -- Injections
    private val postRegisterUseCase: PostRegisterUseCase,
): ViewModel() {
    // --
    val loginModelDomain = MutableLiveData<LoginModelDomain?>()
    val isLoading = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String>()

    // --
    fun postRegister(request: RegisterModelRequest) {
        // --
        isLoading.postValue(true) // -- Loading...
        // --
        viewModelScope.launch {
            // --
            try {
                // --
                val result = postRegisterUseCase(request)
                loginModelDomain.postValue(result)
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