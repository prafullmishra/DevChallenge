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
package com.example.androiddevchallenge.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.OctoCat
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.displayGreen
import com.example.androiddevchallenge.ui.theme.glassBlue
import com.example.androiddevchallenge.ui.theme.glassDarkBlue
import com.example.androiddevchallenge.ui.theme.glassLightBlue
import com.example.androiddevchallenge.ui.theme.plasticRed
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun OctoDex(viewModel: MainViewModel, openCatDetails: () -> Unit, showNext: () -> Unit, showPrev: () -> Unit) {
    val cat by viewModel.currentCat.observeAsState()
    cat?.let { MainContent(cat = it, onCatSelected = openCatDetails, showNext, showPrev) }
}

@Composable
fun MainContent(cat: OctoCat, onCatSelected: () -> Unit, showNext: () -> Unit, showPrev: () -> Unit) {
    Column(Modifier.background(plasticRed)) {
        // Top
        Box(contentAlignment = Alignment.CenterStart) {
            DexBackgroundShape()
            Row() {
                Spacer(modifier = Modifier.width(16.dp))
                Canvas(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    onDraw = {
                        drawCircle(Color.White)
                        drawCircle(color = glassBlue, radius = size.height * 0.45f, center = Offset(size.width * 0.5f, size.height * 0.5f))
                        drawCircle(color = glassDarkBlue, radius = size.height * 0.37f, center = Offset(size.width * 0.53f, size.height * 0.53f))
                        drawCircle(color = glassBlue, radius = size.height * 0.22f, center = Offset(size.width * 0.35f, size.height * 0.35f))
                        drawCircle(color = glassLightBlue, radius = size.height * 0.1f, center = Offset(size.width * 0.32f, size.height * 0.32f))
                    }
                )

                Spacer(modifier = Modifier.width(12.dp))
                Canvas(
                    modifier = Modifier.size(16.dp),
                    onDraw = {
                        drawCircle(Color.Black)
                        drawCircle(Color.Red, radius = size.height * 0.37f)
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))
                Canvas(
                    modifier = Modifier.size(16.dp),
                    onDraw = {
                        drawCircle(Color.Black)
                        drawCircle(Color.Yellow, radius = size.height * 0.37f)
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))
                Canvas(
                    modifier = Modifier.size(16.dp),
                    onDraw = {
                        drawCircle(Color.Black)
                        drawCircle(Color.Green, radius = size.height * 0.37f)
                    }
                )
            }
        }

        // Middle
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(40.dp, 48.dp)
        ) {
            MetalFrameShape()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                // https://octodex.github.com/images/waldocat.png
                CoilImage(
                    data = cat.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(16.dp)
                        .border(5.dp, Color.Black)
                        .background(Color.White)
                        .padding(8.dp)
                ) {}
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Spacer(Modifier.width(24.dp))
                    Canvas(
                        modifier = Modifier.size(20.dp),
                        onDraw = {
                            drawCircle(Color.Black)
                            drawCircle(Color.Red, radius = size.height * 0.37f)
                        }
                    )
                    Spacer(Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.ic_round_reorder_32),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(Modifier.width(20.dp))
                }
            }
        }

        // Bottom
        Surface(
            color = Color.Black,
            modifier = Modifier
                .height(6.dp)
                .fillMaxWidth()
        ) {}
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.Red)
                .heightIn(100.dp, 200.dp)
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Row() {
                    Surface(
                        color = glassBlue,
                        shape = RoundedCornerShape(3.dp),
                        modifier = Modifier
                            .height(8.dp)
                            .weight(1f),
                        border = BorderStroke(2.dp, Color.Black)
                    ) {}
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        color = plasticRed,
                        shape = RoundedCornerShape(3.dp),
                        modifier = Modifier
                            .height(8.dp)
                            .weight(1f),
                        border = BorderStroke(2.dp, Color.Black)
                    ) {}
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            3.dp, Color.Black, RoundedCornerShape(8.dp)
                        )
                ) {
                    Surface(
                        color = displayGreen,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = cat.name,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            fontSize = 24.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            fontFamily = retroFont,
                            modifier = Modifier.padding(12.dp, 24.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Row(Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { showPrev() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                        content = {
                            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_keyboard_arrow_left_24), contentDescription = null)
                        },
                        border = BorderStroke(3.dp, Color.Black),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(12.dp))
                    Button(
                        onClick = { showNext() },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                        content = {
                            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_keyboard_arrow_right_24), contentDescription = null)
                        },
                        border = BorderStroke(3.dp, Color.Black),
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = { onCatSelected() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                    content = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_catching_pokemon_24),
                            contentDescription = null,
                            modifier = Modifier.size(56.dp),
                            tint = Color.White
                        )
                    },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(3.dp, Color.Black),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun DexBackgroundShape() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        onDraw = {

            drawPath(
                Path().apply {
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height * 0.5f)
                    lineTo(size.width * 0.65f, size.height * 0.5f)
                    lineTo(size.width * 0.5f, size.height)
                    lineTo(0f, size.height)
                    close()
                },
                color = Color.Black
            )

            drawPath(
                Path().apply {
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height * 0.9f * 0.5f)
                    lineTo(size.width * 0.645f, size.height * 0.9f * 0.5f)
                    lineTo(size.width * 0.495f, size.height * 0.95f)
                    lineTo(0f, size.height * 0.95f)
                    close()
                },
                color = Color.Red
            )
        }
    )
}

@Composable
fun MetalFrameShape() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        onDraw = {

            drawPath(
                Path().apply {
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height)
                    lineTo(32.dp.toPx(), size.height)
                    lineTo(0f, size.height - 32.dp.toPx())
                    close()
                },
                color = Color.Black
            )

            drawPath(
                Path().apply {
                    moveTo(6.dp.toPx(), 6.dp.toPx())
                    lineTo(size.width - 6.dp.toPx(), 6.dp.toPx())
                    lineTo(size.width - 6.dp.toPx(), size.height - 6.dp.toPx())
                    lineTo(32.dp.toPx() + 3.dp.toPx(), size.height - 6.dp.toPx())
                    lineTo(6.dp.toPx(), size.height - 32.dp.toPx() - 3.dp.toPx())
                    close()
                },
                color = Color.LightGray
            )
        }
    )
}

val retroFont = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.connection
        )
    )
)
