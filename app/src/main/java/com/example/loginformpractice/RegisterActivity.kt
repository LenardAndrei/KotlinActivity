package com.example.loginformpractice

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class RegisterActivity : AppCompatActivity() {

    private var isPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.registration)

            val fullNameField = findViewById<EditText>(R.id.editTextFullName)
            val emailField = findViewById<EditText>(R.id.editTextEmail)
            val passwordField = findViewById<EditText>(R.id.editTextPassword)
            val confirmPasswordField = findViewById<EditText>(R.id.editTextConfirmPassword)
            val togglePasswordButton = findViewById<ImageButton>(R.id.togglePasswordVisibility)
            val toggleConfirmPasswordButton = findViewById<ImageButton>(R.id.toggleConfirmPasswordVisibility)
            val registerButton = findViewById<Button>(R.id.buttonRegister)
            val loginText = findViewById<TextView>(R.id.textView8)

            // Navigate to login screen
            loginText.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            // Toggle password visibility
            togglePasswordButton.setOnClickListener {
                try {
                    if (isPasswordVisible) {
                        passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        togglePasswordButton.setImageResource(android.R.drawable.ic_menu_view)
                        isPasswordVisible = false
                    } else {
                        passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                        togglePasswordButton.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
                        isPasswordVisible = true
                    }
                    passwordField.setSelection(passwordField.text.length)
                    val typeface = ResourcesCompat.getFont(this, R.font.outfit_light)
                    if (typeface != null) {
                        passwordField.typeface = typeface
                    }
                } catch (e: Exception) {
                    Log.e("RegisterActivity", "Error toggling password visibility", e)
                }
            }

            // Toggle confirm password visibility
            toggleConfirmPasswordButton.setOnClickListener {
                try {
                    if (isConfirmPasswordVisible) {
                        confirmPasswordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        toggleConfirmPasswordButton.setImageResource(android.R.drawable.ic_menu_view)
                        isConfirmPasswordVisible = false
                    } else {
                        confirmPasswordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                        toggleConfirmPasswordButton.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
                        isConfirmPasswordVisible = true
                    }
                    confirmPasswordField.setSelection(confirmPasswordField.text.length)
                    val typeface = ResourcesCompat.getFont(this, R.font.outfit_light)
                    if (typeface != null) {
                        confirmPasswordField.typeface = typeface
                    }
                } catch (e: Exception) {
                    Log.e("RegisterActivity", "Error toggling confirm password visibility", e)
                }
            }

            // Register button click listener
            registerButton.setOnClickListener {
                val fullName = fullNameField.text.toString()
                val email = emailField.text.toString()
                val password = passwordField.text.toString()
                val confirmPassword = confirmPasswordField.text.toString()

                when {
                    fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                        Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    }
                    password != confirmPassword -> {
                        Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    }
                    password.length < 6 -> {
                        Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        // Add your registration logic here
                        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                        // Navigate to login screen
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }

        } catch (e: Exception) {
            Log.e("RegisterActivity", "Error in onCreate", e)
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}