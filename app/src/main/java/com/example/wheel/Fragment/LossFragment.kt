package com.example.wheel.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.wheel.R
import com.example.wheel.databinding.FragmentLossBinding
import android.animation.Animator

import android.animation.AnimatorListenerAdapter
import android.widget.TextView
import androidx.core.graphics.alpha
import androidx.navigation.fragment.findNavController
import com.example.wheel.Model.*
import java.lang.Integer.rotateLeft
import java.util.Collections.rotate


class LossFragment : Fragment() {

    private var _bindding : FragmentLossBinding?= null
    private val binding get() = _bindding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _bindding = FragmentLossBinding.inflate(inflater ,container, false)
        val view = binding?.root
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()


        val animationZoomIn = AnimationUtils.loadAnimation(this.context,R.anim.myani)
        binding?.textViewFromloss?.startAnimation(animationZoomIn)

        binding?.lossAnimation?.visibility = View.VISIBLE
        binding?.hiddenwordfromloss?.text = "*"+ " "+ randomWord + " "+"*"



        binding?.PlayAgainFromLoss?.setOnClickListener {

            setLife(5)
            setScore(0)
            getARandomWord()
            findNavController().navigate(R.id.action_lossFragment_to_gamelogic)

        }


    }




}
