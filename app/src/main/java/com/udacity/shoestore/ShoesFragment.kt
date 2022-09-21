package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoesBinding
import com.udacity.shoestore.databinding.ListShoeBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel
import kotlinx.android.synthetic.main.list_shoe.*


class ShoesFragment : Fragment() {

    private lateinit var binding: FragmentShoesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoes, container, false
        )
        setHasOptionsMenu(true)
        val viewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]
        viewModel.shoes.observe(viewLifecycleOwner) { item: List<Shoe> ->
            addShoeList(item)
        }
        binding.lifecycleOwner = viewLifecycleOwner
        binding.addShoe.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ShoesFragmentDirections.actionShoesFragmentToShoesDetailsFragment())
        }
        return binding.root
    }


    private fun addShoeList(item: List<Shoe>) {
        item.forEach {
            val bindingView = ListShoeBinding.inflate(
                LayoutInflater.from(requireContext()),
                binding.showShoeLinear,
                false
            )
            bindingView.showShoeNameTextView.text = it.shoeName
            bindingView.showShoeSizeTextView.text = it.shoeSize
            bindingView.showCompanyTextView.text = it.company
            bindingView.showDescriptionTextView.text = it.description
            binding.showShoeLinear.addView(bindingView.root)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.navdrawer_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.loginFragment -> {
                findNavController()
                    .navigate(ShoesFragmentDirections.actionShoesFragmentToLoginFragment())
                true
            }
            else -> false

        }
    }

}