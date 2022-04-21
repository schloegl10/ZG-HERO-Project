package Linketinder.utils

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.FetchType
import javax.persistence.Table

@Entity
@Table(name="pessoafisica")
class PessoaFisica extends Pessoa {
    @Id
    @GeneratedValue
    Long id
    String CPF
    String sobrenome
    String formacao
    int idade
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "relacaocompetenciapessoa",
            vagaid = @JoinColumn(name = "pessoafisicaid"),
            competenciaid = @JoinColumn(name = "competenciaid"))
    List<Competencia> competencias

    PessoaFisica(String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade, String formacao) {
        super(nome,email,senha,pais, estado, cep, descricao)
        this.sobrenome = sobrenome
        this.CPF = CPF
        this.formacao = formacao
        this.idade = idade
        this.competencias = competencias
    }

    PessoaFisica(){}

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", pais='" + pais + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                ", descricao='" + descricao + '\'' +
                ", competencias=" + competencias +
                ", CPF='" + CPF + '\'' +
                ", idade=" + idade +
                '}';
    }
}
