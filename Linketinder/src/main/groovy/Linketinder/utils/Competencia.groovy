package Linketinder.utils

class Competencia {
    String descricao
    String nivel

    Competencia(String descricao, String nivel) {
        this.descricao = descricao
        this.nivel = nivel
    }


    @Override
    public String toString() {
        return "Competencia{" +
                "descricao='" + descricao + '\'' +
                ", nivel='" + nivel + '\'' +
                '}';
    }
}
