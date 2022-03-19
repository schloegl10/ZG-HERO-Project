package Linketinder.repository


import Linketinder.utils.PessoaJuridica
import io.micronaut.context.annotation.Executable
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface PessoaJuridicaRepository extends CrudRepository<PessoaJuridica, Long> {
    @Executable
    PessoaJuridica find(String nome)
}