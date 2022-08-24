package com.bootcampgp.tiendadeproductos.dto;

import com.bootcampgp.tiendadeproductos.entidades.Producto;
import com.bootcampgp.tiendadeproductos.entidades.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO {

    private Long id;
    private Vendedor vendedor;
    private Producto producto;
    private Double total;
    private Date fecha;

}
