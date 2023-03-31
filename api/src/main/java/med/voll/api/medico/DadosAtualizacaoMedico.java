package med.voll.api.medico;

import io.micrometer.common.lang.NonNull;

public record DadosAtualizacaoMedico(@NonNull Long id, String nome, String telefone) {

}
