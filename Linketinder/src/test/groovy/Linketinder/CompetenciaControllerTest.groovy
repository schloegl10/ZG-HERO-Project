package Linketinder

import Linketinder.controller.CompetenciaController
import Linketinder.repository.CompetenciaRepository
import Linketinder.services.CompetenciaService
import Linketinder.utils.Competencia
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

@MicronautTest
class CompetenciaControllerTest extends Specification {

    CompetenciaController competenciaController

    void 'test obtem competencia'() {
        when:
        competenciaController = new CompetenciaController()
        Competencia competencia = new Competencia()
        CompetenciaService competenciaService = new CompetenciaService()
        CompetenciaRepository competenciaRepository = Mock(CompetenciaRepository.class)
        competenciaRepository.findAll() >> [competencia]
        competenciaService.competenciaRepository = competenciaRepository
        competenciaController.competenciaService = competenciaService
        String resultado = competenciaController.obtemCompetencias()
        String resultadoEsperado = [competencia].toString()
        then:
        assert resultado == resultadoEsperado
    }

    void 'test cria competencia'() {
        when:
        competenciaController = new CompetenciaController()
        CompetenciaService competenciaService = new CompetenciaService()
        CompetenciaRepository competenciaRepository = Mock(CompetenciaRepository.class)
        competenciaRepository.save(_) >> true
        competenciaService.competenciaRepository = competenciaRepository
        competenciaController.competenciaService = competenciaService
        String resultado = competenciaController.criaCompetencia('descricao', '1')
        String resultadoEsperado = true
        then:
        assert resultado == resultadoEsperado
    }

    void 'test delete competencia'() {
        when:
        competenciaController = new CompetenciaController()
        CompetenciaService competenciaService = new CompetenciaService()
        CompetenciaRepository competenciaRepository = Mock(CompetenciaRepository.class)
        competenciaRepository.deleteById(_) >> null
        competenciaService.competenciaRepository = competenciaRepository
        competenciaController.competenciaService = competenciaService
        String resultado = competenciaController.deleteCompetencia(1)
        String resultadoEsperado = true
        then:
        assert resultado == resultadoEsperado
    }

    void 'test busca competencia'() {
        when:
        competenciaController = new CompetenciaController()
        Competencia competencia = new Competencia()
        CompetenciaService competenciaService = new CompetenciaService()
        CompetenciaRepository competenciaRepository = Mock(CompetenciaRepository.class)
        competenciaRepository.findById(_) >> Optional.of(competencia)
        competenciaService.competenciaRepository = competenciaRepository
        competenciaController.competenciaService = competenciaService
        String resultado = competenciaController.buscaCompetencia(1)
        String resultadoEsperado = competencia.toString()
        then:
        assert resultado == resultadoEsperado
    }
}
