package com.bootcampgp.tiendadeproductos.servicios;

import com.bootcampgp.tiendadeproductos.entidades.Vendedor;
import com.bootcampgp.tiendadeproductos.excepciones.NoExisteException;
import com.bootcampgp.tiendadeproductos.excepciones.ExisteException;
import com.bootcampgp.tiendadeproductos.repositorios.VendedorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendedorServiceImp implements VendedorService {

    private Logger logger = LoggerFactory.getLogger(VendedorServiceImp.class);

    @Autowired
    private VendedorRepository repo;

    @Override
    public Vendedor altaVendedor(Vendedor vendedor) throws ExisteException {
        if (vendedor != null) {
            if (vendedor.getId() != null) {
                if (this.repo.findById(vendedor.getId()).isPresent()) {
                    throw new ExisteException("Ya existe vendedor con id " + vendedor.getId());
                }
            }
            vendedor.setComisionesVentas(0.0);
            vendedor.setSueldoTotal(vendedor.getSueldoBase() + vendedor.getComisionesVentas());
            return this.repo.save(vendedor);
        } else {
            return null;
        }
    }

    @Override
    public Vendedor actualizarVendedor(Long id, Vendedor vendedorModificado) throws NoExisteException {
        Optional<Vendedor> vendedorPorId = this.repo.findById(id);
        if (vendedorPorId.isPresent()) {
            Vendedor vendedor = vendedorPorId.get();
            vendedor.setComisionesVentas(vendedorModificado.getComisionesVentas());
            vendedor.setSueldoTotal(vendedorModificado.getSueldoTotal());
            return this.repo.save(vendedor);
        } else {
            throw new NoExisteException("No existe vendedor con id " + id);
        }
    }

    @Override
    public Iterable<Vendedor> listarVendedores() {
        return this.repo.findAll();
    }

    @Override
    public Optional<Vendedor> buscarPorId(Long id) throws NoExisteException {
        Optional<Vendedor> vendedorPorId = this.repo.findById(id);
        if (vendedorPorId.isPresent()) {
            return vendedorPorId;
        } else {
            throw new NoExisteException("No existe vendedor con id " + id);
        }
    }

}
