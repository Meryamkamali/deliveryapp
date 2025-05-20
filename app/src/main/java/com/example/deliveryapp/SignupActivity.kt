package com.example.deliveryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btn_register.setOnClickListener {
            val fullName = et_full_name.text.toString()
            val email = et_email.text.toString()
            val password = et_password.text.toString()
            val confirmPassword = et_confirm_password.text.toString()

            if (validateSignupInputs(fullName, email, password, confirmPassword)) {
                performRegistration(fullName, email, password)
            }
        }
    }

    private fun validateSignupInputs(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        // Validation similaire à LoginActivity avec vérification supplémentaire
        if (password != confirmPassword) {
            et_confirm_password.error = "Les mots de passe ne correspondent pas"
            return false
        }
        return true
    }

    private fun performRegistration(fullName: String, email: String, password: String) {
        // Implémentation de l'inscription
    }
}
