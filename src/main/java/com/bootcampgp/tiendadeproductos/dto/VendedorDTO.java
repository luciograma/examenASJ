package com.bootcampgp.tiendadeproductos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendedorDTO {

    private String nombreApellido;
    private Double sueldoBase;
    private Double comisionesVentas;

}
