package com.example.sara7aq

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sara7aq.databinding.FragmentUsersBinding

class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding

    private val args by navArgs<UsersFragmentArgs>()

    private var etLIst: ArrayList<EditText> = ArrayList()

    private var namesList: ArrayList<String> = ArrayList()

    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        loadingDialog = LoadingDialog(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createEditTextLoop(args.numberOfUser)

        binding.startBtn.setOnClickListener {
            getDataFromUi()
        }
    }

    private fun getDataFromUi() {
        loadingDialog.show()
        namesList.clear()
        for (i in etLIst) {
            val userInput = i.text.toString()
            if (userInput.isNotEmpty() && userInput.isNotBlank()) {
                namesList.add(userInput)
            }
        }
        loadingDialog.hide()

        if (validation()) {
            goToQuestionFragment()
        }
    }

    private fun validation(): Boolean {
        if (namesList.size < 2) {
            etLIst[0].error = "Min 2 user"
            etLIst[1].error = "Min 2 user"
            return false
        }

        return true
    }

    private fun createEditTextLoop(numberOfET: Int) {
        loadingDialog.show()
        etLIst.clear()
        for (i in 1..numberOfET) {
            val myEditText = EditText(requireContext())
            myEditText.layoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            myEditText.hint = "User Name $i"
            etLIst.add(myEditText)
            binding.linear.addView(myEditText)
        }
        loadingDialog.hide()
    }

    private fun goToQuestionFragment() {
        val id = findNavController().currentDestination?.id

        if (id == R.id.usersFragment) {
            val action =
                UsersFragmentDirections.actionUsersFragmentToQuestionsFragment(namesList.toTypedArray())
            findNavController().navigate(action)
        }
    }

    private fun convertPixelsToDp(px: Float, context: Context): Int {
        val resources: Resources = context.resources
        val metrics: DisplayMetrics = resources.displayMetrics
        return (px / (metrics.densityDpi / 160f)).toInt()
    }
}