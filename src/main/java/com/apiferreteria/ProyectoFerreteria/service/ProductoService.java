package com.apiferreteria.ProyectoFerreteria.service;

import com.apiferreteria.ProyectoFerreteria.model.Producto;
import com.apiferreteria.ProyectoFerreteria.repository.iProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements iProductoService{

    private final iProductoRepository prodRepo;

    public ProductoService(iProductoRepository prodRepo) {
        this.prodRepo = prodRepo;
    }

    @Override
    public List<Producto> traerProductos() {
        return prodRepo.findAll();
    }

    @Override
    public Producto buscarProducto(Long id) {
        return prodRepo.findById(id).orElse(null);
    }

    @Override
    public Producto crearProducto(Producto prod) {

        //Validacion para que el producto no sea null
        if (prod==null) {
            return null;
        }

        // Id se genera automaticamente en la DB y con esto la devolvemos
        //junto al producto
        return prodRepo.save(prod);

    }

    @Override
    public Producto editarProducto(Long idProducto, Producto prod) {
        //Buscar que el Producto si existe
        Producto prodExistente = buscarProducto(idProducto);

        //validacion
        if (prodExistente==null){
            return null;
        }

        //actualizar lso datos con el producto
        prodExistente.setCategoria(prod.getCategoria());
        prodExistente.setDescripcion(prod.getDescripcion());
        prodExistente.setMarca(prod.getMarca());
        prodExistente.setStock(prod.getStock());
        prodExistente.setPrecio(prod.getPrecio());
        prodExistente.setNombre(prod.getNombre());

        return prodRepo.save(prodExistente);
    }

    @Override
    public boolean eliminarProducto(Long idProducto) {
        Producto prodExistente = buscarProducto(idProducto);

        if (prodExistente==null){
            return false;
        }

        prodRepo.delete(prodExistente);
        return true;
    }
}
