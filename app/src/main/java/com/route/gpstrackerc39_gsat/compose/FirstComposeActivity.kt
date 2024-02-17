package com.route.gpstrackerc39_gsat.compose

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.route.gpstrackerc39_gsat.R
import com.route.gpstrackerc39_gsat.compose.ui.theme.GPSTrackerC39GSatTheme
import com.route.gpstrackerc39_gsat.compose.ui.theme.black

//                               AppCompatActivity -> Component Activity
//                             setContentView(R.layout.first_compose_activity)
class FirstComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GPSTrackerC39GSatTheme {
                // A surface container using the 'background' color from the theme
                HelloWorldToUser()
            }
        }
    }
}

// Jetpack Compose component
// Reusability  - Declarative UI
@Composable
fun HelloWorldToUser() {                   // Opacity
    // Recomposition
    // Column
    // Row

    LazyColumn {
        items(20) {
            FacebookTask()
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FacebookPreviewTask() {
    FacebookTask()
}

@Composable
fun FacebookTask() {
    ConstraintLayout(modifier = Modifier.padding(16.dp)) {
        val (username, userImage, time, description, postImage, like, comment, share) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.ic_android),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(userImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .size(50.dp)
                .clip(CircleShape),

            )
        Text(text = "Mohamed", modifier = Modifier.constrainAs(username) {
            top.linkTo(userImage.top)
            start.linkTo(userImage.end)
            bottom.linkTo(time.top)
        })
        Text(text = "12:00PM", modifier = Modifier.constrainAs(time) {
            top.linkTo(username.bottom)
            start.linkTo(userImage.end)
            bottom.linkTo(userImage.bottom)
        })
        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle()) {
                    append("Buckle up , because changes are coming to wordpress ")
                }
                withStyle(SpanStyle(color = Color.Blue)) {
                    append("https://stackoverflow.com/questions/65567412/jetpack-compose-text-hyperlink-some-section-of-the-text")
                }
            },
            modifier = Modifier.constrainAs(description) {
                top.linkTo(userImage.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Image(
            painter = painterResource(id = R.drawable.ic_android),
            contentDescription = "Post image", modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1F)

                .constrainAs(postImage) {
                    top.linkTo(description.bottom)
                }, contentScale = ContentScale.Crop
        )
        FacebookReactionButton(modifier = Modifier
            .constrainAs(like) {
                top.linkTo(postImage.bottom)
                start.linkTo(parent.start)
            }
            .fillMaxWidth(0.33F),
            icon = R.drawable.ic_like,
            text = stringResource(R.string.like))
        FacebookReactionButton(
            icon = R.drawable.ic_comment,
            text = stringResource(R.string.comment),
            modifier = Modifier
                .constrainAs(comment) {
                    start.linkTo(like.end)
                    top.linkTo(like.top)
                    bottom.linkTo(like.bottom)

                }
                .fillMaxWidth(0.33F)
        )
        FacebookReactionButton(
            icon = R.drawable.ic_share,
            text = stringResource(R.string.share),
            modifier = Modifier
                .constrainAs(share) {
                    start.linkTo(comment.end)
                    top.linkTo(like.top)
                    bottom.linkTo(like.bottom)
                }
                .fillMaxWidth(0.33F)
        )
    }
}

@Composable
fun FacebookReactionButton(modifier: Modifier = Modifier, icon: Int, text: String) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .border(1.dp, black, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp)
            .clickable {

            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "like icon",
            modifier = Modifier.size(30.dp)
        )
        Text(text = text, fontSize = 11.sp)
    }
}

@Composable
fun IslamiBottomAppBar() {
    BottomAppBar() {
        val selectedIndex = remember {
            mutableStateOf(0)
        }
        val list: List<String> = mutableListOf()
        list.forEach {
//            NavigationBarItem(selected =, onClick = { }, icon = {
//                Column {
//
//
//                }
//            })
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun IslamiBottomAppBarPreview() {
    IslamiBottomAppBar()
}

@Composable
fun SettingsItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Gray, RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_android),
            contentDescription = "icon settings",
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .size(50.dp)

        )
        Column {
            Text(
                text = "Settings Title",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 6.dp)
            )
            Text(text = "Settings subtitle", fontSize = 12.sp)
        }
    }
}

@Composable
fun GreetingUser() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Hello World",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(20.dp)  // first
                .background(Color.Black) // second
                .padding(36.dp)
                .wrapContentSize()
                .clickable {
                    Log.e("Hello", "World")
                },
        )
        Button(
            onClick = {
                Log.e("TAG", "HelloWorldToUser: ")
            }, modifier = Modifier
                .padding(20.dp),
//            colors = ButtonDefaults.buttonColors(),
            shape = RoundedCornerShape(8.dp)
        ) {
            // Click me
            Text(text = "Click me!")
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun GreetingPreview2() {
    GPSTrackerC39GSatTheme {
//        Greeting("Android")
        HelloWorldToUser()
    }
}

//@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun GreetingPreview() {
//    GPSTrackerC39GSatTheme {
//        Greeting("Android")
//        HelloWorldToUser()
//    }
//}