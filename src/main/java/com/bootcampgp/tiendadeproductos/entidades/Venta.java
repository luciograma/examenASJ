package com.bootcampgp.tiendadeproductos.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ventas")
@SequenceGenerator(name = "gn_ventas", sequenceName = "gn_ventas", allocationSize = 1)
public class Venta {

    @Id
    @GeneratedValue(generator = "gn_ventas", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_vendedor")
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name="id_producto")
    private Producto producto;

    private Double total;
    private Date fecha;

}
