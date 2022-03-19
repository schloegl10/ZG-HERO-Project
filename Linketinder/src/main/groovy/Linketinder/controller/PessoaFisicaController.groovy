package Linketinder.controller

import Linketinder.services.PessoaFisicaService
import Linketinder.utils.Competencia
import Linketinder.utils.PessoaFisica
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject

@Controller("/candidato")
class PessoaFisicaController {

    @Inject
    PessoaFisicaService PessoaFisicaService

    @Get(uri = "/listall", produces = MediaType.TEXT_PLAIN)
    String listaCandidatos() {
        return PessoaFisicaService.obtemPessoasFisicas().toString()
    }

    @Post(uri = "/cria", produces = MediaType.TEXT_PLAIN)
    String criaCandidato(String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade, String formacao) {
        PessoaFisica pessoaFisica = new PessoaFisica(nome, sobrenome, email, senha, pais, estado, cep, descricao, competencias, CPF, idade, formacao)
        return PessoaFisicaService.criaPessoaFisica(pessoaFisica).toString()
    }

    @Post(uri = "/atualiza", produces = MediaType.TEXT_PLAIN)
    String atualizaCandidato(String emailOriginal, String senhaOriginal, String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade, String formacao) {
        return PessoaFisicaService.atualizaPessoaFisica(emailOriginal, senhaOriginal, nome, sobrenome, email, senha, pais, estado, cep, descricao, competencias, CPF, idade, formacao).toString()
    }

    @Post(uri = "/delete", produces = MediaType.TEXT_PLAIN)
    String deleteCandidato(String emailOriginal, String senhaOriginal) {
        return PessoaFisicaService.deletePessoaFisica(emailOriginal, senhaOriginal).toString()
    }

    //TODO PROJETO criar select especifico
    //TODO PROJETO criar método que retorna sem dados pessoais
}
