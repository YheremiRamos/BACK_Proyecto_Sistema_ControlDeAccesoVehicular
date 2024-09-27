package cibertec.edu.pe.sistema_vehicular.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol_has_opcion")
public class RolHasOpcion {


    @EmbeddedId
    private RolHasOpcionPK rolHasOpcionPK;

    @ManyToOne
    @JoinColumn(name = "idRol", nullable = false, insertable = false, updatable = false)
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "idOpcion", nullable = false, insertable = false, updatable = false)
    private Opcion opcion;


}
