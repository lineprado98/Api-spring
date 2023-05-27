package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {



    public boolean existsByMedicoIdAndData(long idMedico, LocalDateTime data);

    public boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

}