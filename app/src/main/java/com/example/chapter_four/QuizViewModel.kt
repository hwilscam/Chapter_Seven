package com.example.chapter_four

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import java.util.Locale


private const val TAG = "QuizViewModel"

class QuizViewModel:ViewModel() {

    /*init {

        Log.d(TAG, "ViewModel Instance created")

    }

    override fun onCleared(){
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }

     */
   val questionBank = listOf(

        question(R.string.question_australia, true),
        question(R.string.question_oceans, true),
        question(R.string.question_mideast, false),
        question(R.string.question_africa, false),
        question(R.string.question_americas, true),
        question(R.string.question_asia, true)
    )

     var currentIndex = 0
     var numCorrect = 0

    val currentQuestionAnswer:Boolean
        get()= questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResID

    fun moveToNext(){

        currentIndex = (currentIndex + 1) % questionBank.size

    }

    fun resetIndex(){

        currentIndex = 0
        numCorrect = 0

    }

    fun correctCounter() {

        numCorrect++

    }

}


