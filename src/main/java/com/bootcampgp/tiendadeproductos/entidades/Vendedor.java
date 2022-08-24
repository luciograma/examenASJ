package com.bootcampgp.tiendadeproductos.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="vendedores")
@SequenceGenerator(name="gn_vendedor", sequenceName = "gn_vendedor", allocationSize = 1)
public class Vendedor {

    @Id
    @GeneratedValue(generator = "gn_vendedor", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombreApellido;

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name="sueldoTotal", column = @Column(name = "sueldo_total")),
//            @AttributeOverride(name="sueldoBase", column = @Column(name = "sueldo_base")),
//            @AttributeOverride(name="comisionesVentas", column = @Column(name = "comisiones_ventas"))
//    })
//    private Sueldo sueldo;

    private Double sueldoBase;
    private Double comisionesVentas;
    private Double sueldoTotal;

}
