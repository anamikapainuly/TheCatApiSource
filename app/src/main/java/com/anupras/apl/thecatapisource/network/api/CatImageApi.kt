package com.anupras.apl.thecatapisource.network.api

import com.anupras.apl.thecatapisource.model.CatDetailResponse
import com.anupras.apl.thecatapisource.model.CatImagesResponseItem
import com.anupras.apl.thecatapisource.model.Categories
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Anamika Painuly on 19/09/21.
 */
interface CatImageApi {

    @GET("images/search/")
    suspend fun getAllCatImage(
        @Query("category_ids") category_ids: Int?,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<List<CatImagesResponseItem>>

    @GET("images/{image_id}")
    suspend fun getCatDetails(
        @Path("image_id") image_id: String
    ): Response<CatDetailResponse>

    @GET("categories")
    suspend fun getAllCategory(): Response<List<Categories>>

}