package com.diegochancafe.worldcup.domain.model

import com.diegochancafe.worldcup.data.database.entities.MatchModelEntity
import com.diegochancafe.worldcup.data.model.response.MatchData

// --
data class MatchModelDomain (
    val identifier: String? = "",
    val awayScore: Int? = 0,
    val awayScorers: List<String>,
    val awayTeamID: String? = "",
    val finished: String? = "",
    val group: String,
    val homeScore: Int? = 0,
    val homeScorers: List<String>,
    val homeTeamID: String? = "",
    val id: String = "",
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
fun MatchModelEntity.toDomain() = MatchModelDomain(identifier, awayScore, awayScorers, awayTeamID, finished, group, homeScore, homeScorers, homeTeamID, id, localDate, matchday, persianDate, stadiumID, timeElapsed, type, homeTeamFa, awayTeamFa, homeTeamEn, awayTeamEn, homeFlag, awayFlag)