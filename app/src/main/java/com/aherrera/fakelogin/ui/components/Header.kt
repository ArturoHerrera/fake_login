package com.aherrera.fakelogin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
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
import com.aherrera.fakelogin.ui.theme.BaubapLightBackground
import com.aherrera.fakelogin.ui.theme.BaubapPrimaryPurlple

@Composable
fun WelcomeHeader() {
    Surface(color = BaubapPrimaryPurlple) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 32.dp, top = 56.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baubap_logo),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.onboarding_img),
                contentDescription = ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeHeaderPreview() {
    WelcomeHeader()
}






@Composable
fun FormTopBar(
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .background(BaubapLightBackground)
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 40.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = "",
            modifier = Modifier.clickable { onClick() },
            tint = Color.Black,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun FormTopBarPreview() {
    FormTopBar({})
}