package org.example

data class Pessoa(
    var nome: String="",
    var email: String = "")
{

    var idade = 0

    companion object{
        fun metodoEstatico(): String{
            return "Estático"
        }
    }
}