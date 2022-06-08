package com.nonbinsys.greeners.comercio;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comercios")
public class Comercio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String telf;
    private String direccion;
    private String link_logo;

    private Long id_adm_comercio;

    public Comercio() {
    }

    public Comercio(String nombre, String telf, String direccion, Long id_adm_comercio) {
        this.nombre = nombre;
        this.telf = telf;
        this.direccion = direccion;
        this.id_adm_comercio = id_adm_comercio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comercio comercio = (Comercio) o;
        return Objects.equals(id, comercio.id) && Objects.equals(nombre, comercio.nombre) && Objects.equals(telf, comercio.telf) && Objects.equals(direccion, comercio.direccion) && Objects.equals(link_logo, comercio.link_logo) && Objects.equals(id_adm_comercio, comercio.id_adm_comercio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, telf, direccion, link_logo, id_adm_comercio);
    }

    @Override
    public String toString() {
        return "Comercio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telf='" + telf + '\'' +
                ", direccion='" + direccion + '\'' +
                ", link_logo='" + link_logo + '\'' +
                ", id_adm_comercio=" + id_adm_comercio +
                '}';
    }

    //region Getters and Setters
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

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getId_adm_comercio() {
        return id_adm_comercio;
    }

    public void setId_adm_comercio(Long id_adm_comercio) {
        this.id_adm_comercio = id_adm_comercio;
    }

    public String getLink_logo() {
        return link_logo;
    }

    public void setLink_logo(String link_logo) {
        this.link_logo = link_logo;
    }

    //endregion
}
