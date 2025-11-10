package com.example.demoscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RatingWithAvatars(
    modifier: Modifier = Modifier,
    starCount: Int = 5,
    avatarSize: Dp = 36.dp,
    avatarOverlap: Dp = 24.dp,
    starIcon: Int = R.drawable.ic_feedback_star,
    avatars: List<Int> = listOf(
        R.drawable.img_user_1,
        R.drawable.img_user_2,
        R.drawable.img_user_3,
        R.drawable.img_user_4
    ),
    avatarBackgrounds: List<Color> = listOf(
        Color(0xFFE0E0E0),
        Color(0xFFD6E2C0),
        Color(0xFFFFECB3),
        Color(0xFFBBDEFB)
    )
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        // ⭐ Rating Stars
        repeat(starCount) {
            Icon(
                painter = painterResource(id = starIcon),
                contentDescription = "Star",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // 👤 Overlapping Avatars
        Box {
            avatars.forEachIndexed { index, avatarRes ->
                Box(
                    modifier = Modifier
                        .size(avatarSize)
                        .offset(x = (index * avatarOverlap.value).dp)
                        .clip(CircleShape)
                        .background(avatarBackgrounds.getOrElse(index) { Color.LightGray }),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = avatarRes),
                        contentDescription = "Avatar $index",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RatingWithAvatarsPreview() {
    RatingWithAvatars()
}
