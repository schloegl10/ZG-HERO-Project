package Linketinder.repository

import Linketinder.utils.Vaga
import io.micronaut.context.annotation.Executable
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface VagaRepository extends CrudRepository<Vaga, Long> {
    @Executable
    Vaga find(String nome)
}