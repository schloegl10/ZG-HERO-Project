package Linketinder.utils

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "competencia")
class Competencia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    String descricao
    String nivel

    Competencia(String descricao, String nivel) {
        this.descricao = descricao
        this.nivel = nivel
    }

    Competencia() {}

    @Override
    public String toString() {
        return "Competencia{" +
                "descricao='" + descricao + '\'' +
                ", nivel='" + nivel + '\'' +
                '}';
    }
}
