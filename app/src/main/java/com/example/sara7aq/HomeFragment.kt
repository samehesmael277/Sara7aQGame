package com.example.sara7aq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.sara7aq.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }

        binding.startBtn.setOnClickListener {
            getDataFromUser()
        }
    }

    private fun getDataFromUser() {
        val usersNumber: String = binding.numberOfUsers.text.toString()

        val validation = numberOfUserValidation(usersNumber)

        if (validation) {
            goToQuestionFragment(usersNumber.toInt())
        }
    }

    private fun numberOfUserValidation(number: String): Boolean {
        if (number.isEmpty() || number.isBlank()) {
            binding.numberOfUsers.error = "Enter Number Of Users"
            return false
        }
        if (number.isNotEmpty() && number.toInt() < 2) {
            binding.numberOfUsers.error = "Min User Must Be 2"
            return false
        }

        return number.isNotEmpty() && number.toInt() >= 2
    }

    private fun goToQuestionFragment(numberOfUser: Int) {
        val id = findNavController().currentDestination?.id

        if (id == R.id.homeFragment) {
            val action = HomeFragmentDirections.actionHomeFragmentToUsersFragment(numberOfUser)
            findNavController().navigate(action)
        }
    }
}