package com.bootcampgp.tiendadeproductos.controladores;

import com.bootcampgp.tiendadeproductos.dto.VentaDTO;
import com.bootcampgp.tiendadeproductos.entidades.Venta;
import com.bootcampgp.tiendadeproductos.excepciones.ExisteException;
import com.bootcampgp.tiendadeproductos.excepciones.NoExisteException;
import com.bootcampgp.tiendadeproductos.servicios.VentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    @GetMapping()
    public List<Venta> listarVentas(){
        return (List<Venta>) this.service.listarVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> buscarVenta(@PathVariable Long id) {
        try {
            Optional<Venta> venta = this.service.buscarVentaPorId(id);
            ObjectMapper om = new ObjectMapper();
            VentaDTO ventaDTO = om.convertValue(venta.get(), VentaDTO.class);
            return ResponseEntity.ok(ventaDTO);
        } catch (NoExisteException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity altaVenta(@RequestBody VentaDTO ventaDTO) {
        try {
            ObjectMapper om = new ObjectMapper();
            Venta venta = om.convertValue(ventaDTO, Venta.class);
            Venta ventaEnDB = this.service.altaVenta(venta);
            VentaDTO vendedorPersistido = om.convertValue(ventaEnDB, VentaDTO.class);
            return ResponseEntity.ok(vendedorPersistido);
        } catch (ExisteException e) {
            //TODO agregar logger a todas las catch
            return ResponseEntity.badRequest().body("Detalle: " + e.getMessage());
        } catch (NoExisteException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
