package com.acuateta.banco.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Cliente extends Persona {

    @Column (unique = true  , nullable = false  )
    private String clienteId;

    private String contrasenia;

    private Boolean estado;


}
