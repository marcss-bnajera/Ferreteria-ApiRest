package com.apiferreteria.ProyectoFerreteria.controller;

import com.apiferreteria.ProyectoFerreteria.model.Producto;
import com.apiferreteria.ProyectoFerreteria.service.iProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final iProductoService prodServ;


    public ProductoController(iProductoService prodServ) {
        this.prodServ = prodServ;
    }

    //READ
    @GetMapping
    public List<Producto> traerProductos() {
        return prodServ.traerProductos();
    }

    //READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProducto(@PathVariable Long id) {
        Producto prod = prodServ.buscarProducto(id);

        if (prod == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encuentra un producto con ese codigo");
        }

        return ResponseEntity.ok(prod);
    }

    //CREATE
    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto prod) {
        Producto productoCreado = prodServ.crearProducto(prod);

        if (productoCreado == null) {
            return ResponseEntity.badRequest()
                    .body("Los datos del producto no son validos");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoCreado);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> editarProducto(@PathVariable Long id,
                                            @RequestBody Producto prodModificado) {
        Producto productoEditado = prodServ.editarProducto(id, prodModificado);

        if (productoEditado == null) {
            return ResponseEntity.badRequest()
                    .body("No fue posible editar el producto");
        }

        return ResponseEntity.ok(productoEditado);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {

        boolean eliminado = prodServ.eliminarProducto(id);

        if (eliminado == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encuentra un producto con el id: " +id);
        }

        return ResponseEntity.ok("Producto eliminado correctamente");
    }


}
