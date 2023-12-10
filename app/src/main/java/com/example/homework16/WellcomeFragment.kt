package com.example.homework16

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.homework16.databinding.FragmentWellcomeBinding


class WellcomeFragment : BaseFragment<FragmentWellcomeBinding>(FragmentWellcomeBinding::inflate){


    companion object {

        @JvmStatic
        fun newInstance() =
            WellcomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun setUp() {
        setListeners()
    }

    private fun setListeners(){
        binding.apply {

            registerBtn.setOnClickListener {
                findNavController().navigate(R.id.action_wellcomeFragment_to_registerFragment)
            }

            loginBtn.setOnClickListener{
                findNavController().navigate(R.id.action_wellcomeFragment_to_loginFragment)
            }
        }

    }

    override fun initData(savedInstanceState: Bundle?) {

    }
}