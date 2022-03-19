package Linketinder.services

import Linketinder.repository.PessoaFisicaRepository
import Linketinder.utils.Competencia
import Linketinder.utils.PessoaFisica
import Linketinder.utils.SqlUtils
import groovy.sql.GroovyRowResult
import jakarta.inject.Inject

class PessoaFisicaService {

    @Inject PessoaFisicaRepository pessoaFisicaRepository

    List<PessoaFisica> obtemPessoasFisicas() {
        List<GroovyRowResult> results = sql.rows(SqlUtils.SELECT_PESSOA_FISICA)
        return results
    }

    boolean criaPessoaFisica(String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade, String formacao) {
        String insert = SqlUtils.INSERT_PESSOA_FISICA + "('${nome}', '${sobrenome}', '${email}', '${senha}', '${pais}', '${estado}', '${cep}', '${descricao}', '${CPF}', '${formacao}', '${idade}')"
        criaCompetencias(competencias)
        criaRelacoesCompetenciaPessoa(competencias, email, senha)
        return sql.execute(insert)
    }

    boolean criaRelacoesCompetenciaPessoa(List<Competencia> competencias, String email, String senha) {
        List<Boolean> resultados = []
        for(Competencia competencia in competencias) {
            resultados.add(criaRelacaoCompetenciaPessoa(competencia, email, senha))
        }
        if(resultados.find(false)) {
            return false
        }
        return true
    }

    boolean criaRelacaoCompetenciaPessoa(Competencia competencia, String email, String senha) {
        String selectPessoa = SqlUtils.SELECT_PESSOA_FISICA + " where email = '${email}' and senha = '${senha}'"
        String selectCompetencia = SqlUtils.SELECT_COMPETENCIA + " where descricao = '${competencia.descricao}' AND nivel = '${competencia.nivel}'"
        List<GroovyRowResult> pessoa = sql.rows(selectPessoa)
        List<GroovyRowResult> competenciaGRR = sql.rows(selectCompetencia)
        String insert = SqlUtils.INSERT_RELACAO_COMPETENCIA_PESSOA + "(${competenciaGRR.id}, ${pessoa.id})"
        return sql.execute(insert)
    }

    boolean atualizaPessoaFisica(String emailOriginal, String senhaOriginal, String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade, String formacao) {
        String update = SqlUtils.UPDATE_PESSOA_FISICA + "nome = '${nome}', AND email = '${email}', AND senha = '${senha}',AND pais = '${pais}', AND estado = '${estado}', AND cep = '${cep}', AND descricao = '${descricao}', AND cpf = '${CPF}', AND sobrenome = '${sobrenome}', AND idade = ${idade} AND formacao = '${formacao}' WHERE email = '${emailOriginal}' AND senha = '${senhaOriginal}'"
        //TODO PROJETO criar update das relações com competência
        return sql.execute(update)
    }

    boolean deletePessoaFisica(String emailOriginal, String senhaOriginal, String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade, String formacao) {
        String update = SqlUtils.DELETE_PESSOA_FISICA + "WHERE email = '${emailOriginal}' AND senha = '${senhaOriginal}'"
        return sql.execute(update)
    }
}
