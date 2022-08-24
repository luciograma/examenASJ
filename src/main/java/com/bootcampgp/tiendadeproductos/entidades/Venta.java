package com.bootcampgp.tiendadeproductos.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
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

//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape= JsonFormat.Shape.STRING, timezone = "GMT+3")
    private Date fecha;

}
