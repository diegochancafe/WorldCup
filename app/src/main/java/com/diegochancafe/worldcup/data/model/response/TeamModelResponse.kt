package com.diegochancafe.worldcup.data.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// --
data class TeamModelResponse (
    val status: String?,
    val data: List<TeamData>?
): Serializable

// --
data class TeamData (
    @SerializedName("id")
    val id: String,

    @SerializedName("_id")
    val identifier: String?,

    @SerializedName("name_en")
    val nameEn: String?,

    @SerializedName("name_fa")
    val nameFa: String?,

    val flag: String?,

    @SerializedName("fifa_code")
    val fifaCode: String?,

    val iso2: String?,
    val groups: String?
)
