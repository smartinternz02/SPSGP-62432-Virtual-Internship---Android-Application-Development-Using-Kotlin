@file:OptIn(DelicateCoroutinesApi::class)

package com.internshipcodes.groceryapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/*
this class used for the ViewModel od the app.
ViewModel class is used to store data related to the app's UI
 */
class GroceryViewModel (private val repository: GroceryRepository): ViewModel()
{
    fun insert(item:GroceryItems) = GlobalScope.launch {
        repository.insert(item)
    }
    fun delete(item:GroceryItems) = GlobalScope.launch {
        repository.delete(item)
    }
    fun getAllGroceryItems() = repository.getAllItems()
}