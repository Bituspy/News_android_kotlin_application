package com.example.crypto.ui.news_detail.state

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.common.Constants
import com.example.crypto.common.Resource
import com.example.crypto.domain.use_cases.GetNewsDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val getNewsDetailUseCase: GetNewsDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(NewsDetailState())
    val state: State<NewsDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_NEWS_ID)?.let {
            getNewsDetail(it)
        }
    }

    private fun getNewsDetail(newsId: String) {
        getNewsDetailUseCase(newsId).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = NewsDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = NewsDetailState(
                        newsDetail = result.data
                    )
                }
                is Resource.Error -> {
                    _state.value = NewsDetailState(
                        error = result.message ?: "Unexpected error"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
} 