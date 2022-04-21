package Linketinder.controller

import Linketinder.services.CurtidaService
import Linketinder.services.PessoaJuridicaService
import Linketinder.services.VagaService
import Linketinder.utils.Curtidas
import Linketinder.utils.PessoaJuridica
import Linketinder.utils.Vaga
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject

@Controller("/empresa")
class PessoaJuridicaController {

    @Inject
    PessoaJuridicaService PessoaJuridicaService
    @Inject
    VagaService vagaService
    @Inject
    CurtidaService curtidaService

    @Get(uri = "/listall", produces = MediaType.TEXT_PLAIN)
    String listaEmpresas() {
        return PessoaJuridicaService.obtemPessoasJuridicas().toString()
    }

    @Post(uri = "/cria", produces = MediaType.TEXT_PLAIN)
    String criaEmpresa(String nome, String email, String senha, String pais, String estado, String cep, String descricao, List<Long> idVagas, String CNPJ) {
        List<Vaga> vaga = []
        for (Long id in idVagas) {
            vaga.add(vagaService.buscaVaga(id))
        }
        PessoaJuridica pessoaJuridica = new PessoaJuridica(nome, email, senha, pais, estado, cep, descricao, vaga, CNPJ)
        return PessoaJuridicaService.criaPessoaJuridica(pessoaJuridica).toString()
    }

    @Post(uri = "/atualiza", produces = MediaType.TEXT_PLAIN)
    String atualizaEmpresa(String emailOriginal, String senhaOriginal, String nome, String email, String senha, String pais, String estado, String cep, String descricao, List<Long> idVagas, String CNPJ) {
        List<Vaga> vaga = []
        for (Long id in idVagas) {
            vaga.add(vagaService.buscaVaga(id))
        }
        return PessoaJuridicaService.atualizaPessoaJuridica(emailOriginal, senhaOriginal, nome, senha, email, pais, estado, cep, descricao, vaga, CNPJ).toString()
    }

    @Post(uri = "/delete", produces = MediaType.TEXT_PLAIN)
    String deleteEmpresa(String emailOriginal, String senhaOriginal) {
        return PessoaJuridicaService.deletePessoaJuridica(emailOriginal, senhaOriginal).toString()
    }

    @Post(uri = "/busca", produces = MediaType.TEXT_PLAIN)
    String buscaPessoaJuridica(String nome, String email, String senha, String pais, String estado, String cep, String descricao, List<Vaga> vaga, String CNPJ) {
        return PessoaJuridicaService.buscaPessoaJuridica(nome, email, senha, pais, estado, cep, descricao, vaga, CNPJ)
    }

    @Post(uri = "/curtir", produces = MediaType.TEXT_PLAIN)
    String curtiCandidato(Long candidatoId, Long empresaId) {
        return curtidaService.criarCurtida(candidatoId, empresaId, true, false)
    }

    @Post(uri = "/obtemdadosempresa", produces = MediaType.TEXT_PLAIN)
    String obtemDadosEmpresa(Long idCandidato, Long idEmpresa) {
        Curtidas curtida = curtidaService.obtemCurtida(idCandidato, idEmpresa)
        if(curtida) {
            return pessoaJuridicaService.buscaId(idCandidato)
        } else {
            return pessoaJuridicaService.buscaIdDadosPublicos(idCandidato)
        }
    }
}
