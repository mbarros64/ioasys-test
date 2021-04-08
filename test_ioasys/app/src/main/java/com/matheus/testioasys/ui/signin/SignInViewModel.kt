package com.matheus.testioasys.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.testioasys.data.auth.AuthException
import com.matheus.testioasys.data.repository.AuthRepository
import com.matheus.testioasys.extensions.isEmail
import com.matheus.testioasys.util.RequestState
import kotlinx.coroutines.launch

class SignInViewModel(private val authRepository: AuthRepository): ViewModel() {
    private val mutableRequestState = MutableLiveData<RequestState>()

    var requestState: LiveData<RequestState> = mutableRequestState

    fun authenticate(email: String, password: String) {
        viewModelScope.launch {
            try {
                when {
                    (email.isEmpty() || !email.isEmail()) -> throw AuthException.InvalidEmailException()
                    password.isEmpty() -> throw AuthException.InvalidPasswordException()
                }

                mutableRequestState.postValue(RequestState.LOADING)

                val userData = authRepository.auth(email, password)
                authRepository.saveAuthData(userData)

                mutableRequestState.postValue(RequestState.SUCCESS)
            } catch (e: Exception) {
                mutableRequestState.postValue(RequestState.FAILED(e))
            }
        }
    }
}