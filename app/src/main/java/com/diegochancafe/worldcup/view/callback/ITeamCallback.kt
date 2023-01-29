package com.diegochancafe.worldcup.view.callback

import com.diegochancafe.worldcup.domain.model.TeamModelDomain

interface ITeamCallback {
    fun onTeamGroupClicked(teamModelDomain: TeamModelDomain)
    fun onTeamCountryClicked(teamModelDomain: TeamModelDomain)
}