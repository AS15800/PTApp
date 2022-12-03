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

    private fun loginBtnClicked(){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

    private fun registerBtnClicked(){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}