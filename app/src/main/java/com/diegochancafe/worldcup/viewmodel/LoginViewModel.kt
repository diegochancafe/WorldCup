package com.diegochancafe.worldcup.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegochancafe.worldcup.data.model.request.LoginModelRequest
import com.diegochancafe.worldcup.domain.model.LoginModelDomain
import com.diegochancafe.worldcup.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// --
@HiltViewModel
class LoginViewModel @Inject constructor(
    // -- Injections
    private val loginUseCase: LoginUseCase,
): ViewModel() {
    // --
    val loginModelDomain = MutableLiveData<LoginModelDomain?>()
    val isLoading = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String>()

    // --
    fun postLogin(request: LoginModelRequest) {
        // --
        isLoading.postValue(true) // -- Loading...
        // --
        viewModelScope.launch {
            // --
            try {
                // --
                val result = loginUseCase(request)
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