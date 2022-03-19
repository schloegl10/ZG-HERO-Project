package Linketinder.controller

import Linketinder.utils.Competencia
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/vaga")
class LinketinderController {

    BDService BDService = new BDService()

    @Get(uri = "/listall", produces = MediaType.TEXT_PLAIN)
    String obtemVagas() {
        return BDService.obtemVagas().toString()
    }

    @Post(uri = "/cria", produces = MediaType.TEXT_PLAIN)
    String criaVaga(String descricao, String nome, String estado, String cidade, List<Competencia> competencias) {
        Vaga vaga = new Vaga(descricao, nome, estado, cidade, competencias)
        return BDService.criaVaga(vaga)
    }

    @Post(uri = "/atualiza", produces = MediaType.TEXT_PLAIN)
    String atualizaVaga(String descricaoOriginal, String nomeOriginal, String estadoOriginal, String cidadeOriginal, List<Competencia> competenciasOriginal, String descricao, String nome, String estado, String cidade, List<Competencia> competencias) {
        Vaga vaga = new Vaga(descricao, nome, estado, cidade, competencias)
        Vaga vagaOriginal = new Vaga(descricaoOriginal, nomeOriginal, estadoOriginal, cidadeOriginal, competenciasOriginal)
        return BDService.atualizaVaga(vaga, vagaOriginal).toString()
    }

    @Post(uri = "/delete", produces = MediaType.TEXT_PLAIN)
    String deleteVaga(String descricao, String nome, String estado, String cidade) {
        Vaga vaga = new Vaga(descricao, nome, estado, cidade, [])
        return BDService.deleteVaga(vaga).toString()
    }

    //TODO PROJETO criar select especifico
}
