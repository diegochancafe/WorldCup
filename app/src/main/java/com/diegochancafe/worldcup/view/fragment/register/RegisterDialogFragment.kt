package com.diegochancafe.worldcup.view.fragment.register

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.diegochancafe.worldcup.R
import com.diegochancafe.worldcup.data.model.request.LoginModelRequest
import com.diegochancafe.worldcup.data.model.request.RegisterModelRequest
import com.diegochancafe.worldcup.databinding.FragmentRegisterDialogBinding
import com.diegochancafe.worldcup.domain.model.LoginModelDomain
import com.diegochancafe.worldcup.view.activity.LoginActivity
import com.diegochancafe.worldcup.viewmodel.RegisterDialogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterDialogFragment : DialogFragment() {
    // --
    companion object {
        fun newInstance() = RegisterDialogFragment()
    }

    // --
    private val viewModel: RegisterDialogViewModel by viewModels()
    // --
    private lateinit var viewBinding: FragmentRegisterDialogBinding
    private lateinit var appContext: Context

    // --
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

    }

    // --
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentRegisterDialogBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    // --
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // --
        appContext = view.context
        viewBinding.rlBack.setOnClickListener {
            dismiss()
        }
        // --
        viewBinding.btnRegister.setOnClickListener { 
            // --
            validateForm()
        }
    }
    
    // --
    private fun validateForm() {
        // --
        val fullName: String = viewBinding.etLoginFullName.text.toString()
        val email: String = viewBinding.etLoginEmail.text.toString()
        val password: String = viewBinding.etLoginPassword.text.toString()
        val confirmPassword: String = viewBinding.etLoginConfirmPassword.text.toString()

        // --
        if (
            fullName.isEmpty() ||
            email.isEmpty() ||
            password.isEmpty() ||
            confirmPassword.isEmpty()
        ) {
            // --
            Toast.makeText(appContext, "Completar formulario de registro.", Toast.LENGTH_SHORT).show()
            return
        }

        // --
        if (password.length < 8  && confirmPassword.length < 8) {
            // --
            Toast.makeText(appContext, "La contraseña debe tener más de 8 carácteres.", Toast.LENGTH_SHORT).show()
            return
        }

        // --
        if (password == confirmPassword) {
            // --
            val request = RegisterModelRequest(fullName, email, password, confirmPassword)
            setupViewModel(request)
        } else {
            Toast.makeText(appContext, "Las contraseñas con coinciden.", Toast.LENGTH_SHORT).show()
        }
    }

    // --
    private fun setupViewModel(request: RegisterModelRequest) {
        // --
        viewModel.postRegister(request)
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
            Toast.makeText(appContext, response.message, Toast.LENGTH_SHORT).show()
            // --
            if (response.status == "success") {
                // --
                val email: String = viewBinding.etLoginEmail.text.toString()
                val password: String = viewBinding.etLoginPassword.text.toString()
                // --
                activity?.supportFragmentManager?.setFragmentResult("credentials", bundleOf("email" to email, "password" to password))
                // --
                dismiss()
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
    override fun onStart() {
        super.onStart()
        // --
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

}