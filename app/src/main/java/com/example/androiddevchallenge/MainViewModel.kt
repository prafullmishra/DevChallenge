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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val catList: MutableList<OctoCat> = mutableListOf()

    private val _currentCat = MutableLiveData<OctoCat>()
    val currentCat: LiveData<OctoCat> = _currentCat

    private val _currentScreen = MutableLiveData<Screen>(Screen.DexMain)
    val currentScreen: LiveData<Screen> = _currentScreen

    private var currentIndex = 0

    init {
        generateCats()
    }

    private fun generateCats() {
        catList.addAll(
            listOf(
                OctoCat(
                    "https://octodex.github.com/images/droidtocat.png",
                    "Droidto Cat",
                    "Legend has it that this cat's OS is directly supported by Google, and will remain updated for more years than the Pixel phones!\n\nAfter all, it does not want to be depre-cat-ed :P",
                    "#78",
                    "By /tonyjaramillo"
                ),
                OctoCat(
                    "https://octodex.github.com/images/waldocat.png",
                    "Waldocat",
                    "Some developers think this OctoCat inspired the mobile-app bugs and made them hard to find. But fret not, Reactive programming and Declarative UI are here to help!",
                    "#52",
                    "By /jasoncostello"
                ),
                OctoCat(
                    "https://octodex.github.com/images/ironcat.jpg",
                    "Iron Cat",
                    "Famous line: 'I am Iron Cat'\n\nThis is what Github's mascot would look like if developed by Stark Industries.\n\nCool huh? It can even fly!",
                    "#24",
                    "By /cameronmcefee"
                ),
                OctoCat(
                    "https://octodex.github.com/images/daftpunktocat-guy.gif",
                    "Daftpunktocat",
                    "Who can't identify this masked alter ego, the one and only Daft Punk!\n\nLike every good thing, this too came to an end, but not before completing 28 glorious years\n\n1993-2021",
                    "#99",
                    "By /jeejkang"
                ),
                OctoCat(
                    "https://octodex.github.com/images/Robotocat.png",
                    "Robotocat",
                    "The cat over obsessed with the Roboto font. Not everyone likes it though, because not everyone is fond of its... type.\n\nHeh, get it?",
                    "#92",
                    "By /jeejkang"
                ),
                OctoCat(
                    "https://octodex.github.com/images/minion.png",
                    "Minion",
                    "It loves eating Papples and Gelato. Also likes to play with Baboi in its free time i.e. all the time.\n\nBi-do, you can't understand? Its what minions speak :D\n\nTulaliloo ti amo, Poopaye!",
                    "#79",
                    "By /nickh"
                ),
                OctoCat(
                    "https://octodex.github.com/images/plumber.jpg",
                    "Plumber",
                    "For the past 35 years, Mario has been running, jumping, and saving the Mushroom Kingdom from bad guys, and he couldn’t have done it without you. Thank you for always being there to help Mario on his incredible adventures.",
                    "#58",
                    "By /cameronmcefee"
                ),
                OctoCat(
                    "https://octodex.github.com/images/saritocat.png",
                    "Saritocat",
                    "The beautiful one from India! Complete with all the jewellery, Bindi, traditional costume and gentle smile on face. ",
                    "#112",
                    "By /JohnCreek"
                ),
                OctoCat(
                    "https://octodex.github.com/images/spidertocat.png",
                    "Spidertocat",
                    "You know what the spiders and octopuses have in common? Their love for number 8. This one likes to slingshot itself, instead of driving a car to cover distances.\n\nAppears to be environment conscious!",
                    "#87",
                    "By /jeejkang"
                ),
                OctoCat(
                    "https://octodex.github.com/images/pythocat.png",
                    "Pythocat",
                    "When Python met Github, this happened! Turns out Python aren't as dangerous as one might think.\n\nPopular with data community.",
                    "#12",
                    "By /cameronmcefee"
                ),
            )
        )
        _currentCat.value = catList[currentIndex]
    }

    /**
     * public methods
     */

    fun onBackPressed(): Boolean {
        val current = _currentScreen.value == Screen.DexMain
        _currentScreen.value = Screen.DexMain
        return current
    }

    fun openCatDetails() {
        _currentScreen.value = Screen.DexDetails(catList[currentIndex])
    }

    fun showNext() {
        if (currentIndex == catList.size - 1) currentIndex = 0
        else ++currentIndex
        _currentCat.value = catList[currentIndex]
    }

    fun showPrev() {
        if (currentIndex == 0) currentIndex = catList.size - 1
        else --currentIndex
        _currentCat.value = catList[currentIndex]
    }
}

data class OctoCat(
    val imageUrl: String,
    val name: String,
    val desc: String,
    val count: String,
    val creator: String
)
