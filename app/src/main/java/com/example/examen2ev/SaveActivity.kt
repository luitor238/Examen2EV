package com.example.examen2ev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examen2ev.databinding.ActivitySaveBinding
import com.example.examen2ev.databinding.ActivityThirdBinding

class SaveActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySaveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        binding = ActivitySaveBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val db = JuegosComprados(this)

        val lista = intent.getSerializableExtra("lista") as ArrayList<Videojuego>

        val listaDB = db.getVideojuego()

        binding.listaVideojuegos.text= listaDB.joinToString ("\n")

        binding.volver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("lista",lista)
            startActivity(intent)
        }
    }
}