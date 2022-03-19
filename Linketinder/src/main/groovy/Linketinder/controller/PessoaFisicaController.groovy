package Linketinder.controller

import Linketinder.services.PessoaFisicaService
import Linketinder.utils.Competencia
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/candidato")
class PessoaFisicaController {

    PessoaFisicaService PessoaFisicaService = new PessoaFisicaService()

    @Get(uri = "/listall", produces = MediaType.TEXT_PLAIN)
    String listaCandidatos() {
        return PessoaFisicaService.obtemPessoasFisicas().toString()
    }

    @Post(uri = "/cria", produces = MediaType.TEXT_PLAIN)
    String criaCandidato(String nome, String email, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade) {
        return PessoaFisicaService.criaPessoaFisica(nome, email, pais, estado, cep, descricao, competencias, CPF, idade).toString()
    }

    @Post(uri = "/atualiza", produces = MediaType.TEXT_PLAIN)
    String atualizaCandidato(String emailOriginal, String senhaOriginal, String nome, String email, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade) {
        return PessoaFisicaService.atualizaPessoaFisica(emailOriginal, senhaOriginal, nome, email, pais, estado, cep, descricao, competencias, CPF, idade).toString()
    }

    @Post(uri = "/delete", produces = MediaType.TEXT_PLAIN)
    String deleteCandidato(String emailOriginal, String senhaOriginal) {
        return PessoaFisicaService.deletePessoaFisica(emailOriginal, senhaOriginal).toString()
    }

    //TODO PROJETO criar select especifico
    //TODO PROJETO criar método que retorna sem dados pessoais
}
