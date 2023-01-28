package com.diegochancafe.worldcup.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.diegochancafe.worldcup.data.model.request.LoginModelRequest
import com.diegochancafe.worldcup.databinding.ActivityLoginBinding
import com.diegochancafe.worldcup.domain.model.LoginModelDomain
import com.diegochancafe.worldcup.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

// --
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    // --
    private val viewModel: LoginViewModel by viewModels()
    // --
    private lateinit var viewBinding: ActivityLoginBinding
    private lateinit var appContext: Context

    // --
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        // --
        viewBinding.btnLogin.setOnClickListener {
            login()
        }
        // --
        supportActionBar?.hide()
    }

    // --
    private fun login() {
        // --
        val username = viewBinding.etLoginUsername.text.toString().trim()
        val password = viewBinding.etLoginPassword.text.toString().trim()
        // --
        if (username.isEmpty() || password.isEmpty()) {
            // --
            Toast.makeText(appContext, "Ingresar credenciales", Toast.LENGTH_SHORT).show()
        } else {
            // --
            val request = LoginModelRequest(username, password)
            setupViewModel(request)
        }
    }

    // --
    private fun setupViewModel(request: LoginModelRequest) {
        // --
        viewModel.postLogin(request)
        // --
        viewModel.loginModelDomain.observe(this, loginModelDomain)
        viewModel.errorMessage.observe(this, errorMessageObserver)
        viewModel.isLoading.observe(this, isLoadingObserver)
    }

    // --
    private val loginModelDomain = Observer<LoginModelDomain?> { response ->
        // --
        if (response != null) {
            // --
            if (response.status == "OK") {
//                saveData(response)
                Log.d("TAG", "$response: ")
            } else {
                Toast.makeText(appContext, response.message, Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(appContext, "Error, intentelo nuevamente.", Toast.LENGTH_SHORT).show()
        }
    }

    // --
    private val isLoadingObserver = Observer<Boolean> { response ->
        // --
        if (!response) {
            // --
        }
    }

    // --
    private val errorMessageObserver = Observer<String> { response ->
        // --
        Toast.makeText(appContext, response, Toast.LENGTH_SHORT).show()
    }

}