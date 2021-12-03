package com.example.wheel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.wheel.Fragment.Gamelogic

class info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val text: TextView = findViewById(R.id.gameinstructions)

        text. setText(" Game instructions:\n\n"+
                    "1. The game is for one player.\n"  +
                    "2. You start with 5 “lives\n"+
                    "3. The game starts, a word is randomly chosen from predefined categories.\n" +
                    "4. The word base on colors names, there is 7 colors.\n" +
                    "5. In the event of “extra turn” being shown, you given an extra life.\n" +
                    "6. In the event of “miss turn” being shown, you loses a life without being able to choose a letter.\n +" +
                    "7. In the event of “bankrupt” being shown, you loses all their points.\n" +
                    "8. The “wheel is spun” until the game is won or lost .\n"

                     )







        val startgame: Button = findViewById(R.id.startthegame)
        startgame.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}