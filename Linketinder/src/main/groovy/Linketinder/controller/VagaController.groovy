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
    String criaVaga(String descricao, String nome, String estado, String cidade, List<Competencia> competencias) {
        Vaga vaga = new Vaga(descricao, nome, estado, cidade, competencias)
        return vagaService.criaVaga(vaga)
    }

    @Post(uri = "/atualiza", produces = MediaType.TEXT_PLAIN)
    String atualizaVaga(String descricaoOriginal, String nomeOriginal, String estadoOriginal, String cidadeOriginal, List<Competencia> competenciasOriginal, String descricao, String nome, String estado, String cidade, List<Competencia> competencias) {
        Vaga vaga = new Vaga(descricao, nome, estado, cidade, competencias)
        Vaga vagaOriginal = new Vaga(descricaoOriginal, nomeOriginal, estadoOriginal, cidadeOriginal, competenciasOriginal)
        return vagaService.atualizaVaga(vaga, vagaOriginal).toString()
    }

    @Post(uri = "/delete", produces = MediaType.TEXT_PLAIN)
    String deleteVaga(String descricao, String nome, String estado, String cidade) {
        Vaga vaga = new Vaga(descricao, nome, estado, cidade, [])
        return vagaService.deleteVaga(vaga).toString()
    }

    @Post(uri = "/busca", produces = MediaType.TEXT_PLAIN)
    String buscaCompetencia(String descricao, String nome, String estado, String cidade, List<Competencia> competencias) {
        return vagaService.buscaVaga(descricao, nome, estado, cidade, competencias)
    }
}
