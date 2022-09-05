/*
TODO:
- Ordenar por média da turma?
- Ordenar por níveis
- Talvez fazer com que ID seja automaticamente gerado em ordem de entrada?
- Marcar a turma como média baixa/regular/alta -> para interface com cor vermelha/amarela/verde posteriormente
*/

class Turma(
    var nome:           String, 
    var id:             Int,
    var nivel:          String,
    var listaAlunos:    mutableListOf(Student),
    var numeroAlunos:   Int,
    var mediaTurma:     Float){
    //A idéia é isso ser chamado toda vez que uma turma  é criada - nao sei se o código abaixo realmente funciona assim
    init{
        listaTurmas.add(this) //Ao inicializar uma turma nova, adiciona à lista de turmas
    }
    
    fun adicionarAluno(aluno:Student){
        this.listaAlunos.add(aluno)
        this.numeroAlunos += 1
    }
    fun removerAluno(aluno:Student){
        this.listaAlunos.remove(aluno)
        this.numeroAlunos -= 1
    }

}

//Gera uma lista vazia a ser  populada com cada nova turma criada
val listaTurmas: MutableList<Turma> = mutableListOf()

fun calcAverage(turma:Turma){
    var media:Float = 0
    if(!(turma.listaAlunos.isEmpty())){
        for(aluno in turma.listaAlunos){     // -> Nao sei se funciona <-
            media += getStudentTotalScore(aluno)
        }
        turma.mediaTurma = media / turma.numeroAlunos
        return turma.mediaTurma
    }
}

//Ordenar turmas por ID
fun sortClassByID(){
    var tempList = mutableListOf()
        tempList = addAll(listaTurmas)
        tempList.sortBy{it.id}
    return tempList
}


//Ordenar turmas por Nome
fun sortClassByName(){
    var tempList = mutableListOf()
        tempList = addAll(listaTurmas)
        tempList.sortBy{it.nome}
    return tempList
}

//Ordenar turmas por numero de alunos
fun sortClassByStudents(){
    var tempList = mutableListOf()
        tempList = addAll(listaTurmas)
        tempList.sortBy{it.numeroAlunos}
    return tempList
}


