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
        return findByCandidatoAndEmpresa(candidato, empresa)
    }

    Boolean criarCurtida(Long idCandidato, Long idEmpresa, Boolean empresaCurtiu, Boolean candidatoCurtiu) {
        PessoaFisica candidato = pessoaFisicaService.buscaId(idCandidato)
        PessoaJuridica empresa = pessoaJuridicaService.buscaId(idEmpresa)
        Curtidas curtidaExistente = findByCandidatoAndEmpresa(candidato, empresa)
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

    Curtidas findByCandidatoAndEmpresa(PessoaFisica candidato, PessoaJuridica empresa) {
        List<Curtidas> curtidas = curtidaRepository.findAll() as List<Curtidas>
        Curtidas curtidaCorrta = curtidas.findAll {Curtidas curtida ->
            curtida.empresa.id == empresa.id && curtida.candidato.id == candidato.id
        }.first()
        return curtidaCorrta
    }

}
