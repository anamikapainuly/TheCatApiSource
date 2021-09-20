package com.anupras.apl.thecatapisource.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Anamika Painuly on 21/09/21.
 */
data class CatDetailResponse(
    @SerializedName("categories") val categories : List<Categories>,
    @SerializedName("height") val height : Int,
    @SerializedName("id") val id : String,
    @SerializedName("url") val url : String,
    @SerializedName("width") val width : Int)
