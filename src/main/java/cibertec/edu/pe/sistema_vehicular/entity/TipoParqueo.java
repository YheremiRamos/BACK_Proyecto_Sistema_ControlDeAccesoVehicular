package cibertec.edu.pe.sistema_vehicular.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_parqueo")
public class TipoParqueo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoParqueo;
    
    @Column(name = "nombreTipoParqueo", nullable = false, length = 50)
    private String nombreTipoParqueo; // Ejemplo: "General", "Discapacitado", "Gerencia"
}
