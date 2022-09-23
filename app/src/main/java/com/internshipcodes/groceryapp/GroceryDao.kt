package com.internshipcodes.groceryapp

import androidx.lifecycle.LiveData
import androidx.room.*

/*
This is Dao class that contains the insert, delete and a query to acsess all elements of grocery list
 */
@Dao
interface GroceryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: GroceryItems)

    @Delete
    suspend fun delete(item: GroceryItems)

    @Query("SELECT * FROM grocery_items")
    fun getAllGroceryItems() : LiveData<List<GroceryItems>>

}