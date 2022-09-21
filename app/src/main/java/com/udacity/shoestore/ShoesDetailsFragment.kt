package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoesDetailsBinding

import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel


class ShoesDetailsFragment : Fragment() {
    private lateinit var viewModel: ShoeViewModel
    var shoeList = MutableLiveData<ArrayList<Shoe>>()
    private lateinit var binding: FragmentShoesDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(

            inflater, R.layout.fragment_shoes_details, container, false
        )

        binding.shoe = Shoe("test", "1", "teest", "ttttttttt")

        viewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]



        binding.saveButton.setOnClickListener {
            saveShoeDetails()

        }
        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()

        }
        return binding.root
    }

    private fun saveShoeDetails() {
        val bindingData = binding.shoe
        val shoeName = bindingData?.shoeName.toString()
        val shoeSize = bindingData?.shoeSize.toString()
        val company = bindingData?.company.toString()
        val description = bindingData?.description.toString()

        if (shoeName.isEmpty() || shoeSize.isEmpty() || company.isEmpty()) {
            Toast.makeText(context, "Enter details of shoes", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.addDetailsShoe(
                "shoeName: $shoeName",
                "Company: $company",
                "Size: $shoeSize",
                "Description: $description"
            )

            findNavController().navigate(ShoesDetailsFragmentDirections.actionShoesDetailsFragmentToShoesFragment())
            Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show()

        }


    }


}