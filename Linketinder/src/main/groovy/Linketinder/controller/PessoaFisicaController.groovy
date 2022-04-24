package Linketinder.controller

import Linketinder.services.CompetenciaService
import Linketinder.services.CurtidaService
import Linketinder.services.PessoaFisicaService
import Linketinder.utils.Competencia
import Linketinder.utils.Curtidas
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
    @Inject
    CurtidaService curtidaService

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
    String atualizaCandidato(Long idPessoa, String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Long> idsCompetencias, String CPF, int idade, String formacao) {
        List<Competencia> competencias = []
        for (Long id in idsCompetencias) {
            competencias.add(competenciaService.buscaCompetencia(id))
        }
        return pessoaFisicaService.atualizaPessoaFisica(idPessoa, nome, sobrenome, email, senha, pais, estado, cep, descricao, competencias, CPF, idade, formacao).toString()
    }

    @Post(uri = "/delete", produces = MediaType.TEXT_PLAIN)
    String deleteCandidato(Long id) {
        return pessoaFisicaService.deletePessoaFisica(id)
    }

    @Post(uri = "/busca", produces = MediaType.TEXT_PLAIN)
    String buscaPessoaFisica(String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade, String formacao) {
        return pessoaFisicaService.buscaPessoaFisica(nome, sobrenome, email, senha, pais, estado, cep, descricao, competencias, CPF, idade, formacao)
    }

    @Post(uri = "/curtir", produces = MediaType.TEXT_PLAIN)
    String curtiEmpresa(Long candidatoId, Long empresaId) {
        return curtidaService.criarCurtida(candidatoId, empresaId, false, true)
    }

    @Post(uri = "/obtemdadoscandidato", produces = MediaType.TEXT_PLAIN)
    String obtemDadosCandidato(Long idCandidato, Long idEmpresa) {
        Curtidas curtida = curtidaService.obtemCurtida(idCandidato, idEmpresa)
        if(curtida) {
            return pessoaFisicaService.buscaId(idCandidato)
        } else {
            return pessoaFisicaService.buscaIdDadosPublicos(idCandidato)
        }
    }
}
