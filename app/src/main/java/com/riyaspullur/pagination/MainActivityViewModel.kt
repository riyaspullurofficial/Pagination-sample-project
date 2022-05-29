package com.riyaspullur.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlin.math.max

class MainActivityViewModel:ViewModel() {

    var retroService: RetroService

    init {
        retroService=RetroInstance.getRetroInstance().create(RetroService::class.java)

    }
    fun getListData():Flow<PagingData<CharacterData>>{
        return Pager(config = PagingConfig(pageSize = 42, maxSize = 200),
        pagingSourceFactory = {CharacterPagingSource(retroService)}).flow.cachedIn(viewModelScope)
    }
}