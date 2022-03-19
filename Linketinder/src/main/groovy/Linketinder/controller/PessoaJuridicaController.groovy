package Linketinder.controller

import Linketinder.utils.Competencia
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post

@Controller("/empresa")
class PessoaJuridicaController {

    BDService BDService = new BDService()

    @Get(uri = "/listall", produces = MediaType.TEXT_PLAIN)
    String listaEmpresas() {
        return BDService.obtemPessoasJuridica().toString()
    }

    @Post(uri = "/cria", produces = MediaType.TEXT_PLAIN)
    String criaEmpresa(String nome, String email, String pais, String estado, String cep, String descricao, List<Vagas> vaga, String CNPJ) {
        return BDService.criaPessoaJuridica(nome, email, pais, estado, cep, descricao, vaga, CNPJ).toString()
    }

    @Post(uri = "/atualiza", produces = MediaType.TEXT_PLAIN)
    String atualizaEmpresa(String emailOriginal, String senhaOriginal, String nome, String email, String pais, String estado, String cep, String descricao, List<Vagas> vaga, String CNPJ) {
        return BDService.atualizaPessoaJuridica(emailOriginal, senhaOriginal, nome, email, pais, estado, cep, descricao, vaga, CNPJ).toString()
    }

    @Post(uri = "/delete", produces = MediaType.TEXT_PLAIN)
    String deleteEmpresa(String emailOriginal, String senhaOriginal) {
        return BDService.deletePessoaJuridica(emailOriginal, senhaOriginal).toString()
    }

    //TODO PROJETO criar select especifico
    //TODO PROJETO criar método que retorna sem dados pessoais
}
