package com.voidstudio.ptapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.voidstudio.ptapp.login_register.LoginRegisterActivity
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val workoutInfo = WorkoutInformationArray()

    private val userInformation = UserInformation()

    private val loginActivity = LoginRegisterActivity()

    private lateinit var viewFlipper: ViewFlipper

    // Firestore variable
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Making the screen full and making the status bar transparent
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

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
    private fun toastMsg(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /*
    TODO: Get workout information
    Get all workout information and store in data structure - Multi-dimensional arrayList
     */

    private fun getData(){
        db = Firebase.firestore

        val name = loginActivity.fullName

        db.collection(name)
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    println("${document.id} => ${document.data}")
                }
            }
    }

    private fun prepareItems(){

        val recyclerView: RecyclerView = findViewById(R.id.workout_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<WorkoutItems>()

        for(x in 0 until workoutInfo.workoutInfo.size){
            data.add(WorkoutItems(workoutInfo.workoutInfo[x][0], workoutInfo.workoutInfo[x][1], workoutInfo.workoutInfo[x][2]))
        }

        val adapter = WorkoutAdapter(data)

        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : WorkoutAdapter.OnItemClickListerner {
            override fun onItemClick(position: Int){

                Toast.makeText(this@MainActivity,
                    workoutInfo.workoutInfo[position][0] + "\n" +
                        workoutInfo.workoutInfo[position][1] + "\n" +
                        workoutInfo.workoutInfo[position][2] + "\n",
                    Toast.LENGTH_SHORT).show()
            }
        })

    }

    /*
    TODO: Profile
    Get all user information in multi-dimension array
     */
}