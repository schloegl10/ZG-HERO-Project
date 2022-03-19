package Linketinder.services

import Linketinder.repository.CompetenciaRepository
import Linketinder.utils.Competencia
import Linketinder.utils.SqlUtils
import groovy.sql.GroovyRowResult
import jakarta.inject.Inject

class CompetenciaService {
//TODO PROJECT implementar repository
    @Inject CompetenciaRepository competenciaRepository

    List<Competencia> obtemCompetencias() {
        List<GroovyRowResult> results = sql.rows(SqlUtils.SELECT_COMPETENCIA)
        return results
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

    boolean atualizaCompetencia(Competencia competencia, Competencia competenciaOriginal) {
        String update = SqlUtils.UPDATE_COMPETENCIA + "descricao = '${competencia.descricao}', nivel = '${competencia.nivel}' WHERE descricao = '${competenciaOriginal.descricao}' AND nivel = '${competenciaOriginal.nivel}'"
        return sql.execute(update)
    }

    boolean deleteCompetencia(Competencia competencia) {
        String update = SqlUtils.DELETE_COMPETENCIA + "WHERE descricao = '${competenciaOriginal.descricao}' AND nivel = '${competenciaOriginal.nivel}'"
        return sql.execute(update)
    }
}
