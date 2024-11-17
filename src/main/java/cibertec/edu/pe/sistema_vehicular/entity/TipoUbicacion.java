package cibertec.edu.pe.sistema_vehicular.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_ubicacion")
public class TipoUbicacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoUbicacion;
    
    @Column(name = "nombreTipoUbicacion", nullable = false, length = 50)
    private String nombreTipoUbicacion; // Ejemplo: "Sótano", "Piso", "Exterior"
}
