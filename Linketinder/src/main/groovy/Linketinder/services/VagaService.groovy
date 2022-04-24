package Linketinder.services

import Linketinder.repository.VagaRepository
import Linketinder.utils.Competencia
import Linketinder.utils.PessoaJuridica
import Linketinder.utils.Vaga
import jakarta.inject.Inject

class VagaService {

    @Inject
    VagaRepository vagaRepository
    @Inject
    PessoaJuridicaService pessoaJuridicaService
    @Inject
    CompetenciaService competenciaService

    List<Vaga> obtemVagas() {
        return vagaRepository.findAll() as List<Vaga>
    }

    boolean criaVagas(List<Vaga> vagas) {
        return vagaRepository.saveAll(vagas)
    }

    boolean criaVaga(String descricao, String nome, String estado, String cidade, List<Long> idsCompetencias, Long empresaId) {
        List<Competencia> competencias = []
        for(Long id in idsCompetencias) {
            competencias.add(competenciaService.buscaCompetencia(id))
        }
        PessoaJuridica empresa = pessoaJuridicaService.buscaId(empresaId)
        Vaga vaga = new Vaga(descricao, nome, estado, cidade, competencias, empresa)
        return vagaRepository.save(vaga)
    }

    boolean atualizaVaga(Long idVaga, String descricao, String nome, String estado, String cidade, List<Long> idsCompetencias) {
        List<Competencia> competencias = []
        for(Long id in idsCompetencias) {
            competencias.add(competenciaService.buscaCompetencia(id))
        }
        Vaga vagaOriginalBanco = vagaRepository.findById(idVaga).get()
        vagaOriginalBanco.descricao = descricao
        vagaOriginalBanco.nome = nome
        vagaOriginalBanco.estado = estado
        vagaOriginalBanco.cidade = cidade
        vagaOriginalBanco.competencias = competencias
        return vagaRepository.save(vagaOriginalBanco)
    }

    boolean deleteVaga(Long vagaId) {
        return vagaRepository.deleteById(vagaId)
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
        Vaga vagas = vagaRepository.findById(id).get()
        return vagas
    }
}
