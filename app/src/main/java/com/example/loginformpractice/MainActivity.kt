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

class MainActivity : AppCompatActivity() {

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContentView(R.layout.activity_main)

            val usernameField = findViewById<EditText>(R.id.editTextUsername)
            val passwordField = findViewById<EditText>(R.id.editTextTextPassword)
            val toggleButton = findViewById<ImageButton>(R.id.togglePasswordVisibility)
            val loginButton = findViewById<Button>(R.id.buttonLogin)
            val forgotPasswordText = findViewById<TextView>(R.id.textView6)
            val registerText = findViewById<TextView>(R.id.textView10)

            // Navigate to register screen
            registerText.setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }

            // Toggle password visibility
            toggleButton.setOnClickListener {
                try {
                    if (isPasswordVisible) {
                        // Hide password
                        passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        toggleButton.setImageResource(android.R.drawable.ic_menu_view)
                        isPasswordVisible = false
                    } else {
                        // Show password
                        passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                        toggleButton.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
                        isPasswordVisible = true
                    }
                    // Keep cursor at end and restore font
                    passwordField.setSelection(passwordField.text.length)
                    val typeface = ResourcesCompat.getFont(this, R.font.outfit_light)
                    if (typeface != null) {
                        passwordField.typeface = typeface
                    }
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error toggling password visibility", e)
                }
            }

            // Login button click listener
            loginButton.setOnClickListener {
                val username = usernameField.text.toString()
                val password = passwordField.text.toString()

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                } else {
                    // Add your login logic here
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                }
            }

            // Forgot password click listener
            forgotPasswordText.setOnClickListener {
                Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show()
                // Navigate to forgot password screen or show dialog
            }

        } catch (e: Exception) {
            Log.e("MainActivity", "Error in onCreate", e)
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}