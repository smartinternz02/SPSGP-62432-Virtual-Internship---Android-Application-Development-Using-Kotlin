package com.internshipcodes.groceryapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internshipcodes.groceryapp.databinding.GroceryRvItemsBinding

/*
This class crreates a recyclerview adapter that consists of data in the database
 */
class GroceryRvAdapter(var list: List<GroceryItems>, private val groceryItemClickInterface:GroceryItemClickInterface):
    RecyclerView.Adapter<GroceryRvAdapter.GroceryViewHolder>() {


    //created viewHolder is used to access the views creates from the layout file.
    inner class GroceryViewHolder(var binding: GroceryRvItemsBinding): RecyclerView.ViewHolder(binding.root)

    interface GroceryItemClickInterface {
        fun onItemClick(groceryItems: GroceryItems) {
        }
    }

    //Viewholder is created in this method.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {

        return GroceryViewHolder(
            GroceryRvItemsBinding.inflate(
                LayoutInflater.from
                    (parent.context),
                parent,
                false
            )
        )


    }

    //This method is used to bind the view at specified position.
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {

        //extract text from the list
        holder.binding.itemName.text = list[position].itemName
        holder.binding.itemQuantity.text = list[position].itemQuantity.toString()
        holder.binding.itemRate.text = "₹. "+ list[position].itemPrice.toString()

        //calculate total amount.
        val itemTotal :Float = list[position].itemPrice * list[position].itemQuantity

        //set text of total amount to the text view on the screen.
        holder.binding.totalAmount.text = "₹.$itemTotal"

        //delete the element when user clicks on delete icon.
        holder.binding.delete.setOnClickListener{
            groceryItemClickInterface.onItemClick(list[position])

        }
    }

    //This method returns the size of grocery list.
    override fun getItemCount(): Int {
        return list.size
    }
}