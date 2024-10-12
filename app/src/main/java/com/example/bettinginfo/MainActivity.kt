package com.example.bettinginfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bettinginfo.ui.composables.TopBar
import com.example.bettinginfo.ui.navigation.ScreenDestination
import com.example.bettinginfo.ui.screens.NavigationScreen
import com.example.bettinginfo.ui.theme.BettingInfoTheme
import com.example.bettinginfo.vm.TopBarViewModel
import dev.olshevski.navigation.reimagined.AnimatedNavHost
import dev.olshevski.navigation.reimagined.NavAction
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.pop
import dev.olshevski.navigation.reimagined.rememberNavController
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val topBarState = rememberTopAppBarState()
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topBarState)
            val navController: NavController<ScreenDestination> = rememberNavController(
                startDestination = ScreenDestination.Sports
            )
            val topBarViewModel: TopBarViewModel = koinViewModel()
            LaunchedEffect(Unit) {
                onBackPressedDispatcher.addCallback {
                    if (navController.backstack.entries.size > 1) {
                        navController.pop()
                    } else {
                        finish()
                    }
                }
            }

            BettingInfoTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        TopBar(
                            topBarViewModel.uiState.collectAsStateWithLifecycle().value,
                            scrollBehavior = scrollBehavior
                        )
                    }
                ) { innerPadding ->
                    Box(Modifier.padding(top = innerPadding.calculateTopPadding())){
                        AnimatedNavHost(
                            controller = navController,
                            transitionSpec = { action, _, _ ->
                                val direction = if (action == NavAction.Pop) {
                                    AnimatedContentTransitionScope.SlideDirection.End
                                } else {
                                    AnimatedContentTransitionScope.SlideDirection.Start
                                }
                                ContentTransform(
                                    slideIntoContainer(direction),
                                    slideOutOfContainer(direction)
                                )
                            }
                        ) {
                            NavigationScreen(
                                navController,
                                PaddingValues(
                                    start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                                    end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                                    bottom = innerPadding.calculateBottomPadding()
                                ),
                                topBarViewModel,
                                it
                            ) {
                                topBarState.heightOffset = 0f
                                topBarState.contentOffset = 0f
                            }
                        }
                    }
                }
            }
        }
    }
}
