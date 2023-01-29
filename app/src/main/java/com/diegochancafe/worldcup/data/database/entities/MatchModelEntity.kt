package com.diegochancafe.worldcup.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.diegochancafe.worldcup.data.database.Converters
import com.diegochancafe.worldcup.domain.model.MatchModelDomain

// --
@Entity(tableName = "match_table")
data class MatchModelEntity (
    // --
    @ColumnInfo("_id") val identifier: String? = "",
    @ColumnInfo("away_score") val awayScore: Int? = 0,
    // --
    @TypeConverters(Converters::class)
    @ColumnInfo("away_scorers") val awayScorers: List<String>,
    // --
    @ColumnInfo("away_team_id") val awayTeamID: String? = "",
    @ColumnInfo("finished") val finished: String? = "",
    @ColumnInfo("group_name") val group: String,
    @ColumnInfo("home_score") val homeScore: Int? = 0,
    // --
    @TypeConverters(Converters::class)
    @ColumnInfo("home_scorers") val homeScorers: List<String>,
    // --
    @ColumnInfo("home_team_id") val homeTeamID: String? = "",
    // --
    @PrimaryKey
    @ColumnInfo("id") val id: String = "",
    @ColumnInfo("local_date") val localDate: String? = "",
    @ColumnInfo("matchday") val matchday: String? = "",
    @ColumnInfo("persian_date") val persianDate: String? = "",
    @ColumnInfo("stadium_id") val stadiumID: String? = "",
    @ColumnInfo("time_elapsed") val timeElapsed: String? = "",
    @ColumnInfo("type") val type: String? = "",
    @ColumnInfo("home_team_fa") val homeTeamFa: String? = "",
    @ColumnInfo("away_team_fa") val awayTeamFa: String? = "",
    @ColumnInfo("home_team_en") val homeTeamEn: String? = "",
    @ColumnInfo("away_team_en") val awayTeamEn: String? = "",
    @ColumnInfo("home_flag") val homeFlag: String? = "",
    @ColumnInfo("away_flag") val awayFlag: String?
)

// -- Extensions
fun MatchModelDomain.toDatabase() = MatchModelEntity(identifier, awayScore, awayScorers, awayTeamID, finished, group, homeScore, homeScorers, homeTeamID, id, localDate, matchday, persianDate, stadiumID, timeElapsed, type, homeTeamFa, awayTeamFa, homeTeamEn, awayTeamEn, homeFlag, awayFlag)