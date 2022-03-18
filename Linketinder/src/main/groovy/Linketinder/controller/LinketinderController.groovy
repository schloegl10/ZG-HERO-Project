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
       return linketinderService.obtemPessoasFisicas().toString()
    }
    //TODO PROJETO criar método que retorna sem dados pessoais
    @Get(uri = "/empresas", produces = MediaType.TEXT_PLAIN)
    String obtemPessoasJuridicas() {
        return linketinderService.obtemPessoasFisicas()
    }

    @Post(uri = "/criaempresa", produces = MediaType.TEXT_PLAIN)
    String criaEmpresa(int id, String nome, String email, String pais, String estado, String cep, String descricao, List<Vagas> vaga, String CNPJ) {
        criaPessoaJuridica(nome, email, pais, estado, cep, descricao, vaga, CNPJ)
        return "sucesso";
    }

    @Post(uri = "/criacandidato", produces = MediaType.TEXT_PLAIN)
    String criaCandidato(int id, String nome, String email, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade) {
        criaPessoaFisica(nome, email, pais, estado, cep, descricao, competencias, CPF, idade)
        return "sucesso";
    }

    //TODO PROJETO criar update de candidato, empresa, vaga e competencia
    //TODO PROJETO criar delete de candidato, empresa, vaga e competencia
    //TODO PROJETO criar select especifico de candidato, empresa, vaga e competencia
    //TODO PROJETO criar create de vaga e competencia nessa parte
}
