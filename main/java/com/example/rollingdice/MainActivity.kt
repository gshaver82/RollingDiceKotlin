package com.example.rollingdice

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rollingdice.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            val result = rollDice(rollButton)
            rollButton.text = "Rolling Dice now"
//            Toast.makeText(this, result + " rolled on the dice!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun rollDice(buttonText: TextView): String {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val diceImage: ImageView = findViewById(R.id.imageView)
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        for (i in 1..9) {
            val delay = i*1000
            Handler(Looper.getMainLooper()).postDelayed({
                val spinPic = when ((0..6).random()) {
                    1 -> R.drawable.dice_1
                    2 -> R.drawable.dice_2
                    3 -> R.drawable.dice_3
                    4 -> R.drawable.dice_4
                    5 -> R.drawable.dice_5
                    else -> R.drawable.dice_6
                }
                diceImage.setImageResource(spinPic)
                buttonText.text = "Rolling Dice " + i
            }, delay.toLong())
        }
        Handler(Looper.getMainLooper()).postDelayed({
            diceImage.setImageResource(drawableResource)
            buttonText.text = "Roll again?"
            Toast.makeText(this, diceRoll.toString() + " rolled on the dice!", Toast.LENGTH_SHORT).show()
        }, 10000)
        return (diceRoll.toString())
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}

