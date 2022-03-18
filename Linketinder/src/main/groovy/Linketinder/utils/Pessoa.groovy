package Linketinder.utils

class Pessoa {
    int id
    String nome
    String email
    String senha
    String pais
    String estado
    String cep
    String descricao


    Pessoa(int id, String nome, String email, String senha, String pais, String estado, String cep, String descricao) {
        this.id = id
        this.nome = nome
        this.email = email
        this.senha = senha
        this.pais = pais
        this.estado = estado
        this.cep = cep
        this.descricao = descricao
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
