package com.diegochancafe.worldcup.view.fragment.match

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegochancafe.worldcup.R
import com.diegochancafe.worldcup.databinding.FragmentMatchDialogBinding
import com.diegochancafe.worldcup.domain.model.MatchModelDomain
import com.diegochancafe.worldcup.domain.model.TeamModelDomain
import com.diegochancafe.worldcup.view.callback.IMatchCallback
import com.diegochancafe.worldcup.view.fragment.match.adapter.MatchAdapter
import com.diegochancafe.worldcup.viewmodel.MatchDialogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchDialogFragment : DialogFragment(), IMatchCallback {

    companion object {
        fun newInstance() = MatchDialogFragment()
    }
    // --
    private val viewModel: MatchDialogViewModel by viewModels()
    // --
    private lateinit var viewBinding: FragmentMatchDialogBinding
    private lateinit var appContext: Context
    private lateinit var teamModelDomain: TeamModelDomain
    private lateinit var matchAdapter: MatchAdapter
    // --
    private var isCountry: Boolean? = false

    // --
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    // --
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMatchDialogBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    // --
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // --
        appContext = view.context
        teamModelDomain = arguments?.getSerializable("teamModelDomain") as TeamModelDomain
        isCountry = arguments?.getBoolean("isCountry")
        matchAdapter = MatchAdapter(this, appContext)
        viewBinding.tvTeam.text = "Grupo ${teamModelDomain.groups}"
        // --
        viewBinding.rlBack.setOnClickListener {
            dismiss()
        }
        // --
        setupUI()
        teamModelDomain.groups?.let { setupViewModel(it) }
    }

    // --
    private fun setupUI() {
        // --
        viewBinding.rvList.apply {
            layoutManager = LinearLayoutManager(appContext, LinearLayoutManager.VERTICAL, false)
            adapter = matchAdapter
        }
    }

    // --
    private fun setupViewModel(group: String) {
        // --
        if (isCountry == true) {
            viewModel.getCountryMatchesByGroup(group, teamModelDomain.nameEn.toString())
        } else {
            viewModel.getMatchByGroup(group)
        }

        // --
        viewModel.matchModelDomain.observe(this.viewLifecycleOwner, matchModelDomainObserver)
        viewModel.errorMessage.observe(this.viewLifecycleOwner, errorMessageObserver)
        viewModel.isLoading.observe(this.viewLifecycleOwner, isLoadingObserver)
    }

    // --
    private val matchModelDomainObserver = Observer<List<MatchModelDomain>> { response ->
        // --
        if (response != null) {
            // --
            matchAdapter.updateData(response)
//            viewBinding.tvEmptyMessage.visibility = View.GONE
        } else {
            // --
//            viewBinding.tvEmptyMessage.visibility = View.VISIBLE
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
        Toast.makeText(appContext, response, Toast.LENGTH_LONG).show()
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