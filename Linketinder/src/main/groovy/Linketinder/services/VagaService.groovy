package Linketinder.services

import Linketinder.repository.VagaRepository
import Linketinder.utils.Competencia
import Linketinder.utils.Vaga
import jakarta.inject.Inject

class VagaService {

    @Inject
    VagaRepository vagaRepository


    List<Vaga> obtemVagas() {
        return vagaRepository.findAll() as List<Vaga>
    }

    boolean criaVagas(List<Vaga> vagas) {
        return vagaRepository.saveAll(vagas)
    }

    boolean criaVaga(Vaga vaga) {
        return vagaRepository.save(vaga)
    }

    boolean atualizaVaga(Vaga vaga, Vaga vagaOriginal) {
        Vaga vagaOriginalBanco = vagaRepository.findByDescricaoAndNomeAndEstadoAndCidade(vagaOriginal.descricao, vagaOriginal.nome, vagaOriginal.estado, vagaOriginal.cidade)
        vagaOriginalBanco.descricao = vaga.descricao
        vagaOriginalBanco.nome = vaga.nome
        vagaOriginalBanco.estado = vaga.estado
        vagaOriginalBanco.cidade = vaga.cidade
        vagaOriginalBanco.competencias = vaga.competencias
        return vagaRepository.save(vagaOriginalBanco)
    }

    boolean deleteVaga(Vaga vaga) {
        Vaga vagaBanco = vagaRepository.findByDescricaoAndNomeAndEstadoAndCidade(vaga.descricao, vaga.nome, vaga.estado, vaga.cidade)
        return vagaRepository.deleteById(vagaBanco.id)
    }

    List<Vaga> buscaVaga(String descricao, String nome, String estado, String cidade, List<Competencia> competencias) {
        List<Vaga> vagas = vagaRepository.findAll() as List<Vaga>
        vagas = vagas.findAll { Vaga vaga ->
            boolean descricaoIgual = descricao ? descricao == vaga.descricao : true
            boolean nomeIgual = nome ? nome == vaga.nome : true
            boolean estadoIgual = estado ? estado == vaga.estado : true
            boolean cidadeIgual = cidade ? cidade == vaga.cidade : true
            boolean competenciasIgual = competencias ? competencias == vaga.competencias : true
            return (descricaoIgual && nomeIgual && estadoIgual && cidadeIgual && competenciasIgual)
        }
        return vagas
    }

    Vaga buscaVaga(Long id) {
        Vaga vagas = vagaRepository.findById(id)
        return vagas
    }
}
