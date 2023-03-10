package com.diegochancafe.worldcup.view.fragment.home

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegochancafe.worldcup.R
import com.diegochancafe.worldcup.databinding.FragmentHomeBinding
import com.diegochancafe.worldcup.domain.model.TeamModelDomain
import com.diegochancafe.worldcup.util.Config
import com.diegochancafe.worldcup.view.activity.SplashActivity
import com.diegochancafe.worldcup.view.callback.ITeamCallback
import com.diegochancafe.worldcup.view.fragment.home.adapter.TeamAdapter
import com.diegochancafe.worldcup.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class HomeFragment : Fragment(), ITeamCallback {
    // --
    private val viewModel: HomeViewModel by viewModels()
    // --
    private lateinit var viewBinding: FragmentHomeBinding
    private lateinit var appContext: Context
    private lateinit var teamAdapter: TeamAdapter
    private lateinit var sharedPref: SharedPreferences

    // --
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    // --
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // --
        appContext = view.context
        teamAdapter = TeamAdapter(this, appContext)
        sharedPref = appContext.getSharedPreferences(Config.SP_LOGIN_DATA, Context.MODE_PRIVATE)
        // --
        viewBinding.rlLogout.setOnClickListener {
            logout()
        }
        // --
        setupUI()
        setupViewModel()
    }

    private fun logout() {
        // --
        val dialogBuilder = AlertDialog.Builder(appContext)
        // --
        dialogBuilder.setMessage("??Est?? seguro que quiere cerrar la sesi??n ?")
            .setCancelable(false)
            .setPositiveButton("ACEPTAR") { _, _ ->
                // --
                val editor: SharedPreferences.Editor = sharedPref.edit()
                editor.clear()
                editor.apply()
                // --
                startActivity(Intent(appContext, SplashActivity::class.java))
                activity?.finish()
            }
            .setNegativeButton("CANCELAR") { _, _ -> }
        // --
        val alert = dialogBuilder.create()
        alert.show()
    }

    // --
    private fun setupUI() {
        // --
        viewBinding.rvList.apply {
            layoutManager = LinearLayoutManager(appContext, LinearLayoutManager.VERTICAL, false)
            adapter = teamAdapter
        }
    }

    // --
    private fun setupViewModel() {
        // --
        viewModel.getTeam()
        viewModel.getMatch()
        // --
        viewModel.teamModelDomain.observe(this.viewLifecycleOwner, teamModelDomainObserver)
        viewModel.errorMessage.observe(this.viewLifecycleOwner, errorMessageObserver)
        viewModel.isLoading.observe(this.viewLifecycleOwner, isLoadingObserver)
    }

    // --
    private val teamModelDomainObserver = Observer<List<TeamModelDomain>> { response ->
        // --
        val orderResponse = response.sortedBy { it.groups }
        val groupBy = orderResponse.groupBy { it.groups }
        // --
        val listForAdapter: MutableList<TeamModelDomain> = mutableListOf()
        // --
        groupBy.forEach {
            if (it.key != "--") {
                // --
                listForAdapter.add(
                    TeamModelDomain(
                        "",
                        "",
                        "Grupo ${it.key}",
                        "",
                        "",
                        "",
                        "",
                        it.key,
                        0
                    ))
                // --
                it.value.forEach { item ->
                    // --
                    item.index = 1
                    listForAdapter.add(item)
                }
            }
        }
        // --
        teamAdapter.updateData(listForAdapter)
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
        Toast.makeText(appContext, response, Toast.LENGTH_LONG).show()
    }

    // --
    override fun onTeamGroupClicked(teamModelDomain: TeamModelDomain) {
        // --
        val bundle = bundleOf(
            "teamModelDomain" to teamModelDomain,
            "isCountry" to false
        )
        // --
        findNavController().navigate(R.id.navigationMatchDialogFragment, bundle)
    }

    override fun onTeamCountryClicked(teamModelDomain: TeamModelDomain) {
        // --
        val bundle = bundleOf(
            "teamModelDomain" to teamModelDomain,
            "isCountry" to true
        )
        // --
        findNavController().navigate(R.id.navigationMatchDialogFragment, bundle)
    }

}