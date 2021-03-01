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
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.OctoCat
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.glassDarkBlue
import com.example.androiddevchallenge.ui.theme.plasticRed
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DetailScreen(octoCat: OctoCat, onBackPressed: () -> Unit) {
    Column(Modifier.background(plasticRed)) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            FLippedDexBackgroundShape()
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_graphic_eq_24),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(end = 24.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(24.dp)
                .background(Color.White)
                .border(4.dp, Color.Black)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            CoilImage(
                data = octoCat.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = octoCat.name,
                fontFamily = retroFont,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontFamily = FontFamily.Monospace,
                text = octoCat.desc
            )
            Spacer(modifier = Modifier.height(48.dp))
            Row(Modifier.fillMaxWidth()) {
                Text(text = octoCat.count, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = octoCat.creator)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        Row(
            Modifier
                .height(100.dp)
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            Button(
                onClick = { onBackPressed() },
                colors = ButtonDefaults.buttonColors(backgroundColor = glassDarkBlue),
                content = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_keyboard_arrow_left_24),
                        contentDescription = null,
                        modifier = Modifier.size(56.dp)
                    )
                },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(3.dp, Color.Black),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
            Spacer(Modifier.width(24.dp))
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                content = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_round_done_24),
                        contentDescription = null,
                        modifier = Modifier.size(56.dp)
                    )
                },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(3.dp, Color.Black),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f)
            )
        }
    }
}

@Composable
fun FLippedDexBackgroundShape() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        onDraw = {

            drawPath(
                Path().apply {
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height)
                    lineTo(size.width * 0.65f, size.height)
                    lineTo(size.width * 0.5f, size.height * 0.5f)
                    lineTo(0f, size.height * 0.5f)
                    close()
                },
                color = Color.Black
            )

            drawPath(
                Path().apply {
                    lineTo(size.width, 0f)
                    lineTo(size.width, size.height * 0.96f)
                    lineTo(size.width * 0.65f, size.height * 0.96f)
                    lineTo(size.width * 0.5f, size.height * 0.5f * 0.92f)
                    lineTo(0f, size.height * 0.5f * 0.92f)
                    close()
                },
                color = Color.Red
            )

            drawCircle(Color.Black, radius = 11.dp.toPx(), center = Offset(size.width * 0.1f, size.height * 0.8f))
            drawCircle(Color.Yellow, radius = 8.dp.toPx(), center = Offset(size.width * 0.1f, size.height * 0.8f))
        }
    )
}
