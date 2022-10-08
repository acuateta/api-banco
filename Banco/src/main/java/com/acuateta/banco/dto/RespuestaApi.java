package com.acuateta.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RespuestaApi {

    private String status;
    private Object body;
}
