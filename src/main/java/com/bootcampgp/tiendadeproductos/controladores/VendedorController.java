package com.bootcampgp.tiendadeproductos.controladores;

import com.bootcampgp.tiendadeproductos.dto.VendedorDTO;
import com.bootcampgp.tiendadeproductos.entidades.Vendedor;
import com.bootcampgp.tiendadeproductos.excepciones.NoExisteException;
import com.bootcampgp.tiendadeproductos.excepciones.ExisteException;
import com.bootcampgp.tiendadeproductos.servicios.VendedorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/vendedores")
public class VendedorController {

    private Logger logger = LoggerFactory.getLogger(VendedorController.class);

    @Autowired
    private VendedorService service;

    @GetMapping()
    public List<Vendedor> listarVendedores() {
        return (List<Vendedor>) this.service.listarVendedores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedorDTO> buscarVendedor(@PathVariable Long id) {
        try {
            Optional<Vendedor> vendedor = this.service.buscarPorId(id);
            ObjectMapper om = new ObjectMapper();
            VendedorDTO vendedorDTO = om.convertValue(vendedor.get(), VendedorDTO.class);
            return ResponseEntity.ok(vendedorDTO);
        } catch (NoExisteException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity altaVendedor(@RequestBody VendedorDTO vendedorDTO) {
        try {
            ObjectMapper om = new ObjectMapper();
            Vendedor vendedor = om.convertValue(vendedorDTO, Vendedor.class);
            Vendedor vendedorEnDB = this.service.altaVendedor(vendedor);
            VendedorDTO vendedorPersistido = om.convertValue(vendedorEnDB, VendedorDTO.class);
            return ResponseEntity.ok(vendedorPersistido);
        } catch (ExisteException e) {
            //TODO agregar logger a todas las catch
            logger.error("Error: vendedor existe al persistir uno nuevo", e);
            return ResponseEntity.badRequest().body("Detalle: " + e.getMessage());
        }
    }

    //TODO ELIMINAR SI NO SE USA
    @PutMapping("/{id}")
    public ResponseEntity actualizarVendedor(@PathVariable Long id, @RequestBody VendedorDTO vendedorDTO) {
        try {
            ObjectMapper om = new ObjectMapper();
            Vendedor vendedor = om.convertValue(vendedorDTO, Vendedor.class);
            Vendedor vendedorModificado = this.service.actualizarVendedor(id, vendedor);
            VendedorDTO dto = om.convertValue(vendedorModificado, VendedorDTO.class);
            return ResponseEntity.ok(dto);
        } catch (NoExisteException e) {
            return ResponseEntity.badRequest().body("Error vendedor ");
        }
    }
}
