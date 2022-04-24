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
    String atualizaCompetencia(Long id, String descricao, String nivel) {
        return CompetenciaService.atualizaCompetencia(id, descricao, nivel)
    }

    @Post(uri = "/delete", produces = MediaType.TEXT_PLAIN)
    String deleteCompetencia(Long id) {
        return CompetenciaService.deleteCompetencia(id)
    }

    @Post(uri = "/busca", produces = MediaType.TEXT_PLAIN)
    String buscaCompetencia(Long id) {
        return CompetenciaService.buscaCompetencia(id)
    }
}
