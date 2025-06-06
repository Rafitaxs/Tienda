package com.tienda.service;

import com.tienda.domain.Categoria;
import com.tienda.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {
    
    //Crear un objeto de manera unica para todo el proyecto y de manera automatica
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Transactional(readOnly=true)
    public List<Categoria> getCategorias(boolean activo){
        var lista = categoriaRepository.findAll();
        
        
        //Se valida si solo se desean categorias activas:
        if(activo){
            //Solo se quieren activas:
            lista.removeIf(c -> !c.isActivo());
        }
        
        
        return lista;
        
    }
}
