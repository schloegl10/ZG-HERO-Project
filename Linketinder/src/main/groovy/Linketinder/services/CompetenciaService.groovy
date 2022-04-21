package Linketinder.services

import Linketinder.repository.CompetenciaRepository
import Linketinder.utils.Competencia
import jakarta.inject.Inject

class CompetenciaService {

    @Inject
    CompetenciaRepository competenciaRepository

    List<Competencia> obtemCompetencias() {
        return competenciaRepository.findAll() as List<Competencia>
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

    List<Competencia> buscaCompetencia(String descricao, String nivel) {
        List<Competencia> competencias = competenciaRepository.findByDescricaoAndNivel(descricao, nivel)
        return competencias
    }

    Competencia buscaCompetencia(Long id) {
        Competencia competencias = competenciaRepository.findById(id)
        return competencias
    }
}
