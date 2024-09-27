package cibertec.edu.pe.sistema_vehicular.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class UsuarioHasRolPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idUsuario;
    private int idRol;

}
