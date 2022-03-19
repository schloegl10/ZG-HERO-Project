package Linketinder.controller

import Linketinder.services.PessoaJuridicaService
import Linketinder.utils.Competencia
import Linketinder.utils.Vaga
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/empresa")
class PessoaJuridicaController {

    PessoaJuridicaService PessoaJuridicaService = new PessoaJuridicaService()

    @Get(uri = "/listall", produces = MediaType.TEXT_PLAIN)
    String listaEmpresas() {
        return PessoaJuridicaService.obtemPessoasJuridica().toString()
    }

    @Post(uri = "/cria", produces = MediaType.TEXT_PLAIN)
    String criaEmpresa(String nome, String email, String pais, String estado, String cep, String descricao, List<Vaga> vaga, String CNPJ) {
        return PessoaJuridicaService.criaPessoaJuridica(nome, email, pais, estado, cep, descricao, vaga, CNPJ).toString()
    }

    @Post(uri = "/atualiza", produces = MediaType.TEXT_PLAIN)
    String atualizaEmpresa(String emailOriginal, String senhaOriginal, String nome, String email, String pais, String estado, String cep, String descricao, List<Vaga> vaga, String CNPJ) {
        return PessoaJuridicaService.atualizaPessoaJuridica(emailOriginal, senhaOriginal, nome, email, pais, estado, cep, descricao, vaga, CNPJ).toString()
    }

    @Post(uri = "/delete", produces = MediaType.TEXT_PLAIN)
    String deleteEmpresa(String emailOriginal, String senhaOriginal) {
        return PessoaJuridicaService.deletePessoaJuridica(emailOriginal, senhaOriginal).toString()
    }

    //TODO PROJETO criar select especifico
    //TODO PROJETO criar método que retorna sem dados pessoais
}
