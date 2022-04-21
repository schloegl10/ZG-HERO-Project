package Linketinder.controller

import Linketinder.services.CompetenciaService
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
    PessoaFisicaService pessoaFisicaService
    @Inject
    CompetenciaService competenciaService

    @Get(uri = "/listall", produces = MediaType.TEXT_PLAIN)
    String listaCandidatos() {
        return pessoaFisicaService.obtemPessoasFisicas().toString()
    }

    @Post(uri = "/cria", produces = MediaType.TEXT_PLAIN)
    String criaCandidato(String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Long> idsCompetencias, String CPF, int idade, String formacao) {
        List<Competencia> competencias = []
        for (Long id in idsCompetencias) {
            competencias.add(competenciaService.buscaCompetencia(id))
        }
        PessoaFisica pessoaFisica = new PessoaFisica(nome, sobrenome, email, senha, pais, estado, cep, descricao, competencias, CPF, idade, formacao)
        return pessoaFisicaService.criaPessoaFisica(pessoaFisica).toString()
    }

    @Post(uri = "/atualiza", produces = MediaType.TEXT_PLAIN)
    String atualizaCandidato(String emailOriginal, String senhaOriginal, String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Long> idsCompetencias, String CPF, int idade, String formacao) {
        List<Competencia> competencias = []
        for (Long id in idsCompetencias) {
            competencias.add(competenciaService.buscaCompetencia(id))
        }
        return pessoaFisicaService.atualizaPessoaFisica(emailOriginal, senhaOriginal, nome, sobrenome, email, senha, pais, estado, cep, descricao, competencias, CPF, idade, formacao).toString()
    }

    @Post(uri = "/delete", produces = MediaType.TEXT_PLAIN)
    String deleteCandidato(String emailOriginal, String senhaOriginal) {
        return pessoaFisicaService.deletePessoaFisica(emailOriginal, senhaOriginal).toString()
    }

    @Post(uri = "/busca", produces = MediaType.TEXT_PLAIN)
    String buscaPessoaFisica(String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade, String formacao) {
        return pessoaFisicaService.buscaPessoaFisica(nome, sobrenome, email, senha, pais, estado, cep, descricao, competencias, CPF, idade, formacao)
    }
}
