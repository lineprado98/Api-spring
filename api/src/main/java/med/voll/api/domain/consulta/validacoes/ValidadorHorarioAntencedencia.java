package med.voll.api.domain.consulta.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidadorHorarioAntencedencia implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var dataAtual = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(dataAtual,dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30){
            throw new ValidacaoException("Consulta deve ser agendada com antenced^encia minima de 30 minutos!");
        }
    }
}
