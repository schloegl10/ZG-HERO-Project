package Linketinder.services

import Linketinder.repository.PessoaJuridicaRepository
import Linketinder.utils.Competencia
import Linketinder.utils.PessoaFisica
import Linketinder.utils.PessoaJuridica
import Linketinder.utils.Vaga
import jakarta.inject.Inject

class PessoaJuridicaService {

    @Inject PessoaJuridicaRepository pessoaJuridicaRepository

    List<PessoaJuridica> obtemPessoasJuridicas() {
        return pessoaJuridicaRepository.findAll() as List<PessoaJuridica>
    }

    boolean criaPessoaJuridica(PessoaJuridica pessoaJuridica) {
        return pessoaJuridicaRepository.save(pessoaJuridica)
    }

    boolean atualizaPessoaJuridica(String emailOriginal, String senhaOriginal, String nome, String email, String senha, String pais, String estado, String cep, String descricao, List<Vaga> vagas, String CNPJ) {
        PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findByEmailAndSenha(emailOriginal, senhaOriginal)
        nome ? pessoaJuridica.nome = nome : ''
        email ? pessoaJuridica.email = email : ''
        senha ? pessoaJuridica.senha = senha : ''
        pais ? pessoaJuridica.pais = pais : ''
        estado ? pessoaJuridica.estado = estado : ''
        cep ? pessoaJuridica.cep = cep : ''
        descricao ? pessoaJuridica.descricao = descricao : ''
        CNPJ ? pessoaJuridica.CNPJ = CNPJ : ''
        vagas ? pessoaJuridica.vagas = vagas : ''
        return pessoaJuridicaRepository.save(pessoaJuridica)
    }

    boolean deletePessoaJuridica(String emailOriginal, String senhaOriginal) {
        PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findByEmailAndSenha(emailOriginal, senhaOriginal)
        return pessoaJuridicaRepository.deleteById(pessoaJuridica.id)
    }

    List<PessoaJuridica> buscaPessoaJuridica(String nome, String email, String senha, String pais, String estado, String cep, String descricao, List<Vaga> vagas, String CNPJ) {
        List<PessoaJuridica> pessoasJuridica = pessoaJuridicaRepository.findAll() as List<PessoaJuridica>
        pessoasJuridica = pessoasJuridica.findAll {PessoaJuridica pessoaJuridica ->
            boolean nomeIgual = nome ? nome == pessoaJuridica.nome : true
            boolean emailIgual = email ? email == pessoaJuridica.email : true
            boolean senhaIgual = senha ? senha == pessoaJuridica.senha : true
            boolean paisIgual = pais ? pais == pessoaJuridica.pais : true
            boolean estadoIgual = estado ? estado == pessoaJuridica.estado : true
            boolean cepIgual = cep ? cep == pessoaJuridica.cep : true
            boolean descricaoIgual = descricao ? descricao == pessoaJuridica.descricao : true
            boolean vagasIgual = vagas ? vagas == pessoaJuridica.vagas : true
            boolean CNPJIgual = CNPJ ? CNPJ == pessoaJuridica.CNPJ : true
            return (nomeIgual && emailIgual && senhaIgual && paisIgual && estadoIgual && cepIgual && descricaoIgual && vagasIgual && CNPJIgual)
        }
        return pessoasJuridica
    }
}
