package com.diegochancafe.worldcup.view.fragment.match

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.diegochancafe.worldcup.R

class MatchDialogFragment : Fragment() {

    companion object {
        fun newInstance() = MatchDialogFragment()
    }

    private lateinit var viewModel: MatchDialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MatchDialogViewModel::class.java)
        // TODO: Use the ViewModel
    }

}