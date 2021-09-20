package com.anupras.apl.thecatapisource.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Anamika Painuly on 21/09/21.
 */
data class Categories (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String
)