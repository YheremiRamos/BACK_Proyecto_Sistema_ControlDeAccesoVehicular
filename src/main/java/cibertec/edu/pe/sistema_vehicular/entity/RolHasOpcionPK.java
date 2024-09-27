package cibertec.edu.pe.sistema_vehicular.entity;


import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Embeddable
public class RolHasOpcionPK {

    private static final long serialVersionUID = 1L;
    private int idRol;
    private int idOpcion;
}
