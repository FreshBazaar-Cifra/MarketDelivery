package com.marketsvrn.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marketsvrn.common.ListResourceMapper
import com.marketsvrn.common.Resource

class BasePagingSource<I: Any, O : Any>(
    private val pageSize: Int,
    private val mapper: ListResourceMapper<I, O>,
    private val inputReceiver: suspend (page: Int, pageSize: Int) -> Resource<List<I>>
): PagingSource<Int, O>() {
    override fun getRefreshKey(state: PagingState<Int, O>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, O> {
        return try {
            val page = params.key ?: PagingParams.FIRST_PAGE_INDEX
            val response = mapper.map(inputReceiver(page, pageSize))
            Log.d("PAGING", response.message.toString())
            if (response.status == Resource.Status.ERROR) {
                return LoadResult.Error(Exception(response.message))
            }
            LoadResult.Page(
                data = response.data!!,
                prevKey = if (page == PagingParams.FIRST_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.data!!.isNotEmpty()) page.plus(1) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}