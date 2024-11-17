package cibertec.edu.pe.sistema_vehicular.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "estado_espacios")
public class EstadoEspacios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstadoEspacios;
    
    @Column(name = "nombreEstadoEspacios", nullable = false, length = 50)
    private String nombreEstadoEspacios; // Activo, Inactivo, Disponible, No disponible, Deshabilitado
}
