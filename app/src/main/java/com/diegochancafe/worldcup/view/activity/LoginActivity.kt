package com.diegochancafe.worldcup.view.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.diegochancafe.worldcup.data.model.request.LoginModelRequest
import com.diegochancafe.worldcup.data.model.singleton.TokenSingleton
import com.diegochancafe.worldcup.databinding.ActivityLoginBinding
import com.diegochancafe.worldcup.domain.model.LoginModelDomain
import com.diegochancafe.worldcup.domain.model.LoginTokenDomain
import com.diegochancafe.worldcup.util.Config
import com.diegochancafe.worldcup.viewmodel.LoginViewModel
import com.google.android.gms.security.ProviderInstaller
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// --
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    // --
    @Inject
    lateinit var tokenSingleton: TokenSingleton
    // --
    private val viewModel: LoginViewModel by viewModels()
    // --
    private lateinit var viewBinding: ActivityLoginBinding
    private lateinit var appContext: Context
    private lateinit var sharedPref: SharedPreferences

    // --
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        // --
        appContext = this
        sharedPref = getSharedPreferences(Config.SP_LOGIN_DATA, Context.MODE_PRIVATE)
        // --
        val spLogin: Boolean = sharedPref.getBoolean("isActive", false)
        val token: String? = sharedPref.getString("token", "")
        // --
        if (spLogin) {
            // --
            tokenSingleton.loginTokenDomain = LoginTokenDomain(token)
            changeActivity()
        }
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
            if (response.status == "success") {
                // --
                saveData(response)
                changeActivity()
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
        if (response) {
            viewBinding.rlLoader.visibility = View.VISIBLE
        } else {
            viewBinding.rlLoader.visibility = View.GONE
        }
    }

    // --
    private val errorMessageObserver = Observer<String> { response ->
        // --
        Toast.makeText(appContext, response, Toast.LENGTH_SHORT).show()
    }

    // --
    private fun saveData(loginModelDomain: LoginModelDomain) {
        // --
        val editor: SharedPreferences.Editor = sharedPref.edit()
        val token: String = "Bearer ${loginModelDomain.data?.token}"
        // --
        editor.putBoolean("isActive", true)
        editor.putString("token", token)
        // --
        editor.apply()
        // --
        tokenSingleton.loginTokenDomain = LoginTokenDomain(token)
    }

    // --
    private fun changeActivity() {
        // --
        startActivity(Intent(appContext, MainActivity::class.java))
        finish()
    }
}