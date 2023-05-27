package med.voll.api.domain.paciente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="pacientes")
@Entity(name="Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Paciente {

   @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
   private Long id ;
   private String nome ;
   private String telefone;
   private Boolean ativo;

   public Paciente(DadosCadastroPaciente dados){
      this.ativo = true;
      this.nome = dados.nome();
      this.telefone= dados.telefone();
   }

}
