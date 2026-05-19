package com.smartgeek.intortocompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
                    .fillMaxSize(),
                    color = Color.Blue,){
                    Column(modifier = Modifier
                        .fillMaxSize()) {
//                        MyApp()
//                        CreateCircle()
                        GreetingPreview()
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val messageCount = remember { mutableIntStateOf(0) }
    Surface(modifier = Modifier
        .padding(15.dp),
        color = Color.Green) {
        Text(text = "$ ${messageCount.intValue}",
            textAlign = TextAlign.Center,
            color = Color.LightGray,
            fontSize = 42.sp)
    }

    CreateCircle(messageCount = messageCount.intValue){
        messageCount.intValue = it + 1
    }
}

@Composable
fun CreateCircle(messageCount: Int = 0, updatedCount: (Int) -> Unit) {
    var toastMessage by remember { mutableStateOf<String?>(null) }
    val messageList = remember { mutableStateListOf<String>() }

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
                    updatedCount(messageCount)
//                    toastMessage = "Button Clicked for $messageCount"
//                    toastMessage?.let { messageList.add(toastMessage!!) }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Tap $messageCount", color = Color.Black)
        }
    }

    Spacer(modifier = Modifier.height(20.dp))

    if (messageCount == 15){
        Text(text = "Lots of money")
    }

//    LazyColumn {
//        items(messageList) { message ->
//            Text(text = message)
//        }
//    }

    // ✅ Only show toast when message is not null
//    toastMessage?.let {
//        CustomToast(
//            message = it,
//            onShown = { toastMessage = null } // ✅ reset state
//        )
//    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntorToComposeTheme {
        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            MyApp()
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
//    IntorToComposeTheme {
//        Column {
//            Greeting("Abhishek")
//            ShowAge(age = 27)
//        }
//    }
//}