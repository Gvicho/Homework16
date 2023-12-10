package com.example.homework16

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.homework16.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate){


    val viewModel:SharedViewModel by viewModels()


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
        collect()
    }

    private fun setListeners(){

        binding.apply {

            loginBtn.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                if(validateAllInput(email,password)){
                    val personRequest = PersonRequest(email,password)
                    viewModel.loginPerson(personRequest)
                }

            }

        }

    }

    private fun collect(){
        lifecycleScope.launch {
            viewModel.response.collect{ response ->

                if(response!=null){
                    if (response!!.isSuccessful) {
                        Toast.makeText(context, "successful login", Toast.LENGTH_SHORT).show()
                        Log.d("tag123","successful login")
                        val responseBody = response.body()
                        Log.d("tag123","${responseBody.toString()}")

                    } else {
                        Toast.makeText(context, "Unsuccessful login", Toast.LENGTH_SHORT).show()
                        Log.d("tag123","Unsuccessful login")
                    }
                }else{
                    if(viewModel.connectionError){
                        Toast.makeText(context, "Check network connection", Toast.LENGTH_SHORT).show()// will only show on first time when offline
                    }
                }

            }
        }
    }

    private fun validateAllInput(email:String,password:String):Boolean{

        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

        if(!email.matches(emailRegex.toRegex())){ //validate email
            Toast.makeText(context, "Email is incorrect", Toast.LENGTH_SHORT).show()
            return false
        }

        if(password.length < 8){
            Toast.makeText(context, "password must contain at least 8 characters", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    override fun initData(savedInstanceState: Bundle?) {

    }
}