package Linketinder.services


import Linketinder.utils.PessoaFisica
import Linketinder.utils.SqlUtils
import groovy.sql.GroovyRowResult
import groovy.sql.Sql

class BDService {


    final String url = 'jdbc:postgresql://localhost:5432/linketinder'
    final String user = 'postgres'
    final String password = 'Schloegl@20'


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

    boolean criaVagas(List<Competencia> competencias) {
        List<Boolean> resultados = []
        for(Competencia competencia in competencias) {
            resultados.add(criaCompetencia(competencia))
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
}