/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.androiddevchallenge.screen.DetailScreen
import com.example.androiddevchallenge.screen.OctoDex
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onBackPressed() {
        if (viewModel.onBackPressed()) {
            super.onBackPressed()
        }
    }

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val currentScreen: Screen by viewModel.currentScreen.observeAsState(initial = Screen.DexMain)
                MyApp(
                    currentScreen = currentScreen,
                    onBackPressed = { onBackPressed() },
                    viewModel = viewModel
                )
            }
        }
    }
}

sealed class Screen {
    object DexMain : Screen()
    class DexDetails(val cat: OctoCat) : Screen()
}

@Composable
fun MyApp(viewModel: MainViewModel, currentScreen: Screen = Screen.DexMain, onBackPressed: () -> Unit) {
    when (currentScreen) {
        is Screen.DexDetails -> {
            DetailScreen(octoCat = currentScreen.cat) {
                onBackPressed()
            }
        }
        is Screen.DexMain -> {
            OctoDex(openCatDetails = { viewModel.openCatDetails() }, viewModel = viewModel, showNext = { viewModel.showNext() }, showPrev = { viewModel.showPrev() })
        }
    }
}
