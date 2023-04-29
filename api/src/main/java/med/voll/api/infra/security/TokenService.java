package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.voll.api.domain.usuario.Usuario;

@Service
public class TokenService {

    public String gerarToken(Usuario usuario){
        try{
            var algoritimo = Algorithm.HMAC256("12345678");
            return JWT.create()
            .withIssuer("API Voll.med")
            .withSubject(usuario.getLogin())
            .withExpiresAt(dataExpiracao())
            .sign(algoritimo);

        }catch(JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT");

        }
    }

    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
    
}
