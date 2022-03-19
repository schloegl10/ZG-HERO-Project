package Linketinder.services

import Linketinder.repository.VagaRepository
import Linketinder.utils.Competencia
import Linketinder.utils.SqlUtils
import Linketinder.utils.Vaga
import groovy.sql.GroovyRowResult
import jakarta.inject.Inject

class VagaService {
//TODO PROJECT implementar repository
    @Inject VagaRepository vagaRepository


    List<Vaga> obtemVagas() {
        List<GroovyRowResult> results = sql.rows(SqlUtils.SELECT_VAGA)
        return results
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

    boolean atualizaVaga(Vaga vaga, Vaga vagaOriginal) {
        String update = SqlUtils.UPDATE_VAGA + "nome = '${vaga.nome}', descricao = '${vaga.descricao}', estado = '${vaga.estado}', cidade = '${vaga.cidade}' WHERE nome = '${vagaOriginal.nome}' descricao = '${vagaOriginal.descricao}' estado = '${vagaOriginal.estado}' cidade = '${vagaOriginal.cidade}'"
        //TODO PROJETO criar update das relações com competência
        return sql.execute(update)
    }

    boolean deleteVaga(Vaga vaga) {
        String update = SqlUtils.DELETE_VAGA + "WHERE nome = '${vagaOriginal.nome}' descricao = '${vagaOriginal.descricao}' estado = '${vagaOriginal.estado}' cidade = '${vagaOriginal.cidade}'"
        return sql.execute(update)
    }
}
