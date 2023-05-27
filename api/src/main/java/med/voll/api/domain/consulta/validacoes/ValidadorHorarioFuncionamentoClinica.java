package med.voll.api.domain.consulta.validacoes;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorHorarioFuncionamentoClinica  implements ValidadorAgendamentoDeConsulta{
    
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertura = dataConsulta.getHour() < 7 ;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

          if(domingo || antesDaAbertura|| depoisDoEncerramentoDaClinica){
            throw new ValidacaoException("Horario fora de funcionamento da clinica");

          }
    }
}
