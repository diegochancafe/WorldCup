package com.diegochancafe.worldcup.data.model.response

import com.google.gson.annotations.SerializedName

// --
data class MatchModelResponse (
    val status: String?,
    val data: List<MatchData>?
)

// --
data class MatchData (
    @SerializedName("_id")
    val identifier: String?,

    @SerializedName("away_score")
    val awayScore: Int?,

    @SerializedName("away_scorers")
    val awayScorers: List<String>?,

    @SerializedName("away_team_id")
    val awayTeamID: String?,

    val finished: String?,
    val group: String?,

    @SerializedName("home_score")
    val homeScore: Int?,

    @SerializedName("home_scorers")
    val homeScorers: List<String>?,

    @SerializedName("home_team_id")
    val homeTeamID: String?,

    @SerializedName("id")
    val id: String?,

    @SerializedName("local_date")
    val localDate: String?,

    val matchday: String?,

    @SerializedName("persian_date")
    val persianDate: String?,

    @SerializedName("stadium_id")
    val stadiumID: String?,

    @SerializedName("time_elapsed")
    val timeElapsed: String?,

    val type: String?,

    @SerializedName("home_team_fa")
    val homeTeamFa: String?,

    @SerializedName("away_team_fa")
    val awayTeamFa: String?,

    @SerializedName("home_team_en")
    val homeTeamEn: String?,

    @SerializedName("away_team_en")
    val awayTeamEn: String?,

    @SerializedName("home_flag")
    val homeFlag: String?,

    @SerializedName("away_flag")
    val awayFlag: String?
)
