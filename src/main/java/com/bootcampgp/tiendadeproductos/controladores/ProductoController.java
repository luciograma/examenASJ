package com.bootcampgp.tiendadeproductos.controladores;

import com.bootcampgp.tiendadeproductos.dto.ProductoDTO;
import com.bootcampgp.tiendadeproductos.entidades.Producto;
import com.bootcampgp.tiendadeproductos.excepciones.ExisteException;
import com.bootcampgp.tiendadeproductos.excepciones.NoExisteException;
import com.bootcampgp.tiendadeproductos.servicios.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping()
    public List<Producto> listarProductos() {
        return (List<Producto>) this.service.listarProductos();
    }

    @GetMapping("/categoria/{categoria}")
    public List<Producto> listarProductosPorCategoria(@PathVariable String categoria) {
        return (List<Producto>) this.service.listarPorCategoria(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> buscarProductoPorId(@PathVariable Long id) {
        try {
            Optional<Producto> producto = this.service.buscarPorId(id);
            ObjectMapper om = new ObjectMapper();
            ProductoDTO productoDTO = om.convertValue(producto.get(), ProductoDTO.class);
            return ResponseEntity.ok(productoDTO);
        } catch (NoExisteException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity altaProducto(@RequestBody ProductoDTO productoDTO) {
        try {
            ObjectMapper om = new ObjectMapper();
            Producto producto = om.convertValue(productoDTO, Producto.class);
            Producto productoEnDB = this.service.altaProducto(producto);
            ProductoDTO productoPersistido = om.convertValue(productoEnDB, ProductoDTO.class);
            return ResponseEntity.ok(productoPersistido);
        } catch (ExisteException e) {
            return ResponseEntity.badRequest().body("Detalle: " + e.getMessage());
        }
    }

}
