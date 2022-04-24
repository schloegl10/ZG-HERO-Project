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

    boolean atualizaCompetencia(Long id, String descricao, String nivel) {
        Competencia competenciaOriginalBanco = competenciaRepository.findById(id).get()
        competenciaOriginalBanco.descricao = descricao
        competenciaOriginalBanco.nivel = nivel
        return competenciaRepository.save(competenciaOriginalBanco)
    }

    boolean deleteCompetencia(Long id) {
        try {
            competenciaRepository.deleteById(id)
            return true
        } catch (Exception e) {
            return false
        }
    }

    List<Competencia> buscaCompetencia(String descricao, String nivel) {
        List<Competencia> competencias = competenciaRepository.findByDescricaoAndNivel(descricao, nivel)
        return competencias
    }

    Competencia buscaCompetencia(Long id) {
        Competencia competencias = competenciaRepository.findById(id).get()
        return competencias
    }
}
