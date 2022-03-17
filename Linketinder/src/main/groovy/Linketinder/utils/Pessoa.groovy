package Linketinder.utils

class Pessoa {
    int id
    String nome
    String email
    String pais
    String estado
    String cep
    String descricao
    List<Competencia> competencias

    Pessoa(int id, String nome, String email, String pais, String estado, String cep, String descricao, List<Competencia> competencias) {
        this.id = id
        this.nome = nome
        this.email = email
        this.pais = pais
        this.estado = estado
        this.cep = cep
        this.descricao = descricao
        this.competencias = competencias
    }


    @Override
    String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", pais='" + pais + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                ", descricao='" + descricao + '\'' +
                ", competencias=" + competencias +
                '}';
    }
}
