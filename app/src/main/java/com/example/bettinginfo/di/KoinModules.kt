package com.example.bettinginfo.di

import com.example.bettinginfo.data.data_source.BettingInfoDataSource
import com.example.bettinginfo.data.data_source.BettingInfoDataSourceImpl
import com.example.bettinginfo.data.repository.EventsRepository
import com.example.bettinginfo.data.repository.EventsRepositoryImpl
import com.example.bettinginfo.data.repository.LeaguesRepository
import com.example.bettinginfo.data.repository.LeaguesRepositoryImpl
import com.example.bettinginfo.data.repository.SportsRepository
import com.example.bettinginfo.data.repository.SportsRepositoryImpl
import com.example.bettinginfo.vm.EventsViewModel
import com.example.bettinginfo.vm.LeaguesViewModel
import com.example.bettinginfo.vm.SportsViewModel
import com.example.bettinginfo.vm.TopBarViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val dataSourceModule = module {
    single<BettingInfoDataSource> { BettingInfoDataSourceImpl(androidContext()) }
}

val repoModule = module{
    includes(dataSourceModule)
    single<SportsRepository> { SportsRepositoryImpl(get()) }
    single<LeaguesRepository> { LeaguesRepositoryImpl(get()) }
    single<EventsRepository> { EventsRepositoryImpl(get()) }
}

val vmModule = module {
    includes(repoModule)
    viewModel<TopBarViewModel>{ TopBarViewModel() }
    viewModel<SportsViewModel>{ SportsViewModel(get()) }
    viewModel<LeaguesViewModel>{ (sportId: Int) -> LeaguesViewModel(get(), sportId) }
    viewModel<EventsViewModel>{ (leagueId: Int) -> EventsViewModel(get(), leagueId) }
}

val appModule = module{
    includes(vmModule)
}