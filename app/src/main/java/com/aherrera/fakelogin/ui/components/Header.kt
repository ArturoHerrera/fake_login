package com.aherrera.fakelogin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aherrera.fakelogin.R
import com.aherrera.fakelogin.ui.theme.BaubapPrimaryPurlple

@Composable
fun WelcomeHeader() {
    Surface(color = BaubapPrimaryPurlple) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baubap_logo),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )

            Image(
                painter = painterResource(id = R.drawable.onboarding_img),
                contentDescription = ""
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WelcomeHeaderPreview() {
    WelcomeHeader()
}