package Linketinder.controller

import Linketinder.services.VagaService
import Linketinder.utils.Competencia
import Linketinder.utils.Vaga
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject

@Controller("/vaga")
class VagaController {

    @Inject
    VagaService vagaService

    @Get(uri = "/listall", produces = MediaType.TEXT_PLAIN)
    String obtemVagas() {
        return vagaService.obtemVagas().toString()
    }

    @Post(uri = "/cria", produces = MediaType.TEXT_PLAIN)
    String criaVaga(String descricao, String nome, String estado, String cidade, List<Long> idsCompetencias, Long empresaId) {
        return vagaService.criaVaga(descricao, nome, estado, cidade, idsCompetencias, empresaId)
    }

    @Post(uri = "/atualiza", produces = MediaType.TEXT_PLAIN)
    String atualizaVaga(Long idVaga, String descricao, String nome, String estado, String cidade, List<Long> idsCompetencias) {
        return vagaService.atualizaVaga(idVaga, descricao, nome, estado, cidade, idsCompetencias).toString()
    }

    @Post(uri = "/delete", produces = MediaType.TEXT_PLAIN)
    String deleteVaga(Long vagaId) {
        try {
            vagaService.deleteVaga(vagaId).toString()
            return true
        } catch (Exception e) {
            return false
        }
    }

    @Post(uri = "/busca", produces = MediaType.TEXT_PLAIN)
    String buscaVaga(Long id) {
        return vagaService.buscaVaga(id)
    }
}
