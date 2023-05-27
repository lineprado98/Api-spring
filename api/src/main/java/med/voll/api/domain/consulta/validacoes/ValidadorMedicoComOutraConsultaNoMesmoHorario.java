package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario  implements ValidadorAgendamentoDeConsulta {
    
    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        if(dados.idMedico() == null){
            return ;
         }
    
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(),dados.data());
      
            if(medicoPossuiOutraConsultaNoMesmoHorario){
                throw new ValidacaoException("Medico indisponivel no horario solicitado!");
            }

    }
    
}
