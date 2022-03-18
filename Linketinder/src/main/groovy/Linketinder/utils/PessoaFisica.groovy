package Linketinder.utils

class PessoaFisica extends Pessoa {
    String CPF
    String sobrenome
    String formacao
    int idade
    List<Competencia> competencias

    PessoaFisica(int id, String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade, String formacao) {
        super(id, nome, email, senha, pais, estado, cep, descricao, competencias)
        this.sobrenome = sobrenome
        this.CPF = CPF
        this.formacao = formacao
        this.idade = idade
        this.competencias = competencias
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
