package com.diegochancafe.worldcup.domain.model

import com.diegochancafe.worldcup.data.model.response.MatchData

// --
data class MatchModelDomain (
    val identifier: String? = "",
    val awayScore: Int? = null,
    val awayScorers: List<String>? = emptyList(),
    val awayTeamID: String? = "",
    val finished: String? = "",
    val group: String? = "",
    val homeScore: Int? = null,
    val homeScorers: List<String>? = emptyList(),
    val homeTeamID: String? = "",
    val id: String? = "",
    val localDate: String? = "",
    val matchday: String? = "",
    val persianDate: String? = "",
    val stadiumID: String? = "",
    val timeElapsed: String? = "",
    val type: String? = "",
    val homeTeamFa: String? = "",
    val awayTeamFa: String? = "",
    val homeTeamEn: String? = "",
    val awayTeamEn: String? = "",
    val homeFlag: String? = "",
    val awayFlag: String? = ""
)

// -- Extensions
fun MatchData.toDomain() = MatchModelDomain(identifier, awayScore, awayScorers, awayTeamID, finished, group, homeScore, homeScorers, homeTeamID, id, localDate, matchday, persianDate, stadiumID, timeElapsed, type, homeTeamFa, awayTeamFa, homeTeamEn, awayTeamEn, homeFlag, awayFlag)