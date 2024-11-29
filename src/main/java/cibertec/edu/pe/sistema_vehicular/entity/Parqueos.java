package cibertec.edu.pe.sistema_vehicular.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "parqueos")
public class Parqueos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idParqueos;
    
    @ManyToOne
    @JoinColumn(name = "idUbicacion", nullable = false)
    private Ubicacion ubicacion; // Relación con la tabla "ubicacion"
    
    @ManyToOne
    @JoinColumn(name = "idTipoParqueo", nullable = false)
    private TipoParqueo tipoParqueo; // Relación con la tabla "tipo_parqueo"
    
    @ManyToOne
    @JoinColumn(name = "idTipoVehiculo", nullable = false)
    private TipoVehiculo tipoVehiculo; // Relación con la tabla "tipo_vehiculo"
    
    @ManyToOne
    @JoinColumn(name = "idEstadoEspacios", nullable = false)
    private EstadoEspacios estadoEspacios; // Relación con la tabla "estado_espacios"
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fechaCreacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion; // Fecha de creación
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fechaActualizacion", nullable = false)
    private LocalDateTime fechaActualizacion; // Fecha de última actualización

    @PrePersist
    public void prePersist() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
        if (fechaActualizacion == null) {
            fechaActualizacion = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}
