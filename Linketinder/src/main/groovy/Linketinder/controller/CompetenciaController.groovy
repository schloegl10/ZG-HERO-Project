package Linketinder.controller

import Linketinder.services.CompetenciaService
import Linketinder.utils.Competencia
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject

@Controller("/competencia")
class CompetenciaController {

    @Inject
    CompetenciaService CompetenciaService

    @Get(uri = "/listall", produces = MediaType.TEXT_PLAIN)
    String obtemCompetencias() {
        return CompetenciaService.obtemCompetencias().toString()
    }

    @Post(uri = "/cria", produces = MediaType.TEXT_PLAIN)
    String criaCompetencia(String descricao, String nivel) {
        Competencia competencia = new Competencia(descricao, nivel)
        return CompetenciaService.criaCompetencia(competencia)
    }

    @Post(uri = "/atualiza", produces = MediaType.TEXT_PLAIN)
    String atualizaCompetencia(String descricaoOriginal, String nivelOriginal, String descricao, String nivel) {
        Competencia competencia = new Competencia(descricao, nivel)
        Competencia competenciaOriginal = new Competencia(descricaoOriginal, nivelOriginal)
        return CompetenciaService.atualizaCompetencia(competencia, competenciaOriginal).toString()
    }

    @Post(uri = "/delete", produces = MediaType.TEXT_PLAIN)
    String deleteCompetencia(String descricao, String nivel) {
        Competencia competencia = new Competencia(descricao, nivel)
        return CompetenciaService.deleteCompetencia(competencia).toString()
    }

    //TODO PROJETO criar select especifico
}
