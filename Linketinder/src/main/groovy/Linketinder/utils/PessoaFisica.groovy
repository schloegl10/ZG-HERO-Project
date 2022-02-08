package Linketinder.utils

class PessoaFisica extends Pessoa {
    String CPF
    int idade

    PessoaFisica(String nome, String email, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade) {
        super(nome, email, pais, estado, cep, descricao, competencias)
        this.CPF = CPF
        this.idade = idade
    }

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
