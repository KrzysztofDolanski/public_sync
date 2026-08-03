package com.krzysztof.twopublicsync.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.krzysztof.twopublicsync.R

@Composable
fun UserAvatarScreen(
    navController: NavController,
    userViewModel: UserViewModel = viewModel()
) {
    val avatar = userViewModel.avatar.collectAsState()

    val avatars = listOf(
        R.drawable.ic_avatar_1,
        R.drawable.ic_avatar_2,
        R.drawable.ic_avatar_3,
        R.drawable.ic_avatar_4,
        R.drawable.ic_avatar_5
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Wybierz avatar",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Image(
            painter = painterResource(id = avatar.value),
            contentDescription = "Current avatar",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .padding(bottom = 32.dp)
        )

        avatars.forEach { avatarRes ->
            Image(
                painter = painterResource(id = avatarRes),
                contentDescription = "Avatar option",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .clickable {
                        userViewModel.changeAvatar(avatarRes)
                    }
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Zapisz i wróć")
        }
    }
}
