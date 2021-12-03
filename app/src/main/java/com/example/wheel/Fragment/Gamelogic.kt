package com.example.wheel.Fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.*
import android.widget.Toast.LENGTH_SHORT
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wheel.Model.*
import com.example.wheel.R
import java.util.*
import com.example.wheel.databinding.GamelogicBinding


class Gamelogic : Fragment() {


    private var _binding: GamelogicBinding? = null
    private val binding get() = _binding!!
    var mMediaPlayer: MediaPlayer? = null
    private val RANDOM = Random()  // We create a Random instance to make our wheel spin randomly
    private var handler = Handler()  //create a new Handler it is bound to a Looper . It will deliver messages and runnables to that Looper's message queue and execute
    private var degree = 0
    private  var degreeOld = 0


    // We have 4 sectors on the wheel, we divide 360 by this value to have angle for each sector
    // we divide by 2 to have a half sector
    private val HALF_SECTOR = 360f / 4f / 2f


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = GamelogicBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onStart() {
        super.onStart()


        setLife(5)
        setScore(0)
        updateScoreAndLives()
        getARandomWord()
       binding.wordToBeGuessed.text = hiddenWord()
        rotationTheWheel()

        binding.showbutom.setOnClickListener {
            gameLogic()



        }
    }




    private fun playEffects(){
        mMediaPlayer = MediaPlayer.create(activity, R.raw.spinning )
        mMediaPlayer?.start()


        var musicPause= Runnable{
            mMediaPlayer!!.pause()
        }
        handler.postDelayed(musicPause, 3600)

    }



    private fun rotationTheWheel() {

        binding.apply {

            start.setOnClickListener {
                playEffects()
                updateScoreAndLives()

                degreeOld = degree % 360
                // we calculate random angle for rotation of our wheel
                degree = RANDOM.nextInt(360) + 720
                // rotation effect on the center of the wheel
               val  rotateAnim = RotateAnimation(
                    degreeOld.toFloat(), degree.toFloat(),
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f
                )
                rotateAnim.duration = 3600
                rotateAnim.fillAfter = true
                rotateAnim.interpolator = DecelerateInterpolator()
                rotateAnim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {
                        // we empty the result text view when the animation start
                        resultTv.text = ""
                    }
                    override fun onAnimationEnd(animation: Animation) {
                        // we display the correct sector pointed by the triangle at the end of the rotate animation
                        resultTv.text = getSector(360 - degree % 360)
                        //setSectors(getSector(360 - degree % 360).toString())
                    }
                    override fun onAnimationRepeat(animation: Animation) {
                    }

                })
                // we start the animation
                imageView.startAnimation(rotateAnim)
                showGuesseUI()

                var position= Runnable{
                    ResultsOfTheSpinningTheWheel()
                }
                handler.postDelayed(position, 2000)

            }
        }
    }


    private fun ResultsOfTheSpinningTheWheel() {


        val position = getSector(360 - degree % 360)

        when(position) {

            "bankrupt" -> {
                //In the event of “bankrupt” being shown, the user loses all their points.
                Toast.makeText(activity, "You loses all your points", LENGTH_SHORT).show()
                losesAllScores()
                updateScoreAndLives()

            }
            "1000 points" -> {
                //1000 points
                Toast.makeText(activity, "You got 1000 points", LENGTH_SHORT).show()
                plusScores()
                updateScoreAndLives()

            }
            "miss turn" ->{
                //In the event of “miss turn” being shown, the user loses a life without being able to choose a
                //letter
                Toast.makeText(activity, "You loses a life, and you not able to choose a letter", LENGTH_SHORT).show()
                // minusLife()
               minusLife()
                updateScoreAndLives()
                if (getLife() < 1){
                    lossThheGame()
                }
                hideGuesseUI()
                rotationTheWheel()

            }
            "extra turn" -> {
                //In the event of “extra turn” being shown, the user is given an extra life.
                Toast.makeText(activity, "You got an extra life", LENGTH_SHORT).show()
                plusLife()
                updateScoreAndLives()
            }


        }
    }


    private fun gameLogic(){
        binding.apply {

            if (editTextTextPersonName.text.trim().toString().isEmpty())
            {
                Toast.makeText(activity, "Please enter a letter", LENGTH_SHORT).show()
            }

            if (editTextTextPersonName.text.trim().toString().isNotEmpty()) {

                myLetter = editTextTextPersonName.text.toString()

                for (i in randomWord.indices ){

                   if (getLife() > 1){



                    if (randomWord.get(i) == myLetter.get(0) && hiddenWordArrays[i] != "#"){
                        messages = "This letter has already been used!"
                        Toast.makeText(activity, messages+" "+"try again", LENGTH_SHORT).show()

                        break
                    }
                    else if (randomWord.get(i) == myLetter.get(0) && hiddenWordArrays[i] == "#"){

                        getRandomscores()

                        messages = "You guessed correctly. You get 1000 DKK"
                        Toast.makeText(activity, messages, LENGTH_SHORT).show()
                        plusScores()
                        updateScoreAndLives()
                        wordToBeGuessed.text = updateHiddenWord()
                        hideGuesseUI()

                        if (!hiddenWordArrays.contains("#")){
                            winTheGame()
                        }
                    }
                    else if ( hiddenWordArrays[i].contains("#")   && (getLife() < 1)){

                        setLife(getLife()-1)
                        updateScoreAndLives()
                        start.visibility = View.VISIBLE
                        hideGuesseUI()
                        Toast.makeText(activity, "You've lost the game", LENGTH_SHORT).show()
                        lossThheGame()
                        break
                    }
                    else if ( !randomWord.contains(myLetter.get(0))  )  {
                        messages = "This letter does not exist! You loss one life"
                        Toast.makeText(activity, messages, LENGTH_SHORT).show()
                        wordToBeGuessed.text = updateHiddenWord()
                        setLife(getLife()-1)
                        updateScoreAndLives()
                        start.visibility = View.VISIBLE
                        break

                    }

                   }
                   else {
                       lossThheGame()
                   }

                }

            }
        }
    }



    private fun hideGuesseUI() {
        binding.apply {
            Toast.makeText(activity, getSectors(), LENGTH_SHORT).show()
            showbutom.visibility = View.GONE
            start.visibility = View.VISIBLE

            editTextTextPersonName.visibility = View.GONE

        }
    }

    private fun showGuesseUI() {
        binding.apply {
            Toast.makeText(activity, getSectors(), LENGTH_SHORT).show()
            //spinWheelAnim.visibility = View.GONE
            start.visibility = View.GONE
            //linearLayout.visibility = View.VISIBLE
            showbutom.visibility = View.VISIBLE
            editTextTextPersonName.visibility = View.VISIBLE
        }
    }


    private fun updateScoreAndLives() {
        binding.apply {
            scoreinput.text = "Score: ${getScore()}"
            playerLives.text = "Lives: ${getLife()}"

        }
    }


    private fun winTheGame(){

        findNavController().navigate(R.id.winFragment)
    }
    private fun lossThheGame(){
        findNavController().navigate(R.id.lossFragmentt)
    }



    private fun getSector(degrees: Int): String? {
        var i = 0
        var text: String? = null
        var score : String? = null

        do {
            // start and end of each sector on the wheel
            val start: Float = HALF_SECTOR * (i * 2 + 1)
            val end: Float = HALF_SECTOR * (i * 2 + 3)
            if (degrees >= start && degrees < end) {
                // degrees is in [start;end[
                // so text is equals to sectors[i];
                text = sectors.get(i)
                score = sectorsint.get(i)

            }
            i++
            // now we can test our Android Roulette Game :)
            // That's all !
        } while (text == null && score == null && i < sectors.size)


      //  val myStringNumber: String = score.toString()
        //val number: Int = myStringNumber.toInt()

        binding.apply {
           //setScore(getScore() +number)


            if (score!=null){
                (score.toString()).toInt()
                updateScore(getScore() + (score.toString()).toInt())
            }else {
                score = 1.toString()
                updateScore(getScore() + (score.toString()).toInt())
            }



           // scoreinput.text = (getScore()).toString()
            scoreinput.text = "Score: ${getScore()}"




        }

        return text
    }









}






