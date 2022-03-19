package Linketinder.utils

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Vaga {
    @Id
    @GeneratedValue
    Long id
    String descricao
    String nome
    String estado
    String cidade
    List<Competencia> competencias
    Vaga(String descricao, String nome, String estado, String cidade, List<Competencia> competencias) {
        this.descricao = descricao
        this.nome = nome
        this.estado = estado
        this.cidade = cidade
        this.competencias = competencias
    }

    Vaga(){}


    @Override
    public String toString() {
        return "Competencia{" +
                "descricao='" + descricao + '\'' +
                ", nivel='" + nivel + '\'' +
                '}';
    }
}
