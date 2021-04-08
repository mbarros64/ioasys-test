package com.matheus.testioasys.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.testioasys.data.auth.AuthException
import com.matheus.testioasys.data.model.Enterprise
import com.matheus.testioasys.data.repository.enterprise.EnterpriseRepository
import com.matheus.testioasys.util.RequestState
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SearchViewModel(private val enterpriseRepository: EnterpriseRepository) : ViewModel() {

    private val mutableRequestState = MutableLiveData<RequestState>()
    private val mutableSearchResult = MutableLiveData<List<Enterprise>>()

    var requestState: LiveData<RequestState> = mutableRequestState
    var searchResult: LiveData<List<Enterprise>> = mutableSearchResult

    fun search(query: String) {
        viewModelScope.launch {
            try {
                mutableRequestState.postValue(RequestState.LOADING)

                val result = enterpriseRepository.search(query)
                mutableSearchResult.postValue(result)

                mutableRequestState.postValue(RequestState.SUCCESS)
            } catch (e: HttpException) {
                mutableRequestState.postValue(RequestState.FAILED(AuthException.UnauthorizedException()))
            } catch (e: Exception) {
                mutableRequestState.postValue(RequestState.FAILED(e))
            }
        }
    }
}