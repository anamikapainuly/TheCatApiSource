package com.anupras.apl.thecatapisource.repository

import android.util.Log
import com.anupras.apl.thecatapisource.network.api.CatImageApi
import com.anupras.apl.thecatapisource.model.CatDetailResponse
import com.anupras.apl.thecatapisource.model.Categories
import com.anupras.apl.thecatapisource.network.ApiResult
import com.anupras.apl.thecatapisource.network.Status

/**
 * Created by Anamika Painuly on 21/09/21.
 */
class CatRepository(private val catImageApi: CatImageApi) {

    suspend fun getCatDetails(imageId: String): ApiResult<CatDetailResponse> {
        return try {
            val response = catImageApi.getCatDetails(imageId)
            if (response.isSuccessful) {
                Log.d("Check--Detail", "" + response.body())
                ApiResult(Status.SUCCESS, response.body(), null)
            } else {
                ApiResult(Status.ERROR, null, null)
            }
        } catch (e: Exception) {
            Log.d("Check--Detail", "" + e.message)
            ApiResult(Status.ERROR, null, null)
        }
    }

    suspend fun getAllCategory(): ApiResult<List<Categories>> {
        return try {
            val response = catImageApi.getAllCategory()?.body()
            if (catImageApi.getAllCategory()?.isSuccessful) {
                Log.d("Check--Category", "" + response)
                ApiResult(Status.SUCCESS, response, null)
            } else {
                ApiResult(Status.ERROR, null, null)
            }
        } catch (e: Exception) {
            Log.d("Check--Category", "" + e.message)
            ApiResult(Status.ERROR, null, null)
        }
    }


}