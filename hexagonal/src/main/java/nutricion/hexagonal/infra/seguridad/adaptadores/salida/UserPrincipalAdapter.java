package nutricion.hexagonal.infra.seguridad.adaptadores.salida;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import nutricion.hexagonal.dominio.clases.Usuario;

// infraestructura/seguridad/UserPrincipalAdapter.java
public class UserPrincipalAdapter implements UserDetails {
    private final Usuario usuario;

    public UserPrincipalAdapter(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        // return usuario.getRoles().stream()
        //     .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol))
        //     .toList();
    }

    @Override
    public String getPassword() {
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


