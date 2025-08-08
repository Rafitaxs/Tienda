package com.tienda.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="constante")
public class Constante {
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConstante;
    
    private String atributo;
    private String valor;
       
}
