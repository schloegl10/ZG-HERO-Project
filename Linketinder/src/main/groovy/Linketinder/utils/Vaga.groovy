package Linketinder.utils

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.Table

@Entity
@Table(name="vaga")
class Vaga {
    @Id
    @GeneratedValue
    Long id
    String descricao
    String nome
    String estado
    String cidade
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "relacaocompetenciavaga",
            vagaid = @JoinColumn(name = "vagaid"),
            competenciaid = @JoinColumn(name = "competenciaid"))
    List<Competencia> competencias
    @ManyToOne
    @JoinColumn(name="pessoajuridicaid", nullable=false)
    private PessoaJuridica empresa

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
