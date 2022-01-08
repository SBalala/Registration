package com.example.registration


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var Email: EditText
    private lateinit var Password: EditText
    private lateinit var Password2: EditText
    private lateinit var registerButton: Button
    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sets()

        register()

    }

    private fun sets() {
        Email = findViewById(R.id.Email)
        Password = findViewById(R.id.Password)
        Password2 = findViewById(R.id.Password2)
        registerButton = findViewById(R.id.registerButton)
    }

    private fun register() {
        registerButton.setOnClickListener {
            val email = Email.text.toString()
            val password = Password.text.toString()
            val password2 = Password2.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Empty~!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password == password2 && password.length > 9 && password.contains("[0-9]".toRegex()) && password.contains("[a-z]".toRegex()) && email.matches(emailPattern.toRegex()) ){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Password Requires at least 9 characters and it must contain at least 1 number", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}