package com.voidstudio.ptapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ViewFlipper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var viewFlipper: ViewFlipper

    private val itemList = ArrayList<ArrayList<String>>()
    private lateinit var workoutAdapter: WorkoutAdapter

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

        // Move between the register screen to the login screen
        // Go to register screen
        val toCreateAccountBtn = findViewById<Button>(R.id.toCreateAccountBtn)
        toCreateAccountBtn.setOnClickListener {
            viewFlipper.displayedChild = 0
        }
        // Go to Login Screen
        val toLoginBtn = findViewById<Button>(R.id.toLoginBtn)
        toLoginBtn.setOnClickListener {
            viewFlipper.displayedChild = 1
        }


        // Register or login the user to the application
        // Register the user
        val createAccountBtn = findViewById<Button>(R.id.createAccountBtn)
        createAccountBtn.setOnClickListener { registerBtnClicked() }
        // Login the user
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener { loginBtnClicked() }

    }

    private fun prepareItems(){

        val recyclerView: RecyclerView = findViewById(R.id.workout_recyclerView)
        workoutAdapter = WorkoutAdapter(itemList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = workoutAdapter

        val nums = arrayListOf(
            arrayListOf("Pushup", "Sets: 5", "Reps: 10"),
            arrayListOf("Situp", "Sets: 4", "Reps: 20"),
            arrayListOf("Squat", "Sets: 6", "Reps: 15"),
            arrayListOf("Calf raise", "Sets: 10", "Reps: 3"),
            arrayListOf("Pushup", "Sets: 5", "Reps: 10"),
            arrayListOf("Situp", "Sets: 4", "Reps: 20"),
            arrayListOf("Squat", "Sets: 6", "Reps: 15"),
            arrayListOf("Calf raise", "Sets: 10", "Reps: 3")
        )

        for (i in 0 until nums.size){
            itemList.add(nums[i])
        }
    }

    private fun registerBtnClicked(){
        // Show the workout screen for now as a test
        viewFlipper.displayedChild = 2

        prepareItems()
    }

    private fun loginBtnClicked(){
        // Show the workout screen for now as a test
        viewFlipper.displayedChild = 2

        prepareItems()
    }

}