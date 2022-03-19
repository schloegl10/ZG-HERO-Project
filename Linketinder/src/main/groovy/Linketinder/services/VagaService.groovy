package Linketinder.services

import Linketinder.repository.VagaRepository
import Linketinder.utils.Competencia
import Linketinder.utils.SqlUtils
import Linketinder.utils.Vaga
import groovy.sql.GroovyRowResult
import jakarta.inject.Inject

class VagaService {
//TODO PROJECT implementar repository
    @Inject VagaRepository vagaRepository


    List<Vaga> obtemVagas() {
        return vagaRepository.findAll()
    }

    boolean criaVagas(List<Vaga> vagas) {
        return vagaRepository.saveAll(vagas)
    }

    boolean criaVaga(Vaga vaga) {
        return vagaRepository.save(vaga)
    }

    //TODO PROJETO Rever isso
    boolean criaRelacoesVagaCompetencia(Vaga vagas) {
        List<Boolean> resultados = []
        for(Competencia competencia in vagas.competencias) {
            resultados.add(criaRelacaoVagaCompetencia(vaga, competencia))
        }
        if(resultados.find(false)) {
            return false
        }
        return true
    }

    boolean criaRelacaoVagaCompetencia(Vaga vaga, Competencia competencia) {
        String selectVaga = SqlUtils.SELECT_VAGA + " where nome = '${vaga.nome}' and descricao = '${vaga.descricao}' and estado = '${vaga.estado}' and cidade = '${vaga.cidade}'"
        String selectCompetencia = SqlUtils.SELECT_COMPETENCIA + " where descricao = '${competencia.descricao}' AND nivel = '${competencia.nivel}'"
        List<GroovyRowResult> competenciaGRR = sql.rows(selectCompetencia)
        List<GroovyRowResult> vagaGRR = sql.rows(selectVaga)
        String insert = SqlUtils.INSERT_RELACAO_COMPETENCIA_VAGA + "(${competenciaGRR.id}, ${vagaGRR.id})"
        return sql.execute(insert)
    }

    //TODO PROJETO criar update das relações com competência
    boolean atualizaVaga(Vaga vaga, Vaga vagaOriginal) {
        Vaga vagaOriginalBanco = vagaRepository.findByDescricaoAndNomeAndEstadoAndCidade(vagaOriginal.descricao, vagaOriginal.nome, vagaOriginal.estado, vagaOriginal.cidade)
        vagaOriginalBanco.descricao = vaga.descricao
        vagaOriginalBanco.nome = vaga.nome
        vagaOriginalBanco.estado = vaga.estado
        vagaOriginalBanco.cidade = vaga.cidade
        return vagaRepository.save(vagaOriginalBanco)
    }

    boolean deleteVaga(Vaga vaga) {
        Vaga vagaBanco = vagaRepository.findByDescricaoAndNomeAndEstadoAndCidade(vaga.descricao, vaga.nome, vaga.estado, vaga.cidade)
        return vagaRepository.deleteById(vagaBanco.id)
    }
}
