package com.internshipcodes.groceryapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
This is a Database class that allows other classes to easily access the Dao classes.
 */
@Database(entities = [GroceryItems::class], version = 1, exportSchema = false)
abstract class GroceryDatabase: RoomDatabase() {

    abstract fun getGroceryDao():GroceryDao

    //This below object creates only one instance of the database to prevent race conditions.
    companion object{

        @Volatile
        private var instance:GroceryDatabase?=null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        //create database
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                GroceryDatabase::class.java, "grocery_database.db")
                .build()

    }
}