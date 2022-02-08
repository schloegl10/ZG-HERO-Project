package Linketinder

import Linketinder.controller.LinketinderController
import Linketinder.utils.Competencia
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

@MicronautTest
class LinketinderControllerTest extends Specification {

    LinketinderController linetinderController = new LinketinderController()

    void 'test obtem listaCandidatos'() {
        when:
        String resultado = linetinderController.listaCandidatos()
        String resultadoEsperado = "[PessoaFisica{nome='Carlos1', email='abc1@hotmail.com', pais='China', estado='China', cep='74000001', descricao='Sou um morador da china querendo trabalhar no Brasil', competencias=[Competencia{descricao='Groovy', nivel='Avancado'}], CPF='12345678912', idade=21}, PessoaFisica{nome='Carlos2', email='abc2@hotmail.com', pais='China', estado='China', cep='74000002', descricao='Sou um morador da china querendo trabalhar no Brasil', competencias=[Competencia{descricao='Groovy', nivel='Avancado'}, Competencia{descricao='Java', nivel='Avancado'}], CPF='12345678912', idade=22}, PessoaFisica{nome='Carlos3', email='abc3@hotmail.com', pais='China', estado='China', cep='74000003', descricao='Sou um morador da china querendo trabalhar no Brasil', competencias=[Competencia{descricao='Groovy', nivel='Avancado'}, Competencia{descricao='Postgres', nivel='Avancado'}], CPF='12345678912', idade=23}, PessoaFisica{nome='Carlos4', email='abc4@hotmail.com', pais='China', estado='China', cep='74000004', descricao='Sou um morador da china querendo trabalhar no Brasil', competencias=[Competencia{descricao='Java', nivel='Avancado'}], CPF='12345678912', idade=24}, PessoaFisica{nome='Carlos5', email='abc5@hotmail.com', pais='China', estado='China', cep='74000005', descricao='Sou um morador da china querendo trabalhar no Brasil', competencias=[Competencia{descricao='Postgres', nivel='Avancado'}], CPF='12345678912', idade=25}]"
        then:
        assert resultado == resultadoEsperado
    }

    void 'test obtem listaEmpresas'() {
        when:
        String resultado = linetinderController.listaEmpresas()
        String resultadoEsperado = "[PessoaJuridica{nome='AppleBr', email='AppleBr@hotmail.com', pais='Brasil', estado='GO', cep='74000001', descricao='Uma otima empresa para se trabalhar', competencias=[Competencia{descricao='Groovy', nivel='Avancado'}], CNPJ='12345678912'}, PessoaJuridica{nome='GoogleBr', email='GoogleBr@hotmail.com', pais='Brasil', estado='GO', cep='74000002', descricao='Uma otima empresa para se trabalhar', competencias=[Competencia{descricao='Groovy', nivel='Avancado'}, Competencia{descricao='Java', nivel='Avancado'}], CNPJ='12345678912'}, PessoaJuridica{nome='MicrosoftBr', email='MicrosoftBr@hotmail.com', pais='Brasil', estado='GO', cep='74000003', descricao='Uma otima empresa para se trabalhar', competencias=[Competencia{descricao='Groovy', nivel='Avancado'}, Competencia{descricao='Postgres', nivel='Avancado'}], CNPJ='12345678912'}, PessoaJuridica{nome='LinkedinBr', email='LinkedinBr@hotmail.com', pais='Brasil', estado='GO', cep='74000004', descricao='Uma otima empresa para se trabalhar', competencias=[Competencia{descricao='Java', nivel='Avancado'}], CNPJ='12345678912'}, PessoaJuridica{nome='TinderBr', email='TinderBr@hotmail.com', pais='Brasil', estado='GO', cep='74000005', descricao='Uma otima empresa para se trabalhar', competencias=[Competencia{descricao='Postgres', nivel='Avancado'}], CNPJ='12345678912'}]"
        then:
        assert resultado ==  resultadoEsperado
    }

    void 'test cria empresa'() {
        given:
        LinketinderController linketinderControllerCriacao = new LinketinderController()
        when:
        boolean resultadoCriacao = linketinderControllerCriacao.criaEmpresa('Test', 'test@tes.t', 'test', 'test', 'test', 'test', [new Competencia('test', 'test')], 'test')
        String resultado = linketinderControllerCriacao.listaEmpresas()
        String resultadoEsperado = "[PessoaJuridica{nome='AppleBr', email='AppleBr@hotmail.com', pais='Brasil', estado='GO', cep='74000001', descricao='Uma otima empresa para se trabalhar', competencias=[Competencia{descricao='Groovy', nivel='Avancado'}], CNPJ='12345678912'}, PessoaJuridica{nome='GoogleBr', email='GoogleBr@hotmail.com', pais='Brasil', estado='GO', cep='74000002', descricao='Uma otima empresa para se trabalhar', competencias=[Competencia{descricao='Groovy', nivel='Avancado'}, Competencia{descricao='Java', nivel='Avancado'}], CNPJ='12345678912'}, PessoaJuridica{nome='MicrosoftBr', email='MicrosoftBr@hotmail.com', pais='Brasil', estado='GO', cep='74000003', descricao='Uma otima empresa para se trabalhar', competencias=[Competencia{descricao='Groovy', nivel='Avancado'}, Competencia{descricao='Postgres', nivel='Avancado'}], CNPJ='12345678912'}, PessoaJuridica{nome='LinkedinBr', email='LinkedinBr@hotmail.com', pais='Brasil', estado='GO', cep='74000004', descricao='Uma otima empresa para se trabalhar', competencias=[Competencia{descricao='Java', nivel='Avancado'}], CNPJ='12345678912'}, PessoaJuridica{nome='TinderBr', email='TinderBr@hotmail.com', pais='Brasil', estado='GO', cep='74000005', descricao='Uma otima empresa para se trabalhar', competencias=[Competencia{descricao='Postgres', nivel='Avancado'}], CNPJ='12345678912'}, PessoaJuridica{nome='Test', email='test@tes.t', pais='test', estado='test', cep='test', descricao='test', competencias=[Competencia{descricao='test', nivel='test'}], CNPJ='test'}]"
        then:
        assert resultadoCriacao
        assert resultado ==  resultadoEsperado
    }

    void 'test cria candidato'() {
        given:
        LinketinderController linketinderControllerCriacao = new LinketinderController()
        when:
        boolean resultadoCriacao = linketinderControllerCriacao.criaCandidato('Test', 'test@tes.t', 'test', 'test', 'test', 'test', [new Competencia('test', 'test')], 'test', 1)
        String resultado = linketinderControllerCriacao.listaCandidatos()
        String resultadoEsperado = "[PessoaFisica{nome='Carlos1', email='abc1@hotmail.com', pais='China', estado='China', cep='74000001', descricao='Sou um morador da china querendo trabalhar no Brasil', competencias=[Competencia{descricao='Groovy', nivel='Avancado'}], CPF='12345678912', idade=21}, PessoaFisica{nome='Carlos2', email='abc2@hotmail.com', pais='China', estado='China', cep='74000002', descricao='Sou um morador da china querendo trabalhar no Brasil', competencias=[Competencia{descricao='Groovy', nivel='Avancado'}, Competencia{descricao='Java', nivel='Avancado'}], CPF='12345678912', idade=22}, PessoaFisica{nome='Carlos3', email='abc3@hotmail.com', pais='China', estado='China', cep='74000003', descricao='Sou um morador da china querendo trabalhar no Brasil', competencias=[Competencia{descricao='Groovy', nivel='Avancado'}, Competencia{descricao='Postgres', nivel='Avancado'}], CPF='12345678912', idade=23}, PessoaFisica{nome='Carlos4', email='abc4@hotmail.com', pais='China', estado='China', cep='74000004', descricao='Sou um morador da china querendo trabalhar no Brasil', competencias=[Competencia{descricao='Java', nivel='Avancado'}], CPF='12345678912', idade=24}, PessoaFisica{nome='Carlos5', email='abc5@hotmail.com', pais='China', estado='China', cep='74000005', descricao='Sou um morador da china querendo trabalhar no Brasil', competencias=[Competencia{descricao='Postgres', nivel='Avancado'}], CPF='12345678912', idade=25}, PessoaFisica{nome='Test', email='test@tes.t', pais='test', estado='test', cep='test', descricao='test', competencias=[Competencia{descricao='test', nivel='test'}], CPF='test', idade=1}]"
        then:
        assert resultadoCriacao
        assert resultado ==  resultadoEsperado
    }
}
