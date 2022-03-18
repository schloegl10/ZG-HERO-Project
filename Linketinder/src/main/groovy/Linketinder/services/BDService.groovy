package Linketinder.services


import Linketinder.utils.PessoaFisica
import Linketinder.utils.SqlUtils
import groovy.sql.GroovyRowResult
import groovy.sql.Sql

class BDService {


    final String url = 'jdbc:postgresql://localhost:5432/linketinder'
    final String user = 'postgres'
    final String password = 'Schloegl@20'

//TODO PROJETO separar em mais services divididos por pessoa fisica, juridica, competencia e vaga
    Sql sql

    LinketinderService() {
        sql = Sql.newInstance(url, user, password)
    }

    List<PessoaFisica> obtemPessoasFisicas() {
        List<GroovyRowResult> results = sql.rows(SqlUtils.SELECT_PESSOA_FISICA)
        return results
    }

    List<PessoaJuridica> obtemPessoasJuridicas() {
        List<GroovyRowResult> results = sql.rows(SqlUtils.SELECT_PESSOA_JURIDICA)
        return results
    }

    List<Vaga> obtemVagas() {
        List<GroovyRowResult> results = sql.rows(SqlUtils.SELECT_VAGA)
        return results
    }

    List<Competencia> obtemCompetencias() {
        List<GroovyRowResult> results = sql.rows(SqlUtils.SELECT_COMPETENCIA)
        return results
    }

    boolean criaPessoaFisica(String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade, String formacao) {
        String insert = SqlUtils.INSERT_PESSOA_FISICA + "('${nome}', '${sobrenome}', '${email}', '${senha}', '${pais}', '${estado}', '${cep}', '${descricao}', '${CPF}', '${formacao}', '${idade}')"
        criaCompetencias(competencias)
        criaRelacoesCompetenciaPessoa(competencias, email, senha)
        return sql.execute(insert)
    }

    boolean criaPessoaJuridica(String nome, String email, String senha, String pais, String estado, String cep, String descricao, List<Vaga> vagas, String CNPJ) {
        String insert = SqlUtils.INSERT_PESSOA_JURIDICA + "('${nome}', '${email}', '${senha}', '${pais}', '${estado}', '${cep}', '${descricao}', '${CNPJ}')"
        criaVagas(vagas)
        return sql.execute(insert)
    }

    boolean criaCompetencias(List<Competencia> competencias) {
        List<Boolean> resultados = []
        for(Competencia competencia in competencias) {
            resultados.add(criaCompetencia(competencia))
        }
        if(resultados.find(false)) {
            return false
        }
        return true
    }

    boolean criaCompetencia(Competencia competencia) {
        String insert = SqlUtils.INSERT_COMPETENCIA + "('${competencia.descricao}', '${competencia.nivel}')"
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

    boolean criaVagas(List<Vaga> vagas) {
        List<Boolean> resultados = []
        for(Vaga vaga in vagas) {
            resultados.add(criaCompetencia(vaga))
        }
        if(resultados.find(false)) {
            return false
        }
        return true
    }

    boolean criaVaga(Vaga vaga) {
        String insert = SqlUtils.INSERT_VAGA + "('${vaga.nome}', '${vaga.descricao}', '${vaga.estado}', '${vaga.cidade}', '${vaga.pessoajuridicaid}')"
        criaRelacoesVagaCompetencia(vaga)
        return sql.execute(insert)
    }

    boolean criaRelacoesVagaCompetencia(Vaga vagas) {
        List<Boolean> resultados = []
        for(Competencia competencia in vagas.competencias) {
            resultados.add(criaRelacaoVagaCompetencia(vaga, competencia))
        }
        if(resultados.find(false)) {
            return false
        }
        return true
    }

    boolean criaRelacaoVagaCompetencia(Vaga vaga, Competencia competencia) {
        String selectVaga = SqlUtils.SELECT_VAGA + " where nome = '${vaga.nome}' and descricao = '${vaga.descricao}' and estado = '${vaga.estado}' and cidade = '${vaga.cidade}'"
        String selectCompetencia = SqlUtils.SELECT_COMPETENCIA + " where descricao = '${competencia.descricao}' AND nivel = '${competencia.nivel}'"
        List<GroovyRowResult> competenciaGRR = sql.rows(selectCompetencia)
        List<GroovyRowResult> vagaGRR = sql.rows(selectVaga)
        String insert = SqlUtils.INSERT_RELACAO_COMPETENCIA_VAGA + "(${competenciaGRR.id}, ${vagaGRR.id})"
        return sql.execute(insert)
    }

    boolean atualizaPessoaFisica(String emailOriginal, String senhaOriginal, String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade, String formacao) {
        String update = SqlUtils.UPDATE_PESSOA_FISICA + "nome = '${nome}', AND email = '${email}', AND senha = '${senha}',AND pais = '${pais}', AND estado = '${estado}', AND cep = '${cep}', AND descricao = '${descricao}', AND cpf = '${CPF}', AND sobrenome = '${sobrenome}', AND idade = ${idade} AND formacao = '${formacao}' WHERE email = '${emailOriginal}' AND senha = '${senhaOriginal}'"
        //TODO PROJETO criar update das relações com competência
        return sql.execute(update)
    }

    boolean atualizaPessoaJuridica(String emailOriginal, String senhaOriginal, String nome, String email, String senha, String pais, String estado, String cep, String descricao, List<Vaga> vagas, String CNPJ) {
        String update = SqlUtils.UPDATE_PESSOA_JURIDICA + "nome = '${nome}', AND email = '${email}', AND senha = '${senha}',AND pais = '${pais}', AND estado = '${estado}', AND cep = '${cep}', AND descricao = '${descricao}', AND cnpj = '${CNPJ}' WHERE email = '${emailOriginal}' AND senha = '${senhaOriginal}'"
        //TODO PROJETO criar update das relações com vagas
        return sql.execute(update)
    }

    boolean atualizaVaga(Vaga vaga, Vaga vagaOriginal) {
        String update = SqlUtils.UPDATE_VAGA + "nome = '${vaga.nome}', descricao = '${vaga.descricao}', estado = '${vaga.estado}', cidade = '${vaga.cidade}' WHERE nome = '${vagaOriginal.nome}' descricao = '${vagaOriginal.descricao}' estado = '${vagaOriginal.estado}' cidade = '${vagaOriginal.cidade}'"
        //TODO PROJETO criar update das relações com competência
        return sql.execute(update)
    }

    boolean atualizaCompetencia(Competencia competencia, Competencia competenciaOriginal) {
        String update = SqlUtils.INSERT_COMPETENCIA + "descricao = '${competencia.descricao}', nivel = '${competencia.nivel}' WHERE descricao = '${competenciaOriginal.descricao}' AND nivel = '${competenciaOriginal.nivel}'"
        return sql.execute(update)
    }
}
