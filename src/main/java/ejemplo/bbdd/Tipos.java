package ejemplo.bbdd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tipos")
public class Tipos {

    @Id
    @GeneratedValue(    strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private int id;

    @Column(name = "t_nombre")
    private String nombre;

    @Column(name = "t_numero")
    private int numero;

    public Tipos(){

    }

    public Tipos(String nombre,int numero){

        this.nombre = nombre;
        this.numero = numero;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tipos other = (Tipos) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    

}

