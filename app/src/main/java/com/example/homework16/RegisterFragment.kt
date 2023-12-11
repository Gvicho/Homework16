package com.example.homework16

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homework16.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate){

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

            registerBtn.setOnClickListener {
                val email = emailEditText.text.toString()
                val userName = userNameEditText.text.toString()
                val password = passwordEditText.text.toString()

                if(validateAllInput(email,userName,password)){
                    val personRequest = PersonRequest(email,password)
                    viewModel.registerPerson(personRequest)
                }

            }

        }

    }

    private fun collect(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.response.collect{ response ->
                    if(response!=null){
                        if (response!!.isSuccessful) {
                            Toast.makeText(context, "successful registration", Toast.LENGTH_SHORT).show()
                            Log.d("tag123","successful registration")
                            val responseBody = response.body()
                            Log.d("tag123","${responseBody.toString()}")

                        } else {
                            Toast.makeText(context, "Unsuccessful registration", Toast.LENGTH_SHORT).show()
                            Log.d("tag123","Unsuccessful registration")
                        }
                    }else{
                        if(viewModel.connectionError){
                            Toast.makeText(context, "Check network connection", Toast.LENGTH_SHORT).show() // will only show on first time when offline
                        }
                    }

                }
            }
        }
    }

    private fun validateAllInput(email:String,userName:String,password:String):Boolean{

        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"

        if(!email.matches(emailRegex.toRegex())){ //validate email
            Toast.makeText(context, "Email is incorrect", Toast.LENGTH_SHORT).show()
            return false
        }

        if(userName.isEmpty()){
            Toast.makeText(context, "UserName Field is empty", Toast.LENGTH_SHORT).show()
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


