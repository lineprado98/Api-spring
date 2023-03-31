package med.voll.api.paciente;

public record DadosListagemPaciente(String nome, String telefone){

    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getNome(),paciente.getTelefone());
    }

}
