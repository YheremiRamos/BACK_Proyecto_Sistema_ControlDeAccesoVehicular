package cibertec.edu.pe.sistema_vehicular.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_vehiculo")
public class TipoVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoVehiculo;
    
    @Column(name = "nombreTipoVehiculo", nullable = false, length = 50)
    private String nombreTipoVehiculo; // Ejemplo: "Auto", "Moto", "Bici", etc
}
