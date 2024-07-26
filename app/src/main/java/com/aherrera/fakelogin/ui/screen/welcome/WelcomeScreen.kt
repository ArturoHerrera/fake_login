package com.aherrera.fakelogin.ui.screen.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aherrera.fakelogin.R
import com.aherrera.fakelogin.ui.components.WelcomeHeader
import com.aherrera.fakelogin.ui.components.atoms.ButtonFilled
import com.aherrera.fakelogin.ui.components.atoms.ButtonText

@Composable
fun WelcomeScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize(),
        content = {
            WelcomeContent()
        }
    )
}

@Composable
private fun WelcomeContent(

) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        WelcomeHeader()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 32.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.welcome_tittle),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = stringResource(id = R.string.welcome_message),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                ButtonFilled(
                    onClick = {},
                    text = R.string.welcome_sign_up_button
                )

                ButtonText(
                    onClick = {},
                    text = R.string.welcome_login_button
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WelcomeContentPreview() {
    WelcomeContent()
}