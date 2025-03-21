package com.example.chapter_seven

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter_seven.databinding.ActivityCheatBinding


private const val EXTRA_ANSWER_IS_TRUE = "com.example.chapter_four.answer_is_true"
const val EXTRA_ANSWER_SHOWN = "com.example.chapter_four.answer_shown"


class CheatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheatBinding

    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_cheat)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        binding.showAnswerButton.setOnClickListener {

            val answerText = when {
                answerIsTrue -> "True"
                else -> "False"
            }
            binding.answerTextView.setText(answerText)

            setAnswerShownResult(true)
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {

        val data = Intent().apply {

            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }


    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}

