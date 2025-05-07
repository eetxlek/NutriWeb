package nutricion.hexagonal.infra.adaptadores.entrada;

import org.springframework.stereotype.Component;

import nutricion.hexagonal.dominio.clases.Usuario;
import nutricion.hexagonal.infra.adaptadores.entrada.dto.RegistroRequestDTO;

@Component
public class UsuarioMapper {

    public Usuario fromDto(RegistroRequestDTO dto, String encriptada) {

        return new Usuario(
            0,
            dto.getNombre(),
            dto.getCorreoElectronico(),
            encriptada,
            dto.getEdad(),
            (float) dto.getPeso(),
            (float) dto.getAltura(),
            dto.getNivelActividad(),
            dto.getMetaSalud(),
            dto.getTipoDieta(),
            dto.getTipoUsuario()
        );
    }
}

