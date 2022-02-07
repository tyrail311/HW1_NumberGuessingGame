package com.example.hw1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val LOWER_BOUND = 1
    private val UPPER_BOUND = 100
    private var tries = 0
    private var win = false;
    var number = randomNumber()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private fun randomNumber():Int = Random.nextInt(LOWER_BOUND, UPPER_BOUND)

    private fun checkWin() {
        if(win) {
            play_again_button.text = "Play Again"
            tries = 0
            win = false
        }
    }
    fun displayTries(tries : Int) : String
    {
        return "Number of Tries: $tries"
    }

    fun checkGuess(view: View) {
        var guess = guess_input.text.toString().toIntOrNull()
        play_again_button.text = "Guess"
        guess_input.hideKeyboard()
        guess_input.setText("")
        if (guess != null) {
            when {
                guess < number || win -> {
                    tries++
                    tries_text_view.text = displayTries(tries)
                    hint_textview.text = "Hint: Up!"
                }
                guess > number || win -> {
                    tries++
                    tries_text_view.text = displayTries(tries)
                    hint_textview.text = "Hint: Down!"
                }
                else -> {
                    win = true
                    hint_textview.text = "You won! $number is correct!"
                    checkWin()
                    number = randomNumber()
                }
            }
        }
        else {
            Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show()
        }
    }
    private fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}