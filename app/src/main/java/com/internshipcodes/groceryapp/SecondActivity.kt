@file:Suppress("NAME_SHADOWING")

package com.internshipcodes.groceryapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.internshipcodes.groceryapp.databinding.ActivitySecondBinding
/*
This class is used to create the login of the app. This is the class next executed after the main activity.
 */
class SecondActivity : AppCompatActivity(), GroceryRvAdapter.GroceryItemClickInterface {

    //declare the necessary values at top by using binding
    private lateinit var binding: ActivitySecondBinding
    private lateinit var list: List<GroceryItems>
    private lateinit var groceryRvAdapter: GroceryRvAdapter
    private lateinit var groceryViewModel: GroceryViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //using binding to make declare easy
        binding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(binding.root)

        list = ArrayList()

        groceryRvAdapter = GroceryRvAdapter(list,this)

        binding.rvItems.layoutManager = LinearLayoutManager(this)

        binding.rvItems.adapter =groceryRvAdapter

        val groceryRepository = GroceryRepository(GroceryDatabase(this))

        val factory = GroceryViewModelFactory(groceryRepository)

        groceryViewModel = ViewModelProvider(this,factory)[GroceryViewModel::class.java]

        groceryViewModel.getAllGroceryItems().observe(this) {
            groceryRvAdapter.list = it
            groceryRvAdapter.notifyDataSetChanged()
        }

        /*
        On clicking on the FAB button a method openDailog() is called.
         */
        binding.fabAdd.setOnClickListener{
            openDialog()
        }



    }

    /*
    This method is called to display a dialog used to add an elemnet to the grocery list.
    It is called when user clicks on FAB button.
     */
    @SuppressLint("NotifyDataSetChanged")
    private fun openDialog() {
        //declare and initilize the dialog object and set content on the layout provided.
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.grocery_add_dialog)

        //Find the values in layout by findViewById() method
        val cancelBtn = dialog.findViewById<Button>(R.id.btn_cancel)
        val addBtn = dialog.findViewById<Button>(R.id.btn_add)

        val itemEdt = dialog.findViewById<EditText>(R.id.item_na)
        val itemPrice = dialog.findViewById<EditText>(R.id.item_pri)
        val itemQuantity = dialog.findViewById<EditText>(R.id.item_quant)

        //when user clicks on "cancel" button, this method is invoked in which the dialog is closed and no longer data is added to database
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }

        /*when user clicks on "add" button below method is invoked in which
        all values entered by user are extracted and
        corresponding element is added to grocery list in database
        and shown on the screen.
        * */
        addBtn.setOnClickListener {

            //extract information entered by user in the fields
            val itemName :String = itemEdt.text.toString()
            val itemPrice :String = itemPrice.text.toString()
            val itemQuantity :String = itemQuantity.text.toString()

            //convert quantity and price to int and float datatypes respectively
            val qty : Int = itemQuantity.toInt()
            val pr :Float = itemPrice.toFloat()

            /*
            If no edit text is empty then all items are inserted into grocery list
             */
            if (itemName.isNotEmpty() && itemPrice.isNotEmpty() &&itemQuantity.isNotEmpty()){
                //call GroceryItems method by passing the parameters itemName, quantity, price
                val items = GroceryItems(itemName,qty,pr)

                groceryViewModel.insert(items)

                //show the message of insertion of element to database
                Snackbar.make(binding.root,"Item Inserted..", Snackbar.LENGTH_SHORT).show()

                //make the screen update when database is changed and finally close the dialog.
                groceryRvAdapter.notifyDataSetChanged()
                dialog.dismiss()

            }else//if any field is empty then show the message of error
            {
                Snackbar.make(binding.root,"Empty fields are not allowed !!", Snackbar.LENGTH_SHORT).show()


            }

        }
        dialog.show()


    }

    /*
    It is called when item is clicked. It deletes the elements and updates the screen according to database and shows a message od deletion.
     */
    @SuppressLint("NotifyDataSetChanged")
    override fun onItemClick(groceryItems: GroceryItems) {
        groceryViewModel.delete(groceryItems)
        groceryRvAdapter.notifyDataSetChanged()
        Snackbar.make(binding.root,"Item Deleted !!", Snackbar.LENGTH_SHORT).show()

    }
}