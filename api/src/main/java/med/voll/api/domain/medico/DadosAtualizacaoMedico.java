package med.voll.api.domain.medico;

import io.micrometer.common.lang.NonNull;

public record DadosAtualizacaoMedico(@NonNull Long id, String nome, String telefone) {

}
