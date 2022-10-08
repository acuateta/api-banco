package com.acuateta.banco.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Personas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String genero;

    private Integer edad;

    private String identificacion;

    private String direccion;

    private String telefono;

}
