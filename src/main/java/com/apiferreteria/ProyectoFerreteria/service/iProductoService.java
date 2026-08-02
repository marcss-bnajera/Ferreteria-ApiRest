package com.apiferreteria.ProyectoFerreteria.service;

import com.apiferreteria.ProyectoFerreteria.model.Producto;

import java.util.List;

public interface iProductoService {

    //Metodos para el CRUD
    List<Producto> traerProductos();

    //READ
    Producto buscarProducto (Long id);
    //CREATE
    Producto crearProducto (Producto prod);
    //UPDATE
    Producto editarProducto (Long idProducto, Producto prod);
    //DELETE
    boolean eliminarProducto(Long idProducto);

}
