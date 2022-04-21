package Linketinder.utils

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.OneToMany

@Entity
@Table(name="pessoajuridica")
class PessoaJuridica extends Pessoa {
    @Id
    @GeneratedValue
    Long id
    String CNPJ
    @OneToMany(mappedBy="vaga")
    List<Vaga> vagas;

    PessoaJuridica(String nome, String email, String senha, String pais, String estado, String cep, String descricao, List<Vaga> vagas, String CNPJ) {
        super(nome, email, senha, pais, estado, cep, descricao)
        this.CNPJ = CNPJ
        this.vagas = vagas
    }

    PessoaJuridica(){}

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
