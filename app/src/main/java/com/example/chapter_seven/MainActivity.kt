package com.example.chapter_seven

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter_seven.databinding.ActivityMainBinding
import java.util.Locale


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()
    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            quizViewModel.isCheater =
                result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
    }
}
    // private var numCorrect = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate(Bundle) called")
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

        updateQuestion()

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

        binding.questionTextView.setOnClickListener {
            binding.trueButton.isEnabled=true
            binding.falseButton.isEnabled=true
            // currentIndex = (currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()
            updateQuestion()
        }

        binding.nextButton.setOnClickListener {

            binding.trueButton.isEnabled=true
            binding.falseButton.isEnabled=true
            quizViewModel.isCheater=false

            // currentIndex = (currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()
            updateQuestion()
        }

        binding.cheatButton.setOnClickListener {
            // start Cheat Activity
           // val intent = Intent(this,CheatActivity::class.java)
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)

            // startActivity(intent)
            cheatLauncher.launch(intent)

        }

        /** binding.prevButton.setOnClickListener {
            binding.trueButton.isEnabled=true
            binding.falseButton.isEnabled=true
            // currentIndex = (currentIndex - 1) % questionBank.size
            quizViewModel.moveToPrevious()
            updateQuestion()
        } **/

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
        // val questionTextResId = questionBank[currentIndex].textResID
        Log.d(TAG, "Current Index: ${quizViewModel.currentIndex}")
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean){
        val currentIndex = quizViewModel.currentIndex
        val correctAnswer = quizViewModel.currentQuestionAnswer

        //val correctAnswer = quizViewModel.currentQuestionAnswer

        /**val messageResID = if (userAnswer == correctAnswer) {
            quizViewModel.correctCounter()
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }**/

        val messageResId = when {
            quizViewModel.isCheater -> R.string.judgement_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

        if (currentIndex == quizViewModel.questionBank.size - 1) {
            showCorrect()
        }
    }

    private fun showCorrect(){
        val numCorrect = quizViewModel.numCorrect
        val questionBank = quizViewModel.questionBank
        val score = (numCorrect.toDouble() / questionBank.size) * 100
        val percentageScore = String.format(Locale.getDefault(), "%.1f%%", score)

        Toast.makeText(
            this,
            "Your score: $percentageScore",
            Toast.LENGTH_LONG
        )
            .show()

        quizViewModel.resetIndex()

    }

}




