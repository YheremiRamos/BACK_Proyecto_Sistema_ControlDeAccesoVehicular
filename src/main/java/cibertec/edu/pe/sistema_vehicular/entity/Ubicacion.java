package cibertec.edu.pe.sistema_vehicular.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ubicacion")
public class Ubicacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUbicacion;
    
    @Column(name = "nombreUbicacion", nullable = false, length = 100)
    private String nombreUbicacion; // Ejemplo: "Sótano A", "Piso 1"
    
    @ManyToOne
    @JoinColumn(name = "idTipoUbicacion", nullable = false)
    private TipoUbicacion tipoUbicacion; // Relación con la tabla "tipo_ubicacion"
    
    @ManyToOne
    @JoinColumn(name = "idEstadoEspacios", nullable = false)
    private EstadoEspacios estadoEspacios; // Relación con la tabla "estado_espacios"
    
    @Column(name = "limiteParqueos", nullable = false)
    private int limiteParqueos; // Máximo número de parqueos en esta ubicación
}
