package com.voidstudio.ptapp.login_register

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import com.voidstudio.ptapp.MainActivity
import com.voidstudio.ptapp.R


class LoginRegisterActivity : AppCompatActivity() {

    private lateinit var viewFlipper: ViewFlipper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_register_viewflipper)

        // Making the screen full and making the status bar transparent
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Finding the viewFlipper
        viewFlipper = findViewById(R.id.viewFlipper)

        //Show register screen by default
        viewFlipper.displayedChild = 0

        // Login Screen
        // Login Button - Login the user
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener { loginBtnClicked() }

        // Create account button - Go to register screen
        val goToRegisterScreen = findViewById<Button>(R.id.loginToRegister)
        goToRegisterScreen.setOnClickListener {
            viewFlipper.displayedChild = 1
        }

        // Register Screen
        // Register Button - Login the user
        val registerBtn = findViewById<Button>(R.id.createAccountBtn)
        registerBtn.setOnClickListener { registerBtnClicked() }

        // Create account button - Go to register screen
        val goToLoginScreen = findViewById<Button>(R.id.registerToLogin)
        goToLoginScreen.setOnClickListener {
            viewFlipper.displayedChild = 0
        }
    }

    /*
    TODO: Registering
    New user register and writes their name on Firestore and authentication
    Creates a "profile" with all the information:
    - User1
        - User Information
            - Name
                - First: Adesh
                - Last: Sookdewo
            - Email: adesh5800@hotmail.co.uk
            - Status: Activated
            - Date
                - Joined: 22/11/2022
                - Expire: 26/12/2022

        - Workout
            - Current
                - Monday
                    - Exercise 1
                        - Exercise name: Pushup
                        - Sets: 5
                        - Reps: 10
                        - Between set cooldown: 50
                    - Exercise 2
                        - Exercise name: Situp
                        - Sets: 5
                        - Reps: 10
                        - Between set cooldown: 50
                - Tuesday
                    - Exercise 1
                        - Exercise name: Pushup
                        - Sets: 5
                        - Reps: 10
                        - Between set cooldown: 50
                    - Exercise 2
                        - Exercise name: Situp
                        - Sets: 5
                        - Reps: 10
                        - Between set cooldown: 50
            - Next
                - Monday
                    - Exercise 1
                        - Exercise name: Pushup
                        - Sets: 5
                        - Reps: 10
                        - Between set cooldown: 50
                    - Exercise 2
                        - Exercise name: Situp
                        - Sets: 5
                        - Reps: 10
                        - Between set cooldown: 50
                - Tuesday
                    - Exercise 1
                        - Exercise name: Pushup
                        - Sets: 5
                        - Reps: 10
                        - Between set cooldown: 50
                    - Exercise 2
                        - Exercise name: Situp
                        - Sets: 5
                        - Reps: 10
                        - Between set cooldown: 50

        - Milestones
            - Workout
                - Date: 22/11/2022
                - Pushup: 20
                - Situp: 8
                - Squat: 12
                - Calf raise: 15

     */

    private fun loginBtnClicked(){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

    private fun registerBtnClicked(){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}