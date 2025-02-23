package com.example.chapter_four

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.util.Locale


private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val NUM_CORRECT_KEY = "NUM_CORRECT_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle):ViewModel() {

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

    var currentIndex
        get() = savedStateHandle.get(CURRENT_INDEX_KEY)?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    var numCorrect
        get() = savedStateHandle.get(NUM_CORRECT_KEY)?: 0
        set(value) = savedStateHandle.set(NUM_CORRECT_KEY, value)


    val currentQuestionAnswer:Boolean
        get()= questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResID

    fun moveToNext() {

        currentIndex = (currentIndex + 1) % questionBank.size

    }

    fun moveToPrevious(){

        currentIndex = (currentIndex - 1) % questionBank.size

    }

    fun resetIndex(){

        currentIndex = 0
        numCorrect = 0

    }

    fun correctCounter() {

        numCorrect++

    }

}


