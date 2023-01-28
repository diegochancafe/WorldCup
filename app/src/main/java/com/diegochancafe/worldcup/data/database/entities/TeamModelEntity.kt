package com.diegochancafe.worldcup.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.diegochancafe.worldcup.domain.model.TeamModelDomain

// --
data class TeamModelEntity (
    // --
    @PrimaryKey
    @ColumnInfo("id")  val id: String? = "",
    @ColumnInfo("_id") val identifier: String? = "",
    @ColumnInfo("name_en")  val nameEn: String? = "",
    @ColumnInfo("name_fa")  val nameFa: String? = "",
    @ColumnInfo("flag") val flag: String? = "",
    @ColumnInfo("fifa_code")  val fifaCode: String? = "",
    @ColumnInfo("iso2") val iso2: String? = "",
    @ColumnInfo("groups") val groups: String? = "",
)

// -- Extensions
fun TeamModelDomain.toDatabase() = TeamModelEntity(id, identifier, nameEn, nameFa, flag, fifaCode, iso2, groups)