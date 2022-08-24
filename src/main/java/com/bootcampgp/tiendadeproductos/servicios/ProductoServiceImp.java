package com.bootcampgp.tiendadeproductos.servicios;

import com.bootcampgp.tiendadeproductos.entidades.Producto;
import com.bootcampgp.tiendadeproductos.excepciones.ExisteException;
import com.bootcampgp.tiendadeproductos.excepciones.NoExisteException;
import com.bootcampgp.tiendadeproductos.repositorios.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoServiceImp implements ProductoService {

    private Logger logger = LoggerFactory.getLogger(VendedorServiceImp.class);

    @Autowired
    private ProductoRepository repo;

    @Override
    public Producto altaProducto(Producto producto) throws ExisteException {
        if (producto != null) {
            if (producto.getId() != null) {
                if (this.repo.findById(producto.getId()).isPresent()) {
                    throw new ExisteException("Ya existe un producto con id [" + producto.getId() + "]");
                }
            }
            return this.repo.save(producto);
        } else {
            return null;
        }
    }

    @Override
    public Iterable<Producto> listarProductos() {
        return this.repo.findAll();
    }

    @Override
    public Iterable<Producto> listarPorCategoria(String categoria) {
        return this.repo.findAllByCategoria(categoria);
    }

    @Override
    public Optional<Producto> buscarPorId(Long id) throws NoExisteException {
        if(!this.repo.findById(id).isPresent()){
            throw new NoExisteException("No existe producto con id ["+id+"]");
        } else {
            return this.repo.findById(id);
        }
    }
}
