package Linketinder.controller

import Linketinder.utils.Competencia
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/")
class LinketinderController {

    BDService BDService = new BDService()

    //TODO PROJETO criar método que retorna sem dados pessoais
    @Get(uri = "/candidatos", produces = MediaType.TEXT_PLAIN)
    String listaCandidatos() {
       return BDService.obtemPessoasFisicas().toString()
    }
    //TODO PROJETO criar método que retorna sem dados pessoais
    @Get(uri = "/empresas", produces = MediaType.TEXT_PLAIN)
    String obtemPessoasJuridicas() {
        return BDService.obtemPessoasFisicas().toString()
    }

    @Get(uri = "/vagas", produces = MediaType.TEXT_PLAIN)
    String obtemVagas() {
        return BDService.obtemVagas().toString()
    }

    @Get(uri = "/competencias", produces = MediaType.TEXT_PLAIN)
    String obtemCompetencias() {
        return BDService.obtemCompetencias().toString()
    }

    @Post(uri = "/criaempresa", produces = MediaType.TEXT_PLAIN)
    String criaEmpresa(String nome, String email, String pais, String estado, String cep, String descricao, List<Vagas> vaga, String CNPJ) {
        return BDService.criaPessoaJuridica(nome, email, pais, estado, cep, descricao, vaga, CNPJ).toString()
    }

    @Post(uri = "/criacandidato", produces = MediaType.TEXT_PLAIN)
    String criaCandidato(String nome, String email, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade) {
        return BDService.criaPessoaFisica(nome, email, pais, estado, cep, descricao, competencias, CPF, idade).toString()
    }

    @Post(uri = "/criavaga", produces = MediaType.TEXT_PLAIN)
    String criaVaga(String descricao, String nome, String estado, String cidade, List<Competencia> competencias) {
        Vaga vaga = new Vaga(descricao, nome, estado, cidade, competencias)
        return BDService.criaVaga(vaga)
    }

    @Post(uri = "/criacompetencia", produces = MediaType.TEXT_PLAIN)
    String criaCompetencia(String descricao, String nivel) {
        Competencia competencia = new Competencia(descricao, nivel)
        return BDService.criaCompetencia(competencia)
    }



    //TODO PROJETO criar update de candidato, empresa, vaga e competencia
    //TODO PROJETO criar delete de candidato, empresa, vaga e competencia
    //TODO PROJETO criar select especifico de candidato, empresa, vaga e competencia
}
