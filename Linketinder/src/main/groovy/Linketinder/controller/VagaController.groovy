package Linketinder.controller

import Linketinder.services.VagaService
import Linketinder.utils.Competencia
import Linketinder.utils.Vaga
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/vaga")
class VagaController {

    VagaService VagaService = new VagaService()

    @Get(uri = "/listall", produces = MediaType.TEXT_PLAIN)
    String obtemVagas() {
        return VagaService.obtemVagas().toString()
    }

    @Post(uri = "/cria", produces = MediaType.TEXT_PLAIN)
    String criaVaga(String descricao, String nome, String estado, String cidade, List<Competencia> competencias) {
        Vaga vaga = new Vaga(descricao, nome, estado, cidade, competencias)
        return VagaService.criaVaga(vaga)
    }

    @Post(uri = "/atualiza", produces = MediaType.TEXT_PLAIN)
    String atualizaVaga(String descricaoOriginal, String nomeOriginal, String estadoOriginal, String cidadeOriginal, List<Competencia> competenciasOriginal, String descricao, String nome, String estado, String cidade, List<Competencia> competencias) {
        Vaga vaga = new Vaga(descricao, nome, estado, cidade, competencias)
        Vaga vagaOriginal = new Vaga(descricaoOriginal, nomeOriginal, estadoOriginal, cidadeOriginal, competenciasOriginal)
        return VagaService.atualizaVaga(vaga, vagaOriginal).toString()
    }

    @Post(uri = "/delete", produces = MediaType.TEXT_PLAIN)
    String deleteVaga(String descricao, String nome, String estado, String cidade) {
        Vaga vaga = new Vaga(descricao, nome, estado, cidade, [])
        return VagaService.deleteVaga(vaga).toString()
    }

    //TODO PROJETO criar select especifico
}
