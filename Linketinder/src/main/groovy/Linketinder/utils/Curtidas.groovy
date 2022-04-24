package Linketinder.utils

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "curtida")
class Curtidas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    Boolean pessoafisicacurtiu
    Boolean pessoajuridicacurtiu
    @ManyToOne
    @JoinColumn(name = "pessoafisicaid", nullable = false)
    PessoaFisica candidato
    @ManyToOne
    @JoinColumn(name = "pessoajuridicaid", nullable = false)
    PessoaJuridica empresa

    Curtidas(Boolean pessoafisicacurtiu, Boolean pessoajuridicacurtiu, PessoaFisica candidato, PessoaJuridica empresa) {
        this.pessoafisicacurtiu = pessoafisicacurtiu
        this.pessoajuridicacurtiu = pessoajuridicacurtiu
        this.candidato = candidato
        this.empresa = empresa
    }

    Curtidas() {}

    @Override
    public String toString() {
        return "Competencia{" +
                "pessoafisicacurtiu='" + pessoafisicacurtiu + '\'' +
                "pessoajuridicacurtiu='" + pessoajuridicacurtiu + '\'' +
                "candidato='" + candidato.nome + '\'' +
                "empresa='" + empresa.nome + '\'' +
                '}';
    }
}
