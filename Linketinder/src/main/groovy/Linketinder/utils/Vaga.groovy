package Linketinder.utils

import javax.persistence.*

@Entity
@Table(name = "vaga")
class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    PessoaJuridica empresa

    Vaga(String descricao, String nome, String estado, String cidade, List<Competencia> competencias, PessoaJuridica empresa) {
        this.descricao = descricao
        this.nome = nome
        this.estado = estado
        this.cidade = cidade
        this.competencias = competencias
        this.empresa = empresa
    }

    Vaga() {}


    @Override
    public String toString() {
        return "Vaga{" +
                "descricao='" + descricao + '\'' +
                ", nome='" + nome + '\'' +
                ", estado='" + estado + '\'' +
                ", cidade='" + cidade + '\'' +
                ", comptencias='" + competencias + '\'' +
                ", empresa='" + empresa.nome + '\'' +
                '}';
    }
}
