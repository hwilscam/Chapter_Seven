package com.example.chapter_three

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter_three.databinding.ActivityMainBinding
import java.util.Locale


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding

    private val questionBank = listOf(

    question(R.string.question_australia, true),
    question(R.string.question_oceans, true),
    question(R.string.question_mideast, false),
    question(R.string.question_africa, false),
    question(R.string.question_americas, true),
    question(R.string.question_asia, true)

    )

    private var currentIndex = 0
    private var numCorrect = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate(Bundle) called")
        Log.d("numCorrect", "value: $numCorrect")
        Log.d("currentIndex", "value: $currentIndex")


        binding.trueButton.setOnClickListener {
            binding.trueButton.isEnabled=false
            binding.falseButton.isEnabled=false
            checkAnswer(userAnswer = true)

        }

        binding.falseButton.setOnClickListener {
            binding.trueButton.isEnabled=false
            binding.falseButton.isEnabled=false
            checkAnswer(userAnswer = false)
        }
        updateQuestion()

        binding.questionTextView.setOnClickListener {
            binding.trueButton.isEnabled=true
            binding.falseButton.isEnabled=true
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        binding.nextButton.setOnClickListener {
            binding.trueButton.isEnabled=true
            binding.falseButton.isEnabled=true
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        binding.prevButton.setOnClickListener {
            binding.trueButton.isEnabled=true
            binding.falseButton.isEnabled=true
            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
        }

    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }


    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResID
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionBank[currentIndex].answer

        if (userAnswer == correctAnswer) {
            numCorrect++
        }
        if (currentIndex == questionBank.size - 1){
            showCorrect()
        }
        else {
            val messageResID = if (userAnswer == correctAnswer) {
                R.string.correct_toast
            } else {
                R.string.incorrect_toast
            }

            Toast.makeText(
                this,
                messageResID,
                Toast.LENGTH_SHORT,
            )
                .show()
        }

        }

    private fun showCorrect(){
        val score = (numCorrect.toDouble() / questionBank.size) * 100
        val percentageScore = String.format(Locale.getDefault(), "%.1f%%", score)

        Toast.makeText(
            this,
            "Your score: $percentageScore",
            Toast.LENGTH_LONG
        )
            .show()

        numCorrect = 0
        currentIndex = 0

    }

}




