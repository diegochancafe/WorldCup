package com.diegochancafe.worldcup.domain.model

import com.diegochancafe.worldcup.data.database.entities.TeamModelEntity
import com.diegochancafe.worldcup.data.model.response.TeamData
import java.io.Serializable

// --
data class TeamModelDomain (
    val id: String,
    val identifier: String? = "",
    val nameEn: String? = "",
    val nameFa: String? = "",
    val flag: String? = "",
    val fifaCode: String? = "",
    val iso2: String? = "",
    val groups: String? = "",
    var index: Int? = 0
): Serializable

// -- Extensions
fun TeamData.toDomain() = TeamModelDomain(id, identifier, nameEn, nameFa, flag, fifaCode, iso2, groups)
fun TeamModelEntity.toDomain() = TeamModelDomain(id, identifier, nameEn, nameFa, flag, fifaCode, iso2, groups)