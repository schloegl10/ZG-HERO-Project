package Linketinder.services


import Linketinder.utils.PessoaJuridica
import Linketinder.utils.SqlUtils
import Linketinder.utils.Vaga
import groovy.sql.GroovyRowResult
import jakarta.inject.Inject

class PessoaJuridicaService {

    //TODO PROJECT implementar repository
    @Inject PessoaJuridica pessoaJuridicaRepository

    List<PessoaJuridica> obtemPessoasJuridicas() {
        List<GroovyRowResult> results = sql.rows(SqlUtils.SELECT_PESSOA_JURIDICA)
        return results
    }

    boolean criaPessoaJuridica(String nome, String email, String senha, String pais, String estado, String cep, String descricao, List<Vaga> vagas, String CNPJ) {
        String insert = SqlUtils.INSERT_PESSOA_JURIDICA + "('${nome}', '${email}', '${senha}', '${pais}', '${estado}', '${cep}', '${descricao}', '${CNPJ}')"
        criaVagas(vagas)
        return sql.execute(insert)
    }

    boolean atualizaPessoaJuridica(String emailOriginal, String senhaOriginal, String nome, String email, String senha, String pais, String estado, String cep, String descricao, List<Vaga> vagas, String CNPJ) {
        String update = SqlUtils.UPDATE_PESSOA_JURIDICA + "nome = '${nome}', AND email = '${email}', AND senha = '${senha}',AND pais = '${pais}', AND estado = '${estado}', AND cep = '${cep}', AND descricao = '${descricao}', AND cnpj = '${CNPJ}' WHERE email = '${emailOriginal}' AND senha = '${senhaOriginal}'"
        //TODO PROJETO criar update das relações com vagas
        return sql.execute(update)
    }

    boolean deletePessoaJuridica(String emailOriginal, String senhaOriginal, String nome, String email, String senha, String pais, String estado, String cep, String descricao, List<Vaga> vagas, String CNPJ) {
        String update = SqlUtils.DELETE_PESSOA_JURIDICA + "WHERE email = '${emailOriginal}' AND senha = '${senhaOriginal}'"
        return sql.execute(update)
    }
}
