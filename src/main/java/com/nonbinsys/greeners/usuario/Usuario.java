package com.nonbinsys.greeners.usuario;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String contra_usuario;
    private Long id_persona;
    private Long id_rol;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String contra_usuario, Long id_persona, Long id_rol) {
        this.id = id;
        this.nombre = nombre;
        this.contra_usuario = contra_usuario;
        this.id_persona = id_persona;
        this.id_rol = id_rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nombre, usuario.nombre) && Objects.equals(contra_usuario, usuario.contra_usuario) && Objects.equals(id_persona, usuario.id_persona) && Objects.equals(id_rol, usuario.id_rol);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", contra_usuario='" + contra_usuario + '\'' +
                ", id_persona=" + id_persona +
                ", id_rol=" + id_rol +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, contra_usuario, id_persona, id_rol);
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

    public String getContra_usuario() {
        return contra_usuario;
    }

    public void setContra_usuario(String contra_usuario) {
        this.contra_usuario = contra_usuario;
    }

    public Long getId_persona() {
        return id_persona;
    }

    public void setId_persona(Long id_persona) {
        this.id_persona = id_persona;
    }

    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }
}
