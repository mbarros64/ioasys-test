package com.matheus.testioasys.di
import com.matheus.testioasys.data.api.auth.UserAuthAPI
import com.matheus.testioasys.data.api.enterprise.EnterpriseAPI
import com.matheus.testioasys.data.auth.UserAuth
import com.matheus.testioasys.data.dao.EnterpriseDAO
import com.matheus.testioasys.data.dao.UserAuthDAO
import com.matheus.testioasys.data.repository.*
import com.matheus.testioasys.data.repository.auth.*
import com.matheus.testioasys.data.repository.enterprise.EnterpriseProvider
import com.matheus.testioasys.data.repository.enterprise.EnterpriseRepository
import com.matheus.testioasys.data.repository.enterprise.EnterprisesRepository
import com.matheus.testioasys.data.repository.enterprise.RemoteEnterpriseProvider
import com.matheus.testioasys.data.sharedpreferences.UserSharedPreferences
import com.matheus.testioasys.ui.signin.SignInViewModel
import com.matheus.testioasys.ui.splashscreen.SplashScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<UserAuthDAO> { UserSharedPreferences(androidContext()) }
    single<UserAuth> { UserAuthAPI() }
    single<EnterpriseDAO> { EnterpriseAPI(get()) }
    single<LocalAuthProvider> { LocalUserAuth(get()) }
    single<RemoteAuthProvider> { RemoteUserAuth(get()) }
    single<RemoteEnterpriseProvider> { EnterpriseProvider(get()) }
    single<AuthRepository> { UserAuthRepository(get(), get()) }
    single<EnterpriseRepository> { EnterprisesRepository(get()) }
}

val splashModule = module {
    viewModel { SplashScreenViewModel(get()) }
}

val signInModule = module {
    viewModel { SignInViewModel(get()) }
}
