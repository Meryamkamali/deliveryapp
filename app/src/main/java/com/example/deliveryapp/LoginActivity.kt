package com.example.deliveryapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Gestion du clic sur "Mot de passe oublié"
        tv_forgot_password.setOnClickListener {
            startActivity(Intent(this, PasswordRecoveryActivity::class.java))
        }

        // Gestion du clic sur le bouton de connexion
        btn_login.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            if (validateInputs(email, password)) {
                performLogin(email, password)
            }
        }

        // Connexion avec Google
        btn_google.setOnClickListener {
            signInWithGoogle()
        }

        // Lien vers création de compte
        tv_create_account.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.error = "Adresse email invalide"
            return false
        }

        if (password.isEmpty() || password.length < 6) {
            et_password.error = "Le mot de passe doit contenir au moins 6 caractères"
            return false
        }
        return true
    }

    private fun performLogin(email: String, password: String) {
        // Implémentation de la logique de connexion
    }

    private fun signInWithGoogle() {
        // Implémentation de la connexion Google
    }
}