package Linketinder.utils

import javax.persistence.*

@Entity
@Table(name = "pessoafisica")
class PessoaFisica {
    @Id
    @GeneratedValue
    Long id
    String CPF
    String sobrenome
    String formacao
    int idade
    String nome
    String email
    String senha
    String pais
    String estado
    String cep
    String descricao
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "relacaocompetenciapessoa",
            joinColumns = @JoinColumn(name = "pessoafisicaid"),
            inverseJoinColumns = @JoinColumn(name = "competenciaid"))
    List<Competencia> competencias
    @OneToMany(mappedBy = "candidato")
    List<Curtidas> curtidas

    PessoaFisica(String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade, String formacao) {
        this.nome = nome
        this.email = email
        this.senha = senha
        this.pais = pais
        this.estado = estado
        this.cep = cep
        this.descricao = descricao
        this.sobrenome = sobrenome
        this.CPF = CPF
        this.formacao = formacao
        this.idade = idade
        this.competencias = competencias
    }

    PessoaFisica() {}

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
