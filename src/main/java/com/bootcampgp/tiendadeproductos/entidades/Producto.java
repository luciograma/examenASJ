package com.bootcampgp.tiendadeproductos.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="productos")
@SequenceGenerator(name = "gn_productos", sequenceName = "gn_productos", allocationSize = 1)
public class Producto {

    @Id
    @GeneratedValue(generator = "gn_productos", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;
    private Double precio;
    private String categoria;
}
