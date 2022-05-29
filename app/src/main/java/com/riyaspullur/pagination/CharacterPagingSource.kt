package com.riyaspullur.pagination

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception
import java.net.URI

class CharacterPagingSource (val apiService:RetroService):PagingSource<Int,CharacterData>(){
    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        return try {
            val nextPage=params.key ?: FIRST_PAGE_INDEX
            val response=apiService.getDataFromAPI(nextPage)
            var nextPageNumber:Int?=null
            if (response.info.next !=null){
                val uri=Uri.parse(response.info.next)
                val nextPageQuery=uri.getQueryParameter("page")
                nextPageNumber=nextPageQuery?.toInt()
            }
            LoadResult.Page(data = response.results,
            prevKey = null,
            nextKey = nextPageNumber)


        }catch (e:Exception){
            LoadResult.Error(e)

        }
    }
    companion object{
        val FIRST_PAGE_INDEX=1
    }
}