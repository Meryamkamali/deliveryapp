package com.example.deliveryapp


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardActivity : AppCompatActivity() {

    // Liste des options du menu
    private val menuItems = listOf(
        MenuItem("Rural de la chambre", R.drawable.room, "#FFB74D"), // Use existing icon
        MenuItem("Adresse", R.drawable.baseline_add_location_24, "#81C784"),
        MenuItem("Directeur", R.drawable.profile, "#64B5F6"), // Material person icon
        MenuItem("Médical", R.drawable.hospital, "#E57373"), // Hospital icon
        MenuItem("Comme si", R.drawable.service, "#BA68C8")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setupMenuRecyclerView()
    }

    private fun setupMenuRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_menu)
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 colonnes
        recyclerView.adapter = MenuAdapter(menuItems) { item ->
            handleMenuItemClick(item)
        }
    }

    private fun handleMenuItemClick(item: MenuItem) {
        when (item.title) {
            "Rural de la chambre" -> startActivity(Intent(this, RoomActivity::class.java))
            "Adresse" -> startActivity(Intent(this, AddressActivity::class.java))
            // Ajouter les autres cas selon vos besoins
            else -> showToast("Fonctionnalité en développement")
        }
    }

    fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

}


