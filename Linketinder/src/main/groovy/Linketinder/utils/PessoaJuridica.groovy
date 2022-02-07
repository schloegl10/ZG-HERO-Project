package Linketinder.utils

class PessoaJuridica extends Pessoa {
    String CNPJ

    PessoaJuridica(String nome, String email, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CNPJ) {
        super(nome, email, pais, estado, cep, descricao, competencias)
        this.CNPJ = CNPJ
    }
}
