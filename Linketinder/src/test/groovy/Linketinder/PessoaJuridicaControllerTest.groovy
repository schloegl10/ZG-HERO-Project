package Linketinder

import Linketinder.controller.VagaController
import Linketinder.repository.VagaRepository
import Linketinder.services.CompetenciaService
import Linketinder.services.PessoaJuridicaService
import Linketinder.services.VagaService
import Linketinder.utils.Competencia
import Linketinder.utils.PessoaJuridica
import Linketinder.utils.Vaga
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

@MicronautTest
class PessoaJuridicaControllerTest extends Specification {

    VagaController vagaController = new VagaController()

    void 'test cria vaga'() {
        when:
        Competencia competencia = new Competencia()
        PessoaJuridica pessoaJuridica = new PessoaJuridica()
        Vaga vaga =   new Vaga('desc', 'nome', 'estado', 'cidade', [competencia], pessoaJuridica)
        VagaService vagaService = new VagaService()
        VagaRepository vagaRepository = Mock(VagaRepository.class)
        1* vagaRepository.save(_) >> vaga
        vagaService.vagaRepository = vagaRepository
        CompetenciaService competenciaService = Mock(CompetenciaService)
        competenciaService.buscaCompetencia(_) >> competencia
        vagaService.competenciaService = competenciaService
        PessoaJuridicaService pessoaJuridicaService = Mock(PessoaJuridicaService)
        pessoaJuridicaService.buscaId(_) >> pessoaJuridica
        vagaService.pessoaJuridicaService = pessoaJuridicaService
        vagaController.vagaService = vagaService
        String resultado = vagaController.criaVaga('desc', 'nome', 'estado', 'cidade', [1], 1)
        String resultadoEsperado = true
        then:
        assert resultado == resultadoEsperado
    }

    void 'test obtem vaga'() {
        when:
        Competencia competencia = new Competencia('desc', 'nivel')
        PessoaJuridica pessoaJuridica = new PessoaJuridica()
        pessoaJuridica.descricao = 'descricao'
        Vaga vaga =   new Vaga('desc', 'nome', 'estado', 'cidade', [competencia], pessoaJuridica)
        VagaService vagaService = new VagaService()
        VagaRepository vagaRepository = Mock(VagaRepository.class)
        1* vagaRepository.findAll() >> [vaga]
        vagaService.vagaRepository = vagaRepository
        vagaController.vagaService = vagaService
        String resultado = vagaController.obtemVagas()
        String resultadoEsperado = [vaga].toString()
        then:
        assert resultado == resultadoEsperado
    }

    void 'test atualiza vaga'() {
        when:
        Competencia competencia = new Competencia()
        Vaga vaga =   new Vaga('desc', 'nome', 'estado', 'cidade', [competencia], new PessoaJuridica())
        VagaService vagaService = new VagaService()
        VagaRepository vagaRepository = Mock(VagaRepository.class)
        vagaRepository.save(_) >> vaga
        vagaRepository.findById(_) >> Optional.of(vaga)
        vagaService.vagaRepository = vagaRepository
        CompetenciaService competenciaService = Mock(CompetenciaService)
        competenciaService.buscaCompetencia(_) >> competencia
        vagaService.competenciaService = competenciaService
        vagaController.vagaService = vagaService
        String resultado = vagaController.atualizaVaga(1, 'desc', 'nome', 'estado', 'cidade', [1])
        String resultadoEsperado = true
        then:
        assert resultado == resultadoEsperado
    }

    void 'test deleta vaga'() {
        when:
        VagaService vagaService = new VagaService()
        VagaRepository vagaRepository = Mock(VagaRepository.class)
        1* vagaRepository.deleteById(_) >> null
        vagaService.vagaRepository = vagaRepository
        vagaController.vagaService = vagaService
        String resultado = vagaController.deleteVaga(1)
        String resultadoEsperado = true
        then:
        assert resultado == resultadoEsperado
    }

    void 'test obtem vaga id'() {
        when:
        Competencia competencia = new Competencia('desc', 'nivel')
        PessoaJuridica pessoaJuridica = new PessoaJuridica()
        pessoaJuridica.descricao = 'descricao'
        Vaga vaga =   new Vaga('desc', 'nome', 'estado', 'cidade', [competencia], pessoaJuridica)
        VagaService vagaService = new VagaService()
        VagaRepository vagaRepository = Mock(VagaRepository.class)
        1* vagaRepository.findById(_) >> Optional.of(vaga)
        vagaService.vagaRepository = vagaRepository
        vagaController.vagaService = vagaService
        String resultado = vagaController.buscaVaga(1)
        String resultadoEsperado = vaga.toString()
        then:
        assert resultado == resultadoEsperado
    }
}
