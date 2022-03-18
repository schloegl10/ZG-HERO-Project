package Linketinder.controller

import Linketinder.utils.Competencia
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/")
class LinketinderController {

    BDService BDService = new BDService()

    @Get(uri = "/candidatos", produces = MediaType.TEXT_PLAIN)
    String listaCandidatos() {
       return BDService.obtemPessoasFisicas().toString()
    }

    @Get(uri = "/empresas", produces = MediaType.TEXT_PLAIN)
    String listaEmpresas() {
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

    @Post(uri = "/atualizaempresa", produces = MediaType.TEXT_PLAIN)
    String atualizaEmpresa(String emailOriginal, String senhaOriginal, String nome, String email, String pais, String estado, String cep, String descricao, List<Vagas> vaga, String CNPJ) {
        return BDService.atualizaPessoaJuridica(emailOriginal, senhaOriginal, nome, email, pais, estado, cep, descricao, vaga, CNPJ).toString()
    }

    @Post(uri = "/atualizacandidato", produces = MediaType.TEXT_PLAIN)
    String atualizaCandidato(String emailOriginal, String senhaOriginal, String nome, String email, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade) {
        return BDService.atualizaPessoaFisica(emailOriginal, senhaOriginal,nome, email, pais, estado, cep, descricao, competencias, CPF, idade).toString()
    }

    @Post(uri = "/atualizavaga", produces = MediaType.TEXT_PLAIN)
    String atualizaVaga(String descricaoOriginal, String nomeOriginal, String estadoOriginal, String cidadeOriginal, List<Competencia> competenciasOriginal, String descricao, String nome, String estado, String cidade, List<Competencia> competencias) {
        Vaga vaga = new Vaga(descricao, nome, estado, cidade, competencias)
        Vaga vagaOriginal = new Vaga(descricaoOriginal, nomeOriginal, estadoOriginal, cidadeOriginal, competenciasOriginal)
        return BDService.atualizaVaga(vaga, vagaOriginal).toString()
    }

    @Post(uri = "/atualizacompetencia", produces = MediaType.TEXT_PLAIN)
    String atualizaCompetencia(String descricaoOriginal, String nivelOriginal, String descricao, String nivel) {
        Competencia competencia = new Competencia(descricao, nivel)
        Competencia competenciaOriginal = new Competencia(descricaoOriginal, nivelOriginal)
        return BDService.atualizaCompetencia(competencia, competenciaOriginal).toString()
    }

    @Post(uri = "/deleteempresa", produces = MediaType.TEXT_PLAIN)
    String deleteEmpresa(String emailOriginal, String senhaOriginal) {
        return BDService.deletePessoaJuridica(emailOriginal, senhaOriginal).toString()
    }

    @Post(uri = "/deletecandidato", produces = MediaType.TEXT_PLAIN)
    String deleteCandidato(String emailOriginal, String senhaOriginal) {
        return BDService.deletePessoaFisica(emailOriginal, senhaOriginal).toString()
    }

    @Post(uri = "/deletevaga", produces = MediaType.TEXT_PLAIN)
    String deleteVaga(String descricao, String nome, String estado, String cidade) {
        Vaga vaga = new Vaga(descricao, nome, estado, cidade, [])
        return BDService.deleteVaga(vaga).toString()
    }

    @Post(uri = "/deletecompetencia", produces = MediaType.TEXT_PLAIN)
    String deleteCompetencia(String descricao, String nivel) {
        Competencia competencia = new Competencia(descricao, nivel)
        return BDService.deleteCompetencia(competencia).toString()
    }

    //TODO PROJETO criar select especifico de candidato, empresa, vaga e competencia
    //TODO PROJETO criar método que retorna sem dados pessoais
    //TODO PROJETO separar em mais controler divididos por pessoa fisica, juridica, competencia e vaga
}
