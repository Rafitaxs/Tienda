package com.tienda.repository;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    //Consultas para recuperar los productos de un rango de precios
    //Ordenado por precio
    public List<Producto>
            findByPrecioBetweenOrderByPrecio(double precioInf, double precioSup);

    //Consultas JPQL para recuperar los productos de un rango de precios
    //Ordenado por precio
    @Query(value = "SELECT a FROM Producto a WHERE a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.precio")
    public List<Producto> consultaJPQL(double precioInf, double precioSup);

    //Consultas SQL para recuperar los productos de un rango de precios
    //Ordenado por precio
    @Query(nativeQuery = true,
            value = "SELECT * FROM Producto a WHERE a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.precio")
    public List<Producto> consultaSQL(double precioInf, double precioSup);

    //Consulta de existencias Práctica4
    public List<Producto>
            findByExistenciasBetweenOrderByExistencias(int existenciasInf, int existenciasSup);

    //Consulta de descripción Práctica4
    public List<Producto>
            findByDescripcionContainingIgnoreCase(String descripcion);

}
