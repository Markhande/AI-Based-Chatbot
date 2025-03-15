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

        openAIService = OpenAIService("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")

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