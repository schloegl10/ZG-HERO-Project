package Linketinder

import Linketinder.controller.LinetinderController
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

@MicronautTest
class LinketinderControllerTest extends Specification {

    LinetinderController linetinderController = new LinetinderController()

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
}
