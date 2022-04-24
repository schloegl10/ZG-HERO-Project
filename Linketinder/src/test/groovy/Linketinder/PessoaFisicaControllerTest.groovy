package Linketinder

import Linketinder.controller.PessoaFisicaController
import Linketinder.controller.VagaController
import Linketinder.repository.PessoaFisicaRepository
import Linketinder.repository.VagaRepository
import Linketinder.services.CompetenciaService
import Linketinder.services.PessoaFisicaService
import Linketinder.services.PessoaJuridicaService
import Linketinder.services.VagaService
import Linketinder.utils.Competencia
import Linketinder.utils.PessoaFisica
import Linketinder.utils.PessoaJuridica
import Linketinder.utils.Vaga
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

@MicronautTest
class PessoaFisicaControllerTest extends Specification {

    PessoaFisicaController pessoaFisicaController

    void 'test cria pessoa'() {
        when:
        pessoaFisicaController = new PessoaFisicaController()
        PessoaFisicaService pessoaFisicaService = new PessoaFisicaService()
        PessoaFisicaRepository pessoaFisicaRepository = Mock(PessoaFisicaRepository)
        pessoaFisicaRepository.save(_) >> true
        pessoaFisicaService.pessoaFisicaRepository = pessoaFisicaRepository
        CompetenciaService competenciaService = Mock(CompetenciaService)
        competenciaService.buscaCompetencia(_) >> new Competencia()
        pessoaFisicaController.competenciaService = competenciaService
        pessoaFisicaController.pessoaFisicaService = pessoaFisicaService
        String resultado = pessoaFisicaController.criaCandidato('nome', 'sobrenome', 'email', 'senha', 'pais', 'estado', 'cep', 'descricao', [1], 'CPF', 1, 'formacao')
        String resultadoEsperado = true
        then:
        assert resultado == resultadoEsperado
    }

    void 'test lista pessoa'() {
        when:
        pessoaFisicaController = new PessoaFisicaController()
        PessoaFisica pessoaFisica = new PessoaFisica()
        PessoaFisicaService pessoaFisicaService = new PessoaFisicaService()
        PessoaFisicaRepository pessoaFisicaRepository = Mock(PessoaFisicaRepository)
        pessoaFisicaRepository.findAll() >> [pessoaFisica]
        pessoaFisicaService.pessoaFisicaRepository = pessoaFisicaRepository
        pessoaFisicaController.pessoaFisicaService = pessoaFisicaService
        String resultado = pessoaFisicaController.listaCandidatos()
        String resultadoEsperado = [pessoaFisica].toString()
        then:
        assert resultado == resultadoEsperado
    }

    void 'test atualiza pessoa'() {
        when:
        pessoaFisicaController = new PessoaFisicaController()
        PessoaFisica pessoaFisica = new PessoaFisica()
        PessoaFisicaService pessoaFisicaService = new PessoaFisicaService()
        PessoaFisicaRepository pessoaFisicaRepository = Mock(PessoaFisicaRepository)
        pessoaFisicaRepository.findById(_) >> [pessoaFisica]
        pessoaFisicaRepository.save(_) >> true
        CompetenciaService competenciaService = Mock(CompetenciaService)
        competenciaService.buscaCompetencia(_) >> new Competencia()
        pessoaFisicaController.competenciaService = competenciaService
        pessoaFisicaService.pessoaFisicaRepository = pessoaFisicaRepository
        pessoaFisicaController.pessoaFisicaService = pessoaFisicaService
        String resultado = pessoaFisicaController.atualizaCandidato(1, 'nome', 'sobrenome', 'email', 'senha', 'pais', 'estado', 'cep', 'descricao', [1], 'CPF', 1, 'formacao')
        String resultadoEsperado = true
        then:
        assert resultado == resultadoEsperado
    }

    void 'test delete pessoa'() {
        when:
        pessoaFisicaController = new PessoaFisicaController()
        PessoaFisicaService pessoaFisicaService = new PessoaFisicaService()
        PessoaFisicaRepository pessoaFisicaRepository = Mock(PessoaFisicaRepository)
        pessoaFisicaRepository.deleteById(_) >> null
        pessoaFisicaService.pessoaFisicaRepository = pessoaFisicaRepository
        pessoaFisicaController.pessoaFisicaService = pessoaFisicaService
        String resultado = pessoaFisicaController.deleteCandidato(1)
        String resultadoEsperado = true
        then:
        assert resultado == resultadoEsperado
    }
}
