package com.example.chapter_two

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter_two.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton : Button
    private lateinit var falseButton : Button

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // trueButton = findViewById(R.id.true_button)
        // falseButton = findViewById(R.id.false_button)

        binding.trueButton.setOnClickListener {
            /* Toast.makeText(
                this,
                R.string.correct_toast,
                Toast.LENGTH_SHORT
            )
                .show() */

            checkAnswer(userAnswer = true)
        }

        binding.falseButton.setOnClickListener {
            /* Toast.makeText(
                this,
                R.string.incorrect_toast,
                Toast.LENGTH_SHORT
            )
                .show() */

            checkAnswer(userAnswer = false)
        }

        binding.questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            //val questionTextResId = questionBank[currentIndex].textResID
            //binding.questionTextView.setText(questionTextResId)
            updateQuestion()
        }

        binding.prevButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            //val questionTextResId = questionBank[currentIndex].textResID
            //binding.questionTextView.setText(questionTextResId)
            updateQuestion()
        }

        updateQuestion()

    }

    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResID
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionBank[currentIndex].answer
        val messageResID = if (userAnswer  == correctAnswer) {
            R.string.correct_toast
        } else{
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