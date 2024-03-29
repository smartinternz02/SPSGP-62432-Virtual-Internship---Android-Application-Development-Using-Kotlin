package com.internshipcodes.groceryapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Database column information is here.
@Entity(tableName = "grocery_items")
data class GroceryItems(

    @ColumnInfo(name =  "itemName")
    var itemName:String,

    @ColumnInfo(name =  "itemQuantity")
    var itemQuantity: Int,

    @ColumnInfo(name =  "itemPrice")
    var itemPrice:Float,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}