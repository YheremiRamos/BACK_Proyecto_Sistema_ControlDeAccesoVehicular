package cibertec.edu.pe.sistema_vehicular.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "usuario_tiene_rol")
public class UsuarioHasRol {

    @EmbeddedId
    private UsuarioHasRolPK usuarioHasRolPk;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false, insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idRol", nullable = false, insertable = false, updatable = false)
    private Rol rol;

}
