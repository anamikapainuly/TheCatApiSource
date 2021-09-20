package com.anupras.apl.thecatapisource.model

data class CatImagesResponseItem(
    val breeds: List<Any>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int,
    val alt_names: String,
)