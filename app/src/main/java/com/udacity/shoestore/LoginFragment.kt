package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater, R.layout.fragment_login, container, false
        )
        val email = binding.editTextTextEmailAddress.text.toString()
        val password = binding.editTextTextPassword.text.toString()
        binding.buttonLogin.setOnClickListener { view: View ->

//            if (email.isEmpty() || password.isEmpty()) {
//                Toast.makeText(context, "Enter email and password ", Toast.LENGTH_LONG).show()
//            } else {
            view.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
                )
            )
        }

        binding.buttonSignUp.setOnClickListener { view: View ->
            view.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
                )
            )
        }

        return binding.root
    }
}