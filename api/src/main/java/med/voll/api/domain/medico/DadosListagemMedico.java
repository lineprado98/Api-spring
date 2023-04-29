package med.voll.api.domain.medico;


public record DadosListagemMedico(Long id, String nome, String crm, String email,Especialidade especialidade){
    
    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEmail(), medico.getEspecialidade());
    }
    
}
