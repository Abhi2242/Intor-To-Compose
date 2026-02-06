package com.smartgeek.intortocompose

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartgeek.intortocompose.ui.theme.IntorToComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntorToComposeTheme {
                Surface(modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxSize(),
                    color = Color.Blue) {
                    Column(modifier = Modifier
                        .fillMaxSize()) {
                        GreetingPreview()
                    }
                }
            }
        }
    }
}

//@Composable
//fun MyApp() {
//    Surface(modifier = Modifier
//        .padding(15.dp),
//        color = Color.Green) {
//        Text(text = "Hello!",
//            textAlign = TextAlign.Center,
//            color = Color.LightGray,
//            fontSize = 42.sp)
//    }
//}

@Composable
fun CreateCircle() {
    var toastMessage by remember { mutableStateOf<String?>(null) }
    var clickCounter by remember { mutableStateOf(0) }

    Text(text = clickCounter.toString(), fontSize = 26.sp)

    Card(
        modifier = Modifier
            .padding(10.dp)
            .size(100.dp),
        colors = CardDefaults.cardColors(Color.Cyan),
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    clickCounter += 1
                    Log.d("Click Count", "$clickCounter")
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Tap", color = Color.Black)
        }
    }

    // ✅ Only show toast when message is not null
    toastMessage?.let {
        CustomToast(
            message = it,
            onShown = { toastMessage = null } // ✅ reset state
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntorToComposeTheme {
        Column(modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
//            MyApp()
            CreateCircle()
        }
    }
}

/** Side-effects functions */

@Composable
fun CustomToast(
    message: String,
    onShown: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        onShown()
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier,
//        color = Color.Black
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ShowAge(age: Int = 12) {
//    Text(text = age.toString(),
//        color = Color.Black
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    IntroToComposeTheme {
//        Column {
//            Greeting("Abhishek")
//            ShowAge(age = 27)
//        }
//    }
//}