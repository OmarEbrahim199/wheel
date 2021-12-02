package com.example.wheel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wheel.Model.CustomAdapter
import com.example.wheel.Model.hiddenWord
import com.example.wheel.Model.randomWords
import java.util.ArrayList

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)
    }


    override fun onStart() {
        super.onStart()

        recyclerview()


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



    fun recyclerview(){


            val recyclerview: RecyclerView = findViewById(R.id.recyclerView)


            // this creates a vertical layout Manager
            recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)

            // ArrayList of class ItemsViewModel
            val data = ArrayList<randomWords>()


            data.add(randomWords(hiddenWord()))
            data.add(randomWords("omar"))

            // This will pass the ArrayList to our Adapter
            val adapter = CustomAdapter(data,null,)

            // Setting the Adapter with the recyclerview
            recyclerview.adapter = adapter





    }
}
