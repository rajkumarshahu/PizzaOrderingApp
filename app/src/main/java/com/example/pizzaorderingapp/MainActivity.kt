package com.example.pizzaorderingapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    // Populate context menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.pizza_types,menu)
        return true
    }

    // Handler when user selects a pizza from context menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Create new object of type Order
        val order = Order()

        // Set appropriate pizza type in order object based on user selection
        when(item.itemId){
            R.id.meatSupreme ->{
                order.pizzaType = getString(R.string.meat_supreme)
            }
            R.id.superHawaiian ->{
                order.pizzaType = getString(R.string.super_hawaiian)
            }
            R.id.veggie ->{
                order.pizzaType = getString(R.string.veggie)
            }
            R.id.mediterranean ->{
                order.pizzaType = getString(R.string.mediterranean)
                }
            R.id.cheddarSupreme -> {
                order.pizzaType = getString(R.string.cheddar_supreme)
            }
        }

        val dialog = PizzaSizeDialogFragment()
        dialog.show(supportFragmentManager, "sizeFragment")

        return true
        // Call pizza size activity and pass order object through intent
        val intent = Intent(this@MainActivity, PizzaSizeActivity::class.java)
        intent.putExtra("pizzaOrder", order)
        startActivity(intent)
        return true
    }
}

class PizzaSizeDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val selectedItems = ArrayList<Int>() // Where we track the selected items
            val builder = AlertDialog.Builder(it)
            // Set the dialog title
            builder.setTitle("Pick Size")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setSingleChoiceItems(R.array.pizza_size_array, 0, null)
                // Set the action buttons
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog
                        //order?.pizzaSize = radio.text.toString()
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->

                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}


class PizzaSizeDialogFragment1 : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val selectedItems = ArrayList<Int>() // Where we track the selected items
            val builder = AlertDialog.Builder(it)
            // Set the dialog title
            builder.setTitle("Pick Size")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                //.setSingleChoiceItems(R.array.pizzasize_array)
                .setMultiChoiceItems(R.array.toppings_array, null,
                    DialogInterface.OnMultiChoiceClickListener { dialog, which, isChecked ->
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            selectedItems.add(which)
                        } else if (selectedItems.contains(which)) {
                            // Else, if the item is already in the array, remove it
                            selectedItems.remove(Integer.valueOf(which))
                        }
                    })
                // Set the action buttons
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog

                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->

                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
