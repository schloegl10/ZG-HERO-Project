package Linketinder.services

import Linketinder.repository.PessoaFisicaRepository
import Linketinder.utils.Competencia
import Linketinder.utils.PessoaFisica
import jakarta.inject.Inject

class PessoaFisicaService {

    @Inject
    PessoaFisicaRepository pessoaFisicaRepository

    List<PessoaFisica> obtemPessoasFisicas() {
        return pessoaFisicaRepository.findAll() as List<PessoaFisica>
    }

    boolean criaPessoaFisica(PessoaFisica pessoaFisica) {
        return pessoaFisicaRepository.save(pessoaFisica)
    }

    boolean atualizaPessoaFisica(String emailOriginal, String senhaOriginal, String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade, String formacao) {
        PessoaFisica pessoaFisica = pessoaFisicaRepository.findByEmailAndSenha(emailOriginal, senhaOriginal)
        nome ? pessoaFisica.nome = nome : ''
        sobrenome ? pessoaFisica.sobrenome = sobrenome : ''
        email ? pessoaFisica.email = email : ''
        senha ? pessoaFisica.senha = senha : ''
        pais ? pessoaFisica.pais = pais : ''
        estado ? pessoaFisica.estado = estado : ''
        cep ? pessoaFisica.cep = cep : ''
        descricao ? pessoaFisica.descricao = descricao : ''
        CPF ? pessoaFisica.CPF = CPF : ''
        competencias ? pessoaFisica.competencias = competencias : ''
        idade ? pessoaFisica.idade = idade : ''
        formacao ? pessoaFisica.formacao = formacao : ''
        return pessoaFisicaRepository.save(pessoaFisica)
    }

    boolean deletePessoaFisica(String emailOriginal, String senhaOriginal) {
        PessoaFisica pessoaFisica = pessoaFisicaRepository.findByEmailAndSenha(emailOriginal, senhaOriginal)
        return pessoaFisicaRepository.deleteById(pessoaFisica.id)
    }

    List<PessoaFisica> buscaPessoaFisica(String nome, String sobrenome, String email, String senha, String pais, String estado, String cep, String descricao, List<Competencia> competencias, String CPF, int idade, String formacao) {
        List<PessoaFisica> pessoasFisica = pessoaFisicaRepository.findAll() as List<PessoaFisica>
        pessoasFisica = pessoasFisica.findAll { PessoaFisica pessoaFisica ->
            boolean nomeIgual = nome ? nome == pessoaFisica.nome : true
            boolean sobrenomeIgual = sobrenome ? sobrenome == pessoaFisica.sobrenome : true
            boolean emailIgual = email ? email == pessoaFisica.email : true
            boolean senhaIgual = senha ? senha == pessoaFisica.senha : true
            boolean paisIgual = pais ? pais == pessoaFisica.pais : true
            boolean estadoIgual = estado ? estado == pessoaFisica.estado : true
            boolean cepIgual = cep ? cep == pessoaFisica.cep : true
            boolean descricaoIgual = descricao ? descricao == pessoaFisica.descricao : true
            boolean competenciasIgual = competencias ? competencias == pessoaFisica.competencias : true
            boolean CPFIgual = CPF ? CPF == pessoaFisica.CPF : true
            boolean idadeIgual = idade ? idade == pessoaFisica.idade : true
            boolean formacaoIgual = formacao ? formacao == pessoaFisica.formacao : true
            return (nomeIgual && sobrenomeIgual && emailIgual && senhaIgual && paisIgual && estadoIgual && cepIgual && descricaoIgual && competenciasIgual && CPFIgual && idadeIgual && formacaoIgual)
        }
        return pessoasFisica
    }
}
