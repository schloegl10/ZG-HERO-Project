package Linketinder.utils

import javax.persistence.*

@Entity
@Table(name = "vaga")
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
            joinColumns = @JoinColumn(name = "vagaid"),
            inverseJoinColumns = @JoinColumn(name = "competenciaid"))
    List<Competencia> competencias
    @ManyToOne
    @JoinColumn(name = "pessoajuridicaid", nullable = false)
    private PessoaJuridica empresa

    Vaga(String descricao, String nome, String estado, String cidade, List<Competencia> competencias) {
        this.descricao = descricao
        this.nome = nome
        this.estado = estado
        this.cidade = cidade
        this.competencias = competencias
    }

    Vaga() {}


    @Override
    public String toString() {
        return "Competencia{" +
                "descricao='" + descricao + '\'' +
                ", nivel='" + nivel + '\'' +
                '}';
    }
}
