package Linketinder.services

import Linketinder.repository.CompetenciaRepository
import Linketinder.utils.Competencia
import jakarta.inject.Inject

class CompetenciaService {

    @Inject CompetenciaRepository competenciaRepository

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

    //Buscar todas entidades de um banco para serem filtradas dentro da aplicação não é uma solução viavel para ser usada dentro de uma aplicação do mundo real, mas essa implementação facilita a criação de um filtro que possa filtrar por qualquer combinação de parametros da entidade
    List<Competencia> buscaCompetencia(String descricao, String nivel) {
        List<Competencia> competencias = competenciaRepository.findAll() as List<Competencia>
        competencias = competencias.findAll {Competencia competencia ->
            boolean descricaoIgual = true
            boolean nivelIgual = true
            if(descricao && descricao != competencia.descricao) {
               descricaoIgual = false
            }
            if(nivel && nivel != competencia.nivel) {
                nivelIgual = false
            }
            return (descricaoIgual && nivelIgual)
        }
        return competencias
    }
}
