package Linketinder.services

import Linketinder.repository.CurtidaRepository
import Linketinder.utils.Curtidas
import Linketinder.utils.PessoaFisica
import Linketinder.utils.PessoaJuridica
import jakarta.inject.Inject

class CurtidaService {

    @Inject
    CurtidaRepository curtidaRepository
    @Inject
    PessoaFisicaService pessoaFisicaService
    @Inject
    PessoaJuridicaService pessoaJuridicaService

    Curtidas obtemCurtida(Long idCandidato, Long idEmpresa) {
        PessoaFisica candidato = pessoaFisicaService.buscaId(idCandidato)
        PessoaJuridica empresa = pessoaJuridicaService.buscaId(idEmpresa)
        return curtidaRepository.findByCandidatoAndEmpresa(candidato, empresa) as Curtidas
    }

    Boolean criarCurtida(Long idCandidato, Long idEmpresa, Boolean empresaCurtiu, Boolean candidatoCurtiu) {
        PessoaFisica candidato = pessoaFisicaService.buscaId(idCandidato)
        PessoaJuridica empresa = pessoaJuridicaService.buscaId(idEmpresa)
        Curtidas curtidaExistente = curtidaRepository.findByCandidatoAndEmpresa(candidato, empresa) as Curtidas
        if(curtidaExistente) {
            if(empresaCurtiu) {
                curtidaExistente.pessoajuridicacurtiu = true
            } else {
                curtidaExistente.pessoafisicacurtiu = true
            }
            return curtidaRepository.save(curtidaExistente)
        }
        Curtidas curtida = new Curtidas(candidatoCurtiu, empresaCurtiu, candidato, empresa)
        return curtidaRepository.save(curtida)
    }

}
