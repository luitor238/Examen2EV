package com.example.examen2ev

import java.io.Serializable

class Videojuego(private var nombre: String, private var valoracion: Float): Serializable {

    private var  empresa = ""
    private var anio = 0

    fun getNombre() = nombre
    fun getValoracion() = valoracion
    fun getEmpresa() = empresa
    fun getAnio() = anio

    fun setEmpresa(newEmpresa: String){
        this.empresa=newEmpresa
    }
    fun setAnio(newAnio: Int){
        this.anio=newAnio
    }

    override fun toString(): String {
        return "Nombre='$nombre' Valoracion=$valoracion Empresa='$empresa' Año=$anio"
    }
}