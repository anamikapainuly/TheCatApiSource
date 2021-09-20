package com.anupras.apl.thecatapisource.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.anupras.apl.thecatapisource.api.CatImageApi
import com.anupras.apl.thecatapisource.ui.catsearch.CatPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Anamika Painuly on 20/09/21.
 */
@HiltViewModel
class CatSearchViewModel @Inject constructor(private val catImageApi: CatImageApi): ViewModel() {

    private val query = MutableLiveData<Int>()

    val list = query.switchMap { query->
        Pager(PagingConfig(pageSize = 10)) {
            CatPagingSource(catImageApi,query)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(category_ids:Int){
        query.postValue(category_ids)
    }
}