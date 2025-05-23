package nutricion.hexagonal.infra.adaptadores.salida.security;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import nutricion.hexagonal.dominio.clases.Usuario;

// implementa la interfaz UserDetails y adapta usuario para spring sec (userDetails). 
// Pasa Usuario dominio a algo que spring sec entiende: UserDetails. Una instancia con permiso.
public class UserPrincipalAdapter implements UserDetails {
    private final Usuario usuario;

    public UserPrincipalAdapter(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
     
    }

    @Override
    public String getPassword() {  //da password encriptado, lo hace spring sec para comparar con rawPAssword que se pasa en login
        return usuario.getContraseña(); 
    }

    @Override
    public String getUsername() {
        return usuario.getCorreoElectronico();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // o lógica si manejas bloqueo de cuentas
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // o manejar expiración de contraseña
    }
}


