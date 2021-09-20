package com.anupras.apl.thecatapisource.ui.catsearch

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anupras.apl.thecatapisource.api.CatImageApi
import com.anupras.apl.thecatapisource.model.CatImagesResponseItem

private const val STARTING_PAGE_INDEX = 1

class CatPagingSource(
    private val service: CatImageApi,
    private val query: Int
) : PagingSource<Int, CatImagesResponseItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatImagesResponseItem> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getAllCatImage(query, page,10)
//            Log.d("Check--","RESPONSE: ${response}")
            val photos = response.body()!!
//            Log.d("Check--","Photos: $photos")
            LoadResult.Page(
                data = photos!!,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (photos.isEmpty()!!) null else page + 1
            )
        } catch (exception: Exception) {
//            Log.d("Check--","Photos Exception: ${exception.message}")
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CatImagesResponseItem>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
