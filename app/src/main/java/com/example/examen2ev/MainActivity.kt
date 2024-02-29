package com.example.examen2ev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examen2ev.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var lista: ArrayList<Videojuego>

        try{
            lista = intent.getSerializableExtra("lista") as ArrayList<Videojuego>
        }catch (e: NullPointerException ){
            lista = ArrayList<Videojuego>()
        }

        var videojuego = Videojuego ("",0.toFloat())

        binding.siguiente.setOnClickListener {
            if(binding.nombre.text.isNotEmpty() && binding.valoracion.text.isNotEmpty()){
                try{
                    if(binding.valoracion.text.toString().toFloat()>=0){
                        videojuego = Videojuego(binding.nombre.text.toString(), binding.valoracion.text.toString().toFloat())
                        val intent = Intent(this, SecondActivity::class.java)
                        intent.putExtra("videojuego",videojuego)
                        intent.putExtra("lista",lista)
                        startActivity(intent)
                    }else{
                        binding.error.text="La valoracion solo admite numeros superiores a 0."
                    }
                }catch (e: Exception){
                    binding.error.text="La valoracion solo admite numeros superiores a 0."
                }
            }else{
                binding.error.text="No puede haber campos vacios."
            }
        }

        binding.menu.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("videojuego",videojuego)
            intent.putExtra("lista",lista)
            startActivity(intent)
        }
    }
}