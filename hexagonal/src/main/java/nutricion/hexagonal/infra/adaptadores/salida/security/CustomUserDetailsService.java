package nutricion.hexagonal.infra.adaptadores.salida.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.dominio.interfaces.DeClases.UsuarioRepoSalida;

//imple de servicio que usa UsuarioRepo para recuperar suers
//convierete usuario en Userprincipaladapter

//servicio personalizado de spring sec que carga usuario en spring sec. loaduser se invoca en proceso de autenticacion, busca en BD y da UserPrincipalAdapter
//este da usuario cargado desde repo, el otro hace la traduccion conversion de usuario a userdetails.
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepoSalida usuarioRepo;

    public CustomUserDetailsService(UsuarioRepoSalida usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cargar el usuario desde el repositorio por correo electrónico
        Usuario usuario = usuarioRepo.buscarPorEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Convertir el usuario a UserPrincipalAdapter
        return new UserPrincipalAdapter(usuario);
    }
}

