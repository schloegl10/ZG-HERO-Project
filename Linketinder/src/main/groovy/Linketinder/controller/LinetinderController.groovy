package Linketinder.controller

import Linketinder.utils.Competencia
import Linketinder.utils.PessoaFisica
import Linketinder.utils.PessoaJuridica
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/")
class LinetinderController {
    List<PessoaFisica> candidatos
    List<PessoaJuridica> empresas

    @Get(uri = "/candidatos", produces = MediaType.TEXT_PLAIN)
    String listaCandidatos() {
        if(!candidatos || !empresas) {
            inicializa()
        }
        return candidatos.toString()
    }

    @Get(uri = "/empresas", produces = MediaType.TEXT_PLAIN)
    String listaEmpresas() {
        if(!candidatos || !empresas) {
            inicializa()
        }
        return empresas.toString()
    }

    void inicializa() {
        candidatos = []
        empresas = []
        Competencia competencia = new Competencia("Groovy", "Avancado")
        Competencia competencia2 = new Competencia("Java", "Avancado")
        Competencia competencia3 = new Competencia("Postgres", "Avancado")
        PessoaFisica candidato1 = new PessoaFisica("Carlos1", "abc1@hotmail.com", "China", "China", "74000001", "Sou um morador da china querendo trabalhar no Brasil", [competencia], "12345678912", 21)
        PessoaFisica candidato2 = new PessoaFisica("Carlos2", "abc2@hotmail.com", "China", "China", "74000002", "Sou um morador da china querendo trabalhar no Brasil", [competencia, competencia2], "12345678912", 22)
        PessoaFisica candidato3 = new PessoaFisica("Carlos3", "abc3@hotmail.com", "China", "China", "74000003", "Sou um morador da china querendo trabalhar no Brasil", [competencia, competencia3], "12345678912", 23)
        PessoaFisica candidato4 = new PessoaFisica("Carlos4", "abc4@hotmail.com", "China", "China", "74000004", "Sou um morador da china querendo trabalhar no Brasil", [competencia2], "12345678912", 24)
        PessoaFisica candidato5 = new PessoaFisica("Carlos5", "abc5@hotmail.com", "China", "China", "74000005", "Sou um morador da china querendo trabalhar no Brasil", [competencia3], "12345678912", 25)
        candidatos.add(candidato1)
        candidatos.add(candidato2)
        candidatos.add(candidato3)
        candidatos.add(candidato4)
        candidatos.add(candidato5)

        PessoaJuridica empresa1 = new PessoaJuridica("AppleBr", "AppleBr@hotmail.com", "Brasil", "GO", "74000001", "Uma otima empresa para se trabalhar", [competencia], "12345678912")
        PessoaJuridica empresa2 = new PessoaJuridica("GoogleBr", "GoogleBr@hotmail.com", "Brasil", "GO", "74000002", "Uma otima empresa para se trabalhar", [competencia, competencia2], "12345678912")
        PessoaJuridica empresa3 = new PessoaJuridica("MicrosoftBr", "MicrosoftBr@hotmail.com", "Brasil", "GO", "74000003", "Uma otima empresa para se trabalhar", [competencia, competencia3], "12345678912")
        PessoaJuridica empresa4 = new PessoaJuridica("LinkedinBr", "LinkedinBr@hotmail.com", "Brasil", "GO", "74000004", "Uma otima empresa para se trabalhar", [competencia2], "12345678912")
        PessoaJuridica empresa5 = new PessoaJuridica("TinderBr", "TinderBr@hotmail.com", "Brasil", "GO", "74000005", "Uma otima empresa para se trabalhar", [competencia3], "12345678912")
        empresas.add(empresa1)
        empresas.add(empresa2)
        empresas.add(empresa3)
        empresas.add(empresa4)
        empresas.add(empresa5)


    }

}
