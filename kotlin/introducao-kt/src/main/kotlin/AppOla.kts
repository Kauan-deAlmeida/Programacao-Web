import javax.swing.JOptionPane

fun main(){
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