package Linketinder.utils

class Vaga {
    String descricao
    String nome
    String estado
    String cidade
    List<Competencia> competencias
    Vaga(String descricao, String nome, String estado, String cidade, List<Competencia> competencias) {
        this.descricao = descricao
        this.nome = nome
        this.estado = estado
        this.cidade = cidade
        this.competencias = competencias
    }


    @Override
    public String toString() {
        return "Competencia{" +
                "descricao='" + descricao + '\'' +
                ", nivel='" + nivel + '\'' +
                '}';
    }
}
