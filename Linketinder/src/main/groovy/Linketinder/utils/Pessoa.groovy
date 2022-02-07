package Linketinder.utils

class Pessoa {
    String nome
    String email
    String pais
    String estado
    String cep
    String descricao
    List<Competencia> competencias

    Pessoa(String nome, String email, String pais, String estado, String cep, String descricao, List<Competencia> competencias) {
        this.nome = nome
        this.email = email
        this.pais = pais
        this.estado = estado
        this.cep = cep
        this.descricao = descricao
        this.competencias = competencias
    }
}
