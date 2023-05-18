package med.voll.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.UsuarioRepository;



@Component
public class SecurityFilter extends OncePerRequestFilter  {

    @Autowired
    private TokenService tokenService ;

    @Autowired UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        var tokenJWT = recuperarToken(request);
        if(tokenJWT != null){   
            var subject =  tokenService.getSubject(tokenJWT);
            var usuario = repository.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);


        }
        filterChain.doFilter(request, response);
             
    }

    private String recuperarToken(HttpServletRequest request){

     var authorizationHeader = request.getHeader("Authorization");
     
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");

        }
        return null;
      

    }
}
