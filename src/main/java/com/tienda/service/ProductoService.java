package com.tienda.service;

import com.tienda.domain.Producto;
import com.tienda.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {

    //Crear un objeto de manera unica para todo el proyecto y de manera automatica
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activo) {
        var lista = productoRepository.findAll();

        //Se valida si solo se desean productos activas:
        if (activo) {
            //Solo se quieren activas:
            lista.removeIf(c -> !c.isActivo());
        }

        return lista;

    }

    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoRepository.findById(producto.getIdProducto())
                .orElse(null);

    }

    @Transactional
    public void save(Producto producto) {
        productoRepository.save(producto);

    }

    @Transactional
    public boolean delete(Producto producto) {
        try {
            productoRepository.delete(producto);
            productoRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional(readOnly = true)
    public List<Producto> consultaAmpliada(double precioInf, double precioSup) {
        return productoRepository.findByPrecioBetweenOrderByPrecio(precioInf, precioSup);
    }

    @Transactional(readOnly = true)
    public List<Producto> consultaJPQL(double precioInf, double precioSup) {
        return productoRepository.consultaJPQL(precioInf, precioSup);
    }

    @Transactional(readOnly = true)
    public List<Producto> consultaSQL(double precioInf, double precioSup) {
        return productoRepository.consultaSQL(precioInf, precioSup);
    }
}
