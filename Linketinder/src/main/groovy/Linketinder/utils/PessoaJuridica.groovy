package Linketinder.utils

import javax.persistence.*

@Entity
@Table(name = "pessoajuridica")
class PessoaJuridica {
    @Id
    @GeneratedValue
    Long id
    String CNPJ
    @OneToMany(mappedBy = "empresa")
    List<Vaga> vagas
    String nome
    String email
    String senha
    String pais
    String estado
    String cep
    String descricao

    PessoaJuridica(String nome, String email, String senha, String pais, String estado, String cep, String descricao, List<Vaga> vagas, String CNPJ) {
        this.nome = nome
        this.email = email
        this.senha = senha
        this.pais = pais
        this.estado = estado
        this.cep = cep
        this.descricao = descricao
        this.CNPJ = CNPJ
        this.vagas = vagas
    }

    PessoaJuridica() {}

    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", pais='" + pais + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                ", descricao='" + descricao + '\'' +
                ", competencias=" + competencias +
                ", CNPJ='" + CNPJ + '\'' +
                '}';
    }
}
