@file:Suppress("UNCHECKED_CAST")

package com.internshipcodes.groceryapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
/*
This class object creates an instance of ViewModel.
 */
class GroceryViewModelFactory(private val repository: GroceryRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T: ViewModel>create(modelClass: Class<T>): T{
        return GroceryViewModel(repository) as T
    }
}