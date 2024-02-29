package com.example.examen2ev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examen2ev.databinding.ActivityMainBinding
import com.example.examen2ev.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val videojuego = intent.getSerializableExtra("videojuego") as Videojuego
        var lista = intent.getSerializableExtra("lista") as ArrayList<Videojuego>

        binding.siguiente.setOnClickListener {
            if(binding.empresa.text.isNotEmpty() && binding.anio.text.isNotEmpty()){
                try{
                    if(binding.anio.text.toString().length==4){

                        if(binding.anio.text.toString().toInt() in 0..2024){
                            videojuego.setEmpresa(binding.empresa.text.toString())
                            videojuego.setAnio(binding.anio.text.toString().toInt())
                            lista.add(videojuego)
                            val intent = Intent(this, ThirdActivity::class.java)
                            intent.putExtra("videojuego",videojuego)
                            intent.putExtra("lista",lista)
                            startActivity(intent)
                        }else{
                            binding.error.text="El año no puede ser negativo ni superior a 2024."
                        }
                    }else{
                        binding.error.text="El año ha de ser un numero de 4 digitos."
                    }
                }catch (e: Exception){
                    binding.error.text="Escribe el año en numeros"
                }
            }else{
                binding.error.text="No puede haber campos vacios."
            }
        }

        binding.atras.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("lista",lista)
            startActivity(intent)
        }
    }
}