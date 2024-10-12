package com.example.bettinginfo.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.bettinginfo.ui.navigation.ScreenDestination
import com.example.bettinginfo.vm.TopBarViewModel
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.navigate
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationScreen(
    navController: NavController<ScreenDestination>,
    contentPadding: PaddingValues,
    topBarViewModel: TopBarViewModel,
    destination: ScreenDestination,
    onNavigate: () -> Unit
) {

    val screen: @Composable () -> Unit = remember(destination) {
        val factory: (@Composable () -> Unit) -> @Composable () -> Unit = {
            @Composable { it() }
        }
        onNavigate()
        return@remember when (destination){
            ScreenDestination.Sports -> factory{
                topBarViewModel.updateTitle(null)
                SportsScreen(koinViewModel(), contentPadding) { it, title ->
                    topBarViewModel.updateTitle(title)
                    navController.navigate(ScreenDestination.Leagues(it, title))
                }
            }

            is ScreenDestination.Leagues -> factory{
                topBarViewModel.updateTitle(destination.newTitle)
                LeaguesScreen(koinViewModel{ parametersOf(destination.sportId) }, contentPadding) { it, title ->
                    navController.navigate(ScreenDestination.Events(it, title))
                }
            }
            is ScreenDestination.Events -> factory{
                topBarViewModel.updateTitle(destination.newTitle)
                EventsScreen(koinViewModel{ parametersOf(destination.leagueId) }, contentPadding)
            }
        }
    }
    screen()
}