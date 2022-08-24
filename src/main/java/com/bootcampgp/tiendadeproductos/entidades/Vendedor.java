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
    private Double sueldoBase;
    private Double comisionesVentas;
    private Double sueldoTotal;

}
