package med.voll.api.domain.consulta.validacoes;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;

@Component
public class ValidadorMedicoAtivo  implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private  MedicoRepository repository; 

    public void validar(DadosAgendamentoConsulta dados){

         if(dados.idMedico() == null){
            return ;
         }

         var medicoEstadoAtivo = repository.findAtivoById(dados.idMedico());


         if(!medicoEstadoAtivo){
            throw new ValidacaoException("Consulta nao pode ser agendada com medico inativo");

         }

    }
    
}
