package com.example.examen2ev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.examen2ev.databinding.ActivitySecondBinding
import com.example.examen2ev.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val db = JuegosComprados(this)

        val videojuego = intent.getSerializableExtra("videojuego") as Videojuego
        val lista = intent.getSerializableExtra("lista") as ArrayList<Videojuego>

        if(videojuego.getNombre()!=""){
            binding.videojuego.text = videojuego.toString()
        }else{
            binding.titulo.visibility = View.GONE
            binding.guardar.visibility = View.GONE
        }

        binding.guardar.setOnClickListener {
            db.insertarVideojuego(videojuego)
            val intent = Intent(this, SaveActivity::class.java)
            intent.putExtra("lista",lista)
            startActivity(intent)
        }

        binding.listaVideojuegos.text = lista.joinToString ("\n")

        binding.otro.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("lista",lista)
            startActivity(intent)
        }


    }
}