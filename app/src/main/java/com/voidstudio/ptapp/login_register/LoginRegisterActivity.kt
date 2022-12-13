package com.voidstudio.ptapp.login_register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.voidstudio.ptapp.MainActivity
import com.voidstudio.ptapp.R
import java.time.LocalDateTime
import java.time.Month
import java.time.YearMonth
import java.time.format.DateTimeFormatter


class LoginRegisterActivity : AppCompatActivity() {

    // Declaring a viewFliper variable
    private lateinit var viewFlipper: ViewFlipper

    // Registering screen inputs variable
    private lateinit var fullNameInput: EditText
    private lateinit var emailAddressInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText

    // "Logging in" Text variable
    private lateinit var loginText: TextView

    // Firestore variable
    private lateinit var db: FirebaseFirestore

    // String to store user input and send to database
    private lateinit var fullName: String
    private lateinit var email: String

    // Create Firebase Authentication object
    private lateinit var auth: FirebaseAuth

    private var expireDate = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_register_viewflipper)

        // Making the screen full and making the status bar transparent
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        fullNameInput = findViewById(R.id.fullName_input)
        emailAddressInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        confirmPasswordInput = findViewById(R.id.confirmPassword_input)

        loginText = findViewById(R.id.loginText)

        auth = Firebase.auth

        db = Firebase.firestore

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

        val expireMonth = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM")
        expireDate = expireMonth.format(formatter).toInt()

        if (expireDate == 12){
            expireDate = 1
        } else {
            expireDate += 1
        }

        toastMsg(expireDate.toString())
    }

    private fun toastMsg(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    /*
    TODO: Registering
    New user register and writes their name on Firestore and authentication
    Creates a "profile" with all the information:
    - User1
        - User Information
            - First: Adesh
            - Last: Sookdewo
            - Email: adesh5800@hotmail.co.uk
            - Status: Activated
            - Date Joined: 22/11/2022
            - Date Expire: 26/12/2022

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
    private fun registerBtnClicked(){
        // Getting user input and converting to string
        fullName = fullNameInput.text.toString()
        email = emailAddressInput.text.toString()
        val password = passwordInput.text.toString()
        val confirmPassword = confirmPasswordInput.text.toString()

        // Checking if the inputs are blank
        if (fullName.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()){
            toastMsg("Fullname, email and password should not be blank")
        }
        // If the user's password matches with confirm password
        else if (password != confirmPassword){
            toastMsg("Password don't match")
        }
        // Create user in authentication
        else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                while(!it.isSuccessful){
                    loginText.visibility = View.VISIBLE
                }
                if (it.isSuccessful) {
                    toastMsg("Login successful")
                    createUserInfo()
                    loginText.visibility = View.GONE
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    toastMsg("Login in failed")
                    loginText.visibility = View.GONE
                }
            }
        }
    }

    private fun createUserInfo(){

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val todayDate = current.format(formatter)

        val userInfo = hashMapOf(
            "First" to fullName,
            "Last" to fullName,
            "Email" to email,
            "Status" to "Not Active",
            "Date Joined" to todayDate,
            "Date Expire" to expireDate
        )
        db.collection(fullName)
            .document("User Information")
            .collection("User Information")
            .add(userInfo)
    }

    /*
    TODO: Login
    Check the logging details and login the user
    The application keep login information SaveSharedPreference
    Allow user to use biometric to log in
     */

    private fun loginBtnClicked(){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}