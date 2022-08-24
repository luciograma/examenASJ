package com.bootcampgp.tiendadeproductos.entidades;


import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Sueldo {

    private Double sueldoTotal;
    private Double sueldoBase;
    private Double comisionesVentas;

}
