package com.diegochancafe.worldcup.view.fragment.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegochancafe.worldcup.databinding.FragmentHomeBinding
import com.diegochancafe.worldcup.domain.model.TeamModelDomain
import com.diegochancafe.worldcup.view.fragment.home.adapter.TeamAdapter
import com.diegochancafe.worldcup.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    // --
    private val viewModel: HomeViewModel by viewModels()
    // --
    private lateinit var viewBinding: FragmentHomeBinding
    private lateinit var appContext: Context
    private lateinit var teamAdapter: TeamAdapter

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
        teamAdapter = TeamAdapter(appContext)
        // --

        // --
        setupUI()
        setupViewModel()
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


}