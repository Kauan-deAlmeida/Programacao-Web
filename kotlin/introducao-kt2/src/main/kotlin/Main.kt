package org.example
import javax.swing.JOptionPane

fun main2(){
    println("Olá mundo!")

    // val (utilizado para valores que não serão alterados)
    val texto = "Olá" // tipo inferido (não declarado)
//    texto = "" // não funciona pq não pode ser alterado (val)

    // var (utilizado para valores dinamicos que serão alterados)
    var descricao = "descrição"
    descricao = "Nova descrição"

    println(descricao)

    // tipos inferidos / declarativos
//    var inteiro = 0
//    var double = 0.0
//    var string = ""
//    var boolean = true

    var inteiro: Int? = null
    var double: Double
    var string: String
    var boolean: Boolean

//    var soma = 10 + inteiro!! // null safety (!!)

    println(inteiro)

    var nome: String? = null

    println(nome ?: "Não tem nome")

    nome = "Elvis"
    println("Nome: $nome")

    val n1 = 10
    val n2 = 15
    println("Resultado: ${n1 + n2}")

    print("Digite seu nome: ")
    val nomeUsuario = readlnOrNull() ?: "Sem nome"
    println("Seu nome é $nomeUsuario")

    val nome2 = JOptionPane.showInputDialog("Digite seu nome")
    JOptionPane.showInputDialog(null, "Seu nome é ${nome2 ?: "Sem nome"}")
}


fun main3() {
    // lista Uusario imutável
    val listaUsuario = listOf("José", "Maria")
    // listaUsuario.add("") // não funfa

    val listaUsuarioMutavel = mutableListOf<String>()
    listaUsuarioMutavel.add("Marcio")
    listaUsuarioMutavel.add("Edu")

    println("primeiro indice: ${listaUsuarioMutavel.get(0)}")
    println("primeiro indice: ${listaUsuarioMutavel[0]}")
    println("primeiro indice: ${listaUsuarioMutavel.first()}")
    println("primeiro indice: ${listaUsuarioMutavel.last()}")

    println("------------------------------------------------------------------------")

    listaUsuarioMutavel.forEach{ daVez ->
        println(daVez)
    }

    listaUsuarioMutavel.forEachIndexed {indice, daVez ->
        println("${indice+1}° - $daVez")
    }

    val listaAuxiliar = listaUsuarioMutavel.map {it.uppercase()}
    println(listaAuxiliar)

    for(daVez in listaUsuarioMutavel){
        println(daVez)
    }

    for(indice in 1..listaUsuarioMutavel.size){
        println(listaUsuarioMutavel[indice-1])
    }
}

fun main4() {
    val valor = 10

    when(valor){
        in 0..10 -> println("Ta entre 0 e 10")
        11 -> println("é 11")
        else -> println("número errado")
    }

    val valor2 = 5
    val resultado = when(valor2){
        in 0..10 -> "Isso"
        else -> "Aquilo"
    }

    println(resultado)
}

fun main() {
    val pessoa = Pessoa(nome = "Kauan", email = "kauan.almeida@email.com")
    pessoa.idade = 18
    pessoa.nome = "" // set
    println(pessoa.nome) // get
    println(pessoa)
}
