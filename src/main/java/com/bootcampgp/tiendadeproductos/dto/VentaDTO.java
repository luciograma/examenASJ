package com.bootcampgp.tiendadeproductos.dto;

import com.bootcampgp.tiendadeproductos.entidades.Producto;
import com.bootcampgp.tiendadeproductos.entidades.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDTO {

    private Vendedor vendedor;
    private Producto producto;

}
