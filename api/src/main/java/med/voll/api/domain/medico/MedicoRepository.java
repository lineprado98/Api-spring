package med.voll.api.domain.medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface  MedicoRepository extends JpaRepository<Medico,Long>{

    @Query("""
        select m from Medico m 
        where m.ativo = 1
        and
        m.especialidade =:especialidade
        and m.id not in(
            select c.medico.id from Consulta c 
            WHERE
           c.data = :data
        )
        order by rand()
        limit 1

        """)

    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);


    @Query("""
        select m.ativo from Medico m
        where
        m.id =:idMedico
        """)
    Boolean findAtivoById(Long idMedico);

}
