package com.apiferreteria.ProyectoFerreteria.repository;

import com.apiferreteria.ProyectoFerreteria.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iProductoRepository extends JpaRepository<Producto, Long> {
}
