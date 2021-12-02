package com.example.wheel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.view.animation.RotateAnimation
import android.widget.ImageView
import java.util.*


class InitializationActivity : AppCompatActivity() {

    var handler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.initializationactivity)

        NextActivity()

    }


    private fun NextActivity() {
        rotatLogo()
        handler = Handler()
        handler!!.postDelayed(Runnable {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, 1000)

    }


    private fun rotatLogo() {

        var rotat1 = 0
        var old= 0

        val RANDOM = Random()

        val image: ImageView = findViewById(R.id.loadingicon)

        old = rotat1 % 360
        // we calculate random angle for rotation of our wheel
        rotat1 = RANDOM.nextInt(360) + 720
        // rotation effect on the center of the wheel
        val rotateAnim = RotateAnimation(
            old.toFloat(), rotat1.toFloat(),
            RotateAnimation.RELATIVE_TO_SELF, 0.5F, RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnim.duration = 2600
        rotateAnim.repeatCount =2
        rotateAnim.fillAfter = true
        image.startAnimation(rotateAnim)

    }




}