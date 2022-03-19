package Linketinder.controller

import Linketinder.utils.Competencia
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/competencia")
class CompetenciaController {

    BDService BDService = new BDService()

    @Get(uri = "/listall", produces = MediaType.TEXT_PLAIN)
    String obtemCompetencias() {
        return BDService.obtemCompetencias().toString()
    }

    @Post(uri = "/cria", produces = MediaType.TEXT_PLAIN)
    String criaCompetencia(String descricao, String nivel) {
        Competencia competencia = new Competencia(descricao, nivel)
        return BDService.criaCompetencia(competencia)
    }

    @Post(uri = "/atualiza", produces = MediaType.TEXT_PLAIN)
    String atualizaCompetencia(String descricaoOriginal, String nivelOriginal, String descricao, String nivel) {
        Competencia competencia = new Competencia(descricao, nivel)
        Competencia competenciaOriginal = new Competencia(descricaoOriginal, nivelOriginal)
        return BDService.atualizaCompetencia(competencia, competenciaOriginal).toString()
    }

    @Post(uri = "/delete", produces = MediaType.TEXT_PLAIN)
    String deleteCompetencia(String descricao, String nivel) {
        Competencia competencia = new Competencia(descricao, nivel)
        return BDService.deleteCompetencia(competencia).toString()
    }

    //TODO PROJETO criar select especifico
}
