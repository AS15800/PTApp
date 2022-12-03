package com.voidstudio.ptapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var viewFlipper: ViewFlipper

    private val itemList = ArrayList<ArrayList<String>>()
    private lateinit var workoutAdapter: WorkoutAdapter

    private lateinit var email: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Making the screen full and making the status bar transparent
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Finding the viewFlipper
        viewFlipper = findViewById(R.id.viewFlipper)

        //Show register screen by default
        viewFlipper.displayedChild = 0

        prepareItems()

        val actionBarText = findViewById<TextView>(R.id.actionBar)

        // Navigation buttons
        val navigation: BottomNavigationView = findViewById(R.id.bottomNav)

        navigation.selectedItemId = R.id.nav_workout

        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_workout -> {
                    actionBarText.text = "Workout"
                    viewFlipper.displayedChild = 0
                }
                R.id.nav_message -> {
                    actionBarText.text = "Message"
                    viewFlipper.displayedChild = 1
                }
                R.id.nav_profile -> {
                    actionBarText.text = "Profile"
                    viewFlipper.displayedChild = 2
                }
            }
            true
        }
    }

    private fun prepareItems(){

        val recyclerView: RecyclerView = findViewById(R.id.workout_recyclerView)
        workoutAdapter = WorkoutAdapter(itemList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = workoutAdapter

        val workoutInfo = arrayListOf(
            arrayListOf("Pushup", "Sets: 5", "Reps: 10"),
            arrayListOf("Situp", "Sets: 4", "Reps: 20"),
            arrayListOf("Squat", "Sets: 6", "Reps: 15"),
            arrayListOf("Calf raise", "Sets: 10", "Reps: 3"),
            arrayListOf("Pushup", "Sets: 5", "Reps: 10"),
            arrayListOf("Situp", "Sets: 4", "Reps: 20"),
            arrayListOf("Squat", "Sets: 6", "Reps: 15"),
            arrayListOf("Calf raise", "Sets: 10", "Reps: 3")
        )

        for (i in 0 until workoutInfo.size){
            itemList.add(workoutInfo[i])
        }
    }
}