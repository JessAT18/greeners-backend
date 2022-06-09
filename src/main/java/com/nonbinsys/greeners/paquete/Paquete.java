package com.nonbinsys.greeners.paquete;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "paquetes")
@Entity
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String link_paquete;

    private Long id_tipo_paquete;

    public Paquete() {
    }

    public Paquete(Long id, String nombre, String descripcion, String link_paquete, Long id_tipo_paquete) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.link_paquete = link_paquete;
        this.id_tipo_paquete = id_tipo_paquete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLink_paquete() {
        return link_paquete;
    }

    public void setLink_paquete(String link_paquete) {
        this.link_paquete = link_paquete;
    }

    public Long getId_tipo_paquete() {
        return id_tipo_paquete;
    }

    public void setId_tipo_paquete(Long id_tipo_paquete) {
        this.id_tipo_paquete = id_tipo_paquete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paquete paquete = (Paquete) o;
        return Objects.equals(id, paquete.id) && Objects.equals(nombre, paquete.nombre) && Objects.equals(descripcion, paquete.descripcion) && Objects.equals(link_paquete, paquete.link_paquete) && Objects.equals(id_tipo_paquete, paquete.id_tipo_paquete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, link_paquete, id_tipo_paquete);
    }

    @Override
    public String toString() {
        return "Paquete{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", link_paquete='" + link_paquete + '\'' +
                ", id_tipo_paquete=" + id_tipo_paquete +
                '}';
    }
}
