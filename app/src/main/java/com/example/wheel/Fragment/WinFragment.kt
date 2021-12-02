package com.example.wheel.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.wheel.Model.*
import com.example.wheel.R
import com.example.wheel.databinding.FragmentLossBinding
import com.example.wheel.databinding.FragmentWinBinding

class WinFragment : Fragment() {

    private var _bindding :FragmentWinBinding ?= null
    private val binding get() = _bindding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _bindding = FragmentWinBinding.inflate(inflater ,container, false)
        val view = binding?.root
        return view
    }


    override fun onStart() {
        super.onStart()

        val animationZoomIn = AnimationUtils.loadAnimation(this.context,R.anim.myani)
        binding?.textViewFromWin?.startAnimation(animationZoomIn)

        binding?.winAnimation?.visibility = View.VISIBLE
        binding?.hiddenwordfromwin?.text = "*"+ " "+ updateHiddenWord()+ " "+"*"


        binding?.PlayAgainFromWin?.setOnClickListener {

            setLife(5)
            setScore(0)
            getARandomWord()

            findNavController().navigate(R.id.action_winFragment_to_gamelogic)

        }


    }

}