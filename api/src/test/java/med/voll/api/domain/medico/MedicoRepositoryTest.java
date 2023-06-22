package med.voll.api.domain.medico;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.Paciente;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Deve devolver null quanod o unico medico cadastrado nao está disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1(){
        var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY) ).atTime(10,0);

        var medico = cadastrarMedico("Marcelo","marcelo@gmail.com","99999l",Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Medico" ,"Mecio'@gmail", "0000");
        cadastrarConsulta(medico,paciente,proximaSegundaAs10);
        System.out.println("teste");
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

       assertThat(medicoLivre).isNull();

    }
    private Paciente cadastrarPaciente(String nome,String email, String cpf){
            var paciente = new Paciente(dadosPaciente(nome,cpf));
            em.persist(paciente);
            return paciente;
        
    }
    
    private Medico cadastrarMedico(String nome,String email, String crm, Especialidade especialidade){
            var medico = new Medico(dadosMedico(nome, email ,crm,especialidade));
            em.persist(medico);
            return medico;
        
    }
    
    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade){
        return new DadosCadastroMedico(
                    nome,
                    email,"0000",
                    crm,especialidade,
                    dadosEndereco()
            );
        }

    private DadosCadastroPaciente dadosPaciente(String nome,String cpf){
            return new DadosCadastroPaciente(nome, cpf);

        }

    private DadosEndereco dadosEndereco(){
        return new DadosEndereco(
                   "rus teste",
                   "bairro",
                   "000",
                   "Brasilia",
                   "DF",
                   null,
                   null
            );
        }
    
    private void cadastrarConsulta(Medico medico,Paciente paciente,LocalDateTime data){
        em.persist(new Consulta(null,medico,paciente,data));
    }
}
