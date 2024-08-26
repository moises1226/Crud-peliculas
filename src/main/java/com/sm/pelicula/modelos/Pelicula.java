package com.sm.pelicula.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity //mapea toda la tabla de nuestra base de datos conectada
@Table(name = "pelicula")//le indicamos a que tabla queremos mapear
public class Pelicula {
    
  //generamos una estructura para guardar los datos (son iguales a los datso de nuestra db mysql)
  //-----  
    
    //vamos indicarle cual es nuestra primary key 
    @Id 
    //le inidcamos q nuestra clave pk se auto genera
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String anio;
    private int votos;
    private double clasif;
    
    //como le pusimos otro nombre a la image_url, vamos a especificar a esa varible a cual atributo hace
    //referencia
    @Column(name = "imagen_url")
    private String imgUrl;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votes) {
        this.votos = votes;
    }

    public double getClasif() {
        return clasif;
    }

    public void setClasif(double clasif) {
        this.clasif = clasif;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    
    
    
    
}
