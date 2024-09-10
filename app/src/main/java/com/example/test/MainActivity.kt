package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.democlass.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

class MainActivity_1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val username = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "    ")
                Text(text = "    ")

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "                                                               ")

                    Image(
                        painter = painterResource(id = R.drawable.netflix),
                        contentDescription = "Cat Image",
                        modifier = Modifier.size(200.dp, 80.dp)
                    )

                    Text(text = " ")

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Home Icon",
                        modifier = Modifier
                            .size(40.dp, 80.dp)
                            .background(Color.Black),
                        tint = Color.White
                    )
                }
                Text(text = "  ")
                Text(text = "  ")
                Text(text = "  ")
                Text(text = "  ")
                Text(text = "  ")

                TextField(
                    value = username.value,
                    onValueChange = { username.value = it },
                    modifier = Modifier.size(500.dp, 100.dp),
                    label = {
                        Text(text = "اسم المستخدم ",
                            fontSize = 20.sp  )
                    }
                )
                Text(text = "  ")
                Text(text = "  ")

                TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    modifier = Modifier.size(500.dp, 100.dp),
                    label = {
                        Text(text = "كلمة المرور",
                            fontSize = 20.sp)
                    }
                )
                Text(text = "  ")
                Text(text = "  ")

                Button(
                    onClick = {
                        // Coroutine to send the login request
                        CoroutineScope(Dispatchers.IO).launch {
                            login(username.value, password.value)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier
                        .border(width = 2.dp, color = Color.White)
                        .size(500.dp, 100.dp)
                ) {
                    Text(text = "تسجيل الدخول",
                        fontSize = 20.sp)
                }

                Text(text = "  ")
                Text(text = "  ")
                Text(text = " أو", color =Color.White ,
                    fontSize = 20.sp)
                Text(text = "  ")
                Text(text = " ")


                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black),
                    modifier = Modifier
                        .border(width = 2.dp, color = Color.White)
                        .size(500.dp, 100.dp))
                {
                    Text(text = "إستخدام رمز تسجيل الدخول ",
                        fontSize = 20.sp)
                }

                Text(text = " ")
                Text(text = " ")

                Text( text = "هل نسيت كلمة المرور ؟ " ,
                    fontSize = 20.sp
                    ,color=Color.White)
                Text(text = "")
                Text(text = " ")
                Text(text = "؟ سجل الآنNETFILIX جديد على ",
                    fontSize = 20.sp
                    ,color=Color.White
                    , fontWeight = FontWeight.Bold)

                Text(text = "")
                Text(text = " ")
                Text(text = " ")

                Text( text = " Google من reCAPTCHA سجل الدخول محمي بواسطة خدمة " ,
                    fontSize = 20.sp
                    ,color=Color.White,
                )

                Text( text = "لضمان أنك لست آليا. تعرف علي المزيد " ,
                    fontSize = 20.sp
                    ,color=Color.White)


            }
        }
    }


    private fun login(username: String, password: String) {
        val client = OkHttpClient()

        // JSON body
        val jsonBody = JSONObject().apply {
            put("username", username)
            put("password", password)
        }

        val requestBody = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            jsonBody.toString()
        )

        val request = Request.Builder()
            .url("https://dummyjson.com/auth/login")
            .post(requestBody)
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace() // Print the error to console
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.let { responseBody ->
                        val jsonResponse = JSONObject(responseBody.string())
                        val token = jsonResponse.getString("token")
                        println("Received Token: $token") // Print the token to console
                    }
                } else {
                    println("Request failed: ${response.code}")
                }
            }
        })
    }
}
