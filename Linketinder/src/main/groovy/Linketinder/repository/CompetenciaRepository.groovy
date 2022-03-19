package Linketinder.repository

import Linketinder.utils.Competencia
import io.micronaut.context.annotation.Executable
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface CompetenciaRepository extends CrudRepository<Competencia, Long> {
    @Executable
    Competencia find(String descricao)
}