package com.sm.pelicula.repositorios;

import com.sm.pelicula.modelos.Pelicula;

import org.springframework.data.jpa.repository.JpaRepository;
/*

JpaRepository<> es una interfaz proporcionada por Spring Data JPA que extiende 
las capacidades de los repositorios en JPA. Su principal funcionalidad es simplificar el acceso y la manipulación de datos 
en la base de datos, eliminando la necesidad de escribir código SQL o consultas manualmente.
JpaRepository proporciona un conjunto completo de operaciones CRUD (Crear, Leer, Actualizar, Eliminar) y algunas funcionalidades avanzadas como paginación y ordenación.

*/


//aca nos esta pidiendo el tipo de entidad con el q vamos a trabajar y el tipo de dato del id de esa entidad
//este interface realiza, la actualacion, eliminiacion, insercion y muestra de los datos q se agregan 
//a la entidad Pelicula
public interface PeliculaRepositorio extends JpaRepository<Pelicula , Long> {
 
    
    
    
}
