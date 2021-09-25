package com.anupras.apl.thecatapisource.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.anupras.apl.thecatapisource.model.CatDetailResponse
import com.anupras.apl.thecatapisource.model.Categories
import com.anupras.apl.thecatapisource.network.ApiResult
import com.anupras.apl.thecatapisource.network.Events
import com.anupras.apl.thecatapisource.network.Status
import com.anupras.apl.thecatapisource.network.api.CatImageApi
import com.anupras.apl.thecatapisource.repository.CatRepository
import com.anupras.apl.thecatapisource.ui.catsearch.CatPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Anamika Painuly on 20/09/21.
 */
@HiltViewModel
class CatImagesViewModel @Inject constructor(
    private val catImageApi: CatImageApi,
    private val repository: CatRepository
) : ViewModel() {
    private val query = MutableLiveData<Int>()

    private val _allCategories = MutableLiveData<Events<ApiResult<List<Categories>>>>()
    val allCategories: MutableLiveData<Events<ApiResult<List<Categories>>>> = _allCategories

    fun getAllCategory() = viewModelScope.launch {
        _allCategories.postValue(Events(ApiResult(Status.LOADING, null, null)))
        _allCategories.postValue(Events(repository.getAllCategory()))
        Log.d("Check--Category is", ":$allCategories")
    }

    val list = query.switchMap { query ->
        Pager(PagingConfig(pageSize = 10)) {
            CatPagingSource(catImageApi, query)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(category_ids: Int?) {
        query.postValue(category_ids)
    }

    private val _catDetails = MutableLiveData<Events<ApiResult<CatDetailResponse>>>()
    val catDetails: LiveData<Events<ApiResult<CatDetailResponse>>> = _catDetails

    fun getCatDetails(imageId: String) = viewModelScope.launch {
        _catDetails.postValue(Events(ApiResult(Status.LOADING, null, null)))
        _catDetails.postValue(Events(repository.getCatDetails(imageId)))
    }


}