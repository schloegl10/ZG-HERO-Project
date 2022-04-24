package Linketinder


import Linketinder.repository.CurtidaRepository
import Linketinder.services.CurtidaService
import Linketinder.services.PessoaFisicaService
import Linketinder.services.PessoaJuridicaService
import Linketinder.utils.Curtidas
import Linketinder.utils.PessoaFisica
import Linketinder.utils.PessoaJuridica
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

@MicronautTest
class CurtidaServiceTest extends Specification {

    CurtidaService curtidaService

    void 'test obtem curtida'() {
        when:
        PessoaFisica pessoaFisica = new PessoaFisica()
        PessoaJuridica pessoaJuridica = new PessoaJuridica()
        Curtidas curtidas = new Curtidas()
        curtidas.empresa = pessoaJuridica
        curtidas.candidato = pessoaFisica
        curtidaService = new CurtidaService()
        CurtidaRepository curtidaRepository = Mock(CurtidaRepository.class)
        curtidaRepository.findAll() >> [curtidas]
        curtidaRepository.save(_) >> true
        PessoaFisicaService pessoaFisicaService = Mock(PessoaFisicaService)
        pessoaFisicaService.buscaId(_) >> pessoaFisica
        PessoaJuridicaService pessoaJuridicaService = Mock(PessoaJuridicaService)
        pessoaJuridicaService.buscaId(_) >> pessoaJuridica
        curtidaService.pessoaJuridicaService = pessoaJuridicaService
        curtidaService.pessoaFisicaService = pessoaFisicaService
        curtidaService.curtidaRepository = curtidaRepository

        String resultado = curtidaService.obtemCurtida(1, 1)
        String resultadoEsperado = curtidas.toString()
        then:
        assert resultado == resultadoEsperado
    }

    void 'test cria curtida'() {
        when:
        PessoaFisica pessoaFisica = new PessoaFisica()
        PessoaJuridica pessoaJuridica = new PessoaJuridica()
        Curtidas curtidas = new Curtidas()
        curtidas.empresa = pessoaJuridica
        curtidas.candidato = pessoaFisica
        curtidaService = new CurtidaService()
        CurtidaRepository curtidaRepository = Mock(CurtidaRepository.class)
        curtidaRepository.findAll() >> [curtidas]
        curtidaRepository.save(_) >> true
        PessoaFisicaService pessoaFisicaService = Mock(PessoaFisicaService)
        pessoaFisicaService.buscaId(_) >> pessoaFisica
        PessoaJuridicaService pessoaJuridicaService = Mock(PessoaJuridicaService)
        pessoaJuridicaService.buscaId(_) >> pessoaJuridica
        curtidaService.pessoaJuridicaService = pessoaJuridicaService
        curtidaService.pessoaFisicaService = pessoaFisicaService
        curtidaService.curtidaRepository = curtidaRepository
        String resultado = curtidaService.criarCurtida(1, 1, false, true)
        String resultadoEsperado = true
        then:
        assert resultado == resultadoEsperado
    }
}
