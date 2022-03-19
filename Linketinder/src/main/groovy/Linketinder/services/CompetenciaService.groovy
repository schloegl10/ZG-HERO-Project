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
        return competenciaRepository.findAll()
    }

    boolean criaCompetencias(List<Competencia> competencias) {
        return competenciaRepository.saveAll(competencias)
    }

    boolean criaCompetencia(Competencia competencia) {
        return competenciaRepository.save(competencia)
    }

    boolean atualizaCompetencia(Competencia competencia, Competencia competenciaOriginal) {
        Competencia competenciaOriginalBanco = competenciaRepository.findByDescricaoAndNivel(competenciaOriginal.descricao, competenciaOriginal.nivel)
        competenciaOriginalBanco.descricao = competencia.descricao
        competenciaOriginalBanco.nivel = competencia.nivel
        return competenciaRepository.save(competenciaOriginalBanco)
    }

    boolean deleteCompetencia(Competencia competencia) {
        Competencia competenciaBanco = competenciaRepository.findByDescricaoAndNivel(competencia.descricao, competencia.nivel)
        return competenciaRepository.deleteById(competenciaBanco.id)
    }
}
