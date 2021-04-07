package com.matheus.testioasys.di
import com.matheus.testioasys.data.api.auth.UserAuthAPI
import com.matheus.testioasys.data.auth.UserAuth
import com.matheus.testioasys.data.dao.UserAuthDAO
import com.matheus.testioasys.data.repository.*
import com.matheus.testioasys.data.sharedpreferences.UserSharedPreferences
import com.matheus.testioasys.ui.signin.SignInViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<UserAuthDAO> { UserSharedPreferences(androidContext()) }
    single<UserAuth> { UserAuthAPI() }
    single<LocalAuthProvider> { LocalUserAuth(get()) }
    single<RemoteAuthProvider> { RemoteUserAuth(get()) }
    single<AuthRepository> { UserAuthRepository(get(), get()) }
}

val signInModule = module {
    viewModel { SignInViewModel(get()) }
}
