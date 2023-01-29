package com.diegochancafe.worldcup.view.callback

import com.diegochancafe.worldcup.domain.model.TeamModelDomain

interface ITeamCallback {
    fun onTeamClicked(teamModelDomain: TeamModelDomain)
}