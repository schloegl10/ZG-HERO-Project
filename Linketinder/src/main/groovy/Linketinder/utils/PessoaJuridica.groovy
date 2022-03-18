package Linketinder.utils

class PessoaJuridica extends Pessoa {
    String CNPJ
    List<Vaga> vagas

    PessoaJuridica(int id, String nome, String email, String senha, String pais, String estado, String cep, String descricao, List<Vaga> vagas, String CNPJ) {
        super(id, nome, email, senha, pais, estado, cep, descricao)
        this.CNPJ = CNPJ
        this.vagas = vagas
    }


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
