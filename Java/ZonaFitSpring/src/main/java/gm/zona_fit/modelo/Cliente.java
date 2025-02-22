package gm.zona_fit.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data //Para generar los metodos get y set automaticamente
@NoArgsConstructor //Agregar un constructor vacio
@AllArgsConstructor //Agregar un constructor con todos los parametos
@ToString //Agregar metodo toString
@EqualsAndHashCode //Agrega los metodos de Equal y HashCode
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Se recomienda cuando se trabaja con mySql y columnas de valor autoincrementable
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer membresia;
}
