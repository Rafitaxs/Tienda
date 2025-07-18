package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pruebas")
public class PruebasController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = productoService.getProductos(false);
        model.addAttribute("productos", lista);

        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);

        return "/pruebas/listado";
    }

    @GetMapping("/listado/{idCategoria}")
    public String listado(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);

        var lista = categoria.getProductos();
        model.addAttribute("productos", lista);

        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);

        return "/pruebas/listado";
    }

    @GetMapping("/listado2")
    public String listado2(Model model) {
        var lista = productoService.getProductos(false);
        model.addAttribute("productos", lista);

        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);

        return "/pruebas/listado2";
    }

    @PostMapping("/query1")
    public String query1(@RequestParam() double precioInf,
            @RequestParam() double precioSup,
            Model model) {

        var lista = productoService.consultaAmpliada(precioInf, precioSup);
        model.addAttribute("productos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2";
    }

    @PostMapping("/query2")
    public String query2(@RequestParam() double precioInf,
            @RequestParam() double precioSup,
            Model model) {

        var lista = productoService.consultaJPQL(precioInf, precioSup);
        model.addAttribute("productos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2";

    }

    @PostMapping("/query3")
    public String query3(@RequestParam() double precioInf,
            @RequestParam() double precioSup,
            Model model) {

        var lista = productoService.consultaSQL(precioInf, precioSup);
        model.addAttribute("productos", lista);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "/pruebas/listado2";

    }
    //Consulta 1 de la Práctica #4
    @PostMapping("/practica41")
    public String practica4(@RequestParam() int existenciasInf,
            @RequestParam() int existenciasSup,
            Model model) {

        var lista = productoService.consultaAmpliadaExistencias(existenciasInf, existenciasSup);
        model.addAttribute("productos", lista);
        model.addAttribute("existenciasInf", existenciasInf);
        model.addAttribute("existenciasSup", existenciasSup);
        return "/pruebas/listado2";
    }
    
    //Consulta 2 de la Práctica #4
    @PostMapping("/practica42")
    public String practica42(@RequestParam() String descripcion,
            Model model) {

        var lista = productoService.consultaAmpliadaDescripcion(descripcion);
        model.addAttribute("productos", lista);
        model.addAttribute("descripcion", descripcion);
        return "/pruebas/listado2";
    }
}
