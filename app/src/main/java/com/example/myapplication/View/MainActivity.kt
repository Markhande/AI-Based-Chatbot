package com.example.myapplication.View

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var openAIService: OpenAIService
    private lateinit var tvChatOutput: TextView
    private lateinit var etInput: EditText
    private lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvChatOutput = findViewById(R.id.tvChatOutput)
        etInput = findViewById(R.id.etInput)
        btnSend = findViewById(R.id.btnSend)

//	for security reason moved api from this project
        openAIService = OpenAIService("Paste your API")


        btnSend.setOnClickListener {
            val inputText = etInput.text.toString().trim()
            if (inputText.isNotEmpty()) {
                tvChatOutput.append("\nYou: $inputText")

                openAIService.sendMessage(inputText) { response ->
                    runOnUiThread {
                        tvChatOutput.append("\nBot: $response\n")
                        etInput.text.clear()
                    }
                }
            }
        }

    }
}