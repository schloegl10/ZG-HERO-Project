package Linketinder.repository

import Linketinder.utils.Curtidas
import io.micronaut.context.annotation.Executable
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface CurtidaRepository extends CrudRepository<Curtidas, Long> {
    @Executable
    Curtidas find(Long id)
}