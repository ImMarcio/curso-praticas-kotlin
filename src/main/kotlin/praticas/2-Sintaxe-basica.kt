package br.ifpb.pdm.praticas

class Livro(var titulo: String, var preco: Double) {



    override fun toString(): String {
        return "Livro: Titulo = $titulo, Preco = $preco"
    }
}

fun menu() {
    println("1 - Cadastrar livro")
    println("2 - Excluir livro")
    println("3 - Buscar livro")
    println("4 - Editar livro")
    println("5 - Listar livros")
    println("6 - Listar livros que começam com letra escolhida")
    println("7 - Listar livros com preço abaixo do informado")
    println("8 - Sair")
}

fun inputTitulo(): String {
    print("Digite o titulo do livro: ")
    return readlnOrNull() ?:""
}

fun inputPreco(): Double {
    print("Digite o preco do livro: ")
    var preco = readlnOrNull()!!.toDouble()
    if(preco > 0 ){
        return preco
    }else{
        print("Preço inválido")
        return inputPreco()
    }
}

fun cadastrarLivro(repositorio: MutableList<Livro>) {
    val titulo = inputTitulo()
    var preco = inputPreco()
    repositorio.add(Livro(titulo, preco))
    println("\nCadastrado com sucesso!\n")
}

fun excluirLivro(repositorio: MutableList<Livro>) {
    val livro = buscarNome(repositorio)
    if(livro != null){
        repositorio.remove(livro)
        println("Livro removido com sucesso!")
    }else{
        println("Livro não encontrado")
    }

}

fun buscarNome(repositorio: MutableList<Livro>): Livro? {
    val titulo = inputTitulo()
    return repositorio.find { it.titulo == titulo }
}

fun editarLivro(repositorio: MutableList<Livro>) {
    println("Qual livro desejar alterar?")
    var livro = buscarNome(repositorio)
    println("Escolha o que desejar alterar: " +
            "1 -> título" +
            "2 -> preço")

    var opcao = readlnOrNull()!!.toInt()
    when(opcao){
        1 -> {
            livro?.titulo =  inputTitulo()
            println("Titulo alterado com sucesso")
        }
        2 -> {
            livro?.preco = inputPreco()
            println("Preco alterado com sucesso")
        }
    }
}

fun listar(repositorio: MutableList<Livro>) {
    repositorio.forEach{
        println(it)
    }

}

fun listarComLetraInicial(repositorio: MutableList<Livro>) {
    print("Informe a letra: ")
    var letra = readlnOrNull() ?:""

    while(letra.length > 1) {
        print("Informe apenas uma letra: ")
        letra = readlnOrNull() ?:""
    }

    if(letra != "") {
        val livros = repositorio.filter { it.titulo.startsWith(letra) }
        livros.forEach {println(it)}
    } else {
        println("É necessário informar pelo menos um caracter para esta função executar!")
    }
}

fun listarComPrecoAbaixo(repositorio: MutableList<Livro>) {
    val preco = inputPreco()
    val livros = repositorio.filter { it.preco < preco }
    livros.forEach { println(it) }
}

fun main() {
    val repositorioLivros = mutableListOf<Livro>()
    repositorioLivros.add(Livro("Livro dos Livros", 999999.99))
    repositorioLivros.add(Livro("Turma da Monica", 4.99))
    repositorioLivros.add(Livro("Kotlin for Dummies", 29.99))
    repositorioLivros.add(Livro("A", 59.99))

    var opcao = 0
    while (opcao != 8) {
        menu()
        println(repositorioLivros[0])
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?:8

        when (opcao) {
            1 -> cadastrarLivro(repositorioLivros)
            2 -> excluirLivro(repositorioLivros)
            3 -> {
                val livro = buscarNome(repositorioLivros)
                println(livro)
            }
//            4 ->
            5 -> listar(repositorioLivros)
            6 -> listarComLetraInicial(repositorioLivros)
            7 -> listarComPrecoAbaixo(repositorioLivros)
            8 -> println("Até a próxima :)")
        }
        Thread.sleep(3000)
    }
}