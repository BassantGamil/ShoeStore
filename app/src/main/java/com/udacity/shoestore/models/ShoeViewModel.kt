package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeViewModel : ViewModel() {

    private var shoesList = mutableListOf<Shoe>()
    private var _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    fun addDetailsShoe(name: String, company: String, size: String, description: String) {
        val newItem = Shoe(name, company, size, description)
        newItem.let { item ->
            shoesList.add(item)
            _shoes.value = shoesList
        }
    }
}