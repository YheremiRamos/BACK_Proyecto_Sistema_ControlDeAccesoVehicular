package cibertec.edu.pe.sistema_vehicular.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;


    private String nombres;
    private String apellidos;
    private String identificador;
    private String telefono;
    private int numIncidencias;
    
    public String getNombreApellido() {
    	return nombres + " " + apellidos;
    }

}
