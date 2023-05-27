package med.voll.api.domain.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class AgendaDeConsultas {

    @Autowired
    private  ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
   private  List<ValidadorAgendamentoDeConsulta> validadores;


    public void agendar(DadosAgendamentoConsulta dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe");
        }

        if(dados.idMedico() !=null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe");
        }
        validadores.forEach(v -> v.validar(dados));
        var medico = escolherMedico(dados);
    
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());


       if(medico == null){
        throw new ValidacaoException("Não existe medico disponivel nessa data!");

       }

        var consulta =  new Consulta(null,medico,paciente,  dados.data());
    
        consultaRepository.save(consulta);
        
    }


    private Medico escolherMedico(DadosAgendamentoConsulta dados) {

        if(dados.idMedico() != null ){
            return  medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória, quando o medico não for selecionado!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

     
    }
    
}
