package com.bootcampgp.tiendadeproductos.servicios;

import com.bootcampgp.tiendadeproductos.entidades.Producto;
import com.bootcampgp.tiendadeproductos.entidades.Vendedor;
import com.bootcampgp.tiendadeproductos.entidades.Venta;
import com.bootcampgp.tiendadeproductos.excepciones.ExisteException;
import com.bootcampgp.tiendadeproductos.excepciones.NoExisteException;
import com.bootcampgp.tiendadeproductos.repositorios.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImp implements VentaService {

    @Autowired
    private VentaRepository repo;

    @Autowired
    private VendedorService vendedorService;

    @Autowired
    private ProductoService productoService;

    //TODO BORRAR LO QUE NO SE USE

    @Override
    public Iterable<Venta> listarVentas() {
        return this.repo.findAll();
    }

    @Override
    public Optional<Venta> buscarVentaPorId(Long id) throws NoExisteException {
        if (!this.repo.findById(id).isPresent()) {
            throw new NoExisteException("No existe venta con id [" + id + "]");
        } else {
            return this.repo.findById(id);
        }
    }

    @Override
    public Venta altaVenta(Venta venta) throws ExisteException, NoExisteException {
        if (venta != null) {
            if (venta.getId() != null) {
                if (this.repo.findById(venta.getId()).isPresent()) {
                    throw new ExisteException("Ya existe una venta con id [" + venta.getId() + "]");
                }
            }
        }
        //Seteo la fecha
        venta.setFecha(new Date());

        //Seteo el total
        Optional<Producto> productoOp = this.productoService.buscarPorId(venta.getProducto().getId());
        if(!productoOp.isPresent()){
            throw new NoExisteException("El producto no existe");
        }
        Producto producto = productoOp.get();
        venta.setTotal(producto.getPrecio());


        //Valido vendedor
        Optional<Vendedor> vendedorOp = this.vendedorService.buscarPorId(venta.getVendedor().getId());
        if(!vendedorOp.isPresent()){
            throw new NoExisteException("El vendedor no existe");
        }

        //Persisto venta
        Venta resultado = this.repo.save(venta);

//        Actualizo comisiones y sueldo del vendedor
        Double comisionVentaActualizada = this.porcentajeComision(venta);

        Vendedor vendedor = vendedorOp.get();
        vendedor.setComisionesVentas(comisionVentaActualizada);
        vendedor.setSueldoTotal(vendedor.getSueldoBase()+vendedor.getComisionesVentas());

        //Persisto cambios en vendedor
        this.vendedorService.actualizarVendedor(vendedor.getId(), vendedor);

        //Devuelvo venta
        return resultado;
    }

    private Double porcentajeComision(Venta venta) {
        List<Venta> ventasVendedor = (List<Venta>) this.repo.buscarVentasPorIdVendedor(venta.getVendedor().getId());
        Double totalVentas = 0.0;
        for (Venta v : ventasVendedor) {
            totalVentas += v.getTotal();
        }
        return (ventasVendedor.size() <= 2 ? totalVentas * 0.05 : totalVentas * 0.1);
    }

}
