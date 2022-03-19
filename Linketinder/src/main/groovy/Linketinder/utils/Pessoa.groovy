package Linketinder.utils

class Pessoa {
    String nome
    String email
    String senha
    String pais
    String estado
    String cep
    String descricao


    Pessoa(String nome, String email, String senha, String pais, String estado, String cep, String descricao) {
        this.nome = nome
        this.email = email
        this.senha = senha
        this.pais = pais
        this.estado = estado
        this.cep = cep
        this.descricao = descricao
    }

    Pessoa(){}

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
