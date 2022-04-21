package Linketinder.repository


import Linketinder.utils.PessoaFisica
import io.micronaut.context.annotation.Executable
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long> {
    @Executable
    PessoaFisica find(String nome)
}