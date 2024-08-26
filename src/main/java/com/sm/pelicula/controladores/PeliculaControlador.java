package com.sm.pelicula.controladores;

import com.sm.pelicula.modelos.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
//importamos la interface q creamos
import com.sm.pelicula.repositorios.PeliculaRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

//con esta anotacion estamos indicando que se va a encargar de realizar las peticiones q recibe
@RestController
//con esta anotacion estamos dicinedo q cuando alguien realiza unA peticion a esa Uri , el controlador
//responde.
@RequestMapping("/api/peliculas")
public class PeliculaControlador {
    
    //creamos un objeto de PeliculaRepositorio
    @Autowired
    private PeliculaRepositorio peliculaRepo;
    
    //vamos a atomizar todas la funcionas para q cada uno pueda hacer un trabajo de manera orndenada 
    //en el pedidos
    
    /*cuando hagan /api/peliculas/all -> va a mostar los datos(get)
    expecificamos el uri específico q va a tener esta funcion para que asi funcione
     --solo se puede utlizar una uri unica para cada get--
    //podemos utliza la especificacion de uri, si es q tenemos mucho gets
    
        @GetMapping("/all")
    */
   
    //en este caso no vamos a tener mucho get, asi q no hace falta la especificacion de uri
    @CrossOrigin
    @GetMapping
    //mostrar todas las peliculas
    public List<Pelicula> getTodasPelis (){
    
        //recuperamos todos los datos
       return peliculaRepo.findAll();
        
    }
    
    //dovolver pelicula deseada
    //en este caso utlizamos como respues la clase ResponseEntity<>
    // insertamos un parametro, la cual no especifica q ide quiere q mostremos
    @CrossOrigin
    @GetMapping("/{id}")  
    public ResponseEntity<Pelicula> getPeliculaByid(@PathVariable Long id ){
        
        //si el id existe en nustra db, va a guardarlo en nuestra optional<>
        Optional<Pelicula> pelicula =  peliculaRepo.findById(id);

        //Aca estamos diciendo que q si el objeto pelicula encuentra, el id solicidato, va a mostrar un ok
        //pero si no encuentra va a mostra un funcion flecha q dice notFound()
        //la funcionlidad de build() es q envie la respues sin cuuerto.
        return pelicula.map(ResponseEntity::ok)
                          .orElseGet( () ->  ResponseEntity.notFound().build() );
    }

    //creamoremos la funcion de crear(post)
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Pelicula> creacionPelicula(@RequestBody Pelicula pelicula){

        //el metodo save() es para guardar los datos
        //creamos un objeto para de Pelicula para gurado los datos de la creacion q hicieron
        //los recibe del parametro pelicula
        Pelicula peliculaGuarda = peliculaRepo.save(pelicula);

        //aca estamos enviando como respuesta q estado creado, un mnesaje de creacion realizada.
        //dentro del body esta la pelicula creada.
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuarda);
    }

    //creamos la funcion de eliminar pelicula
    //usamos void porque esta funcion no guarda nada
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminacionPelicula(@PathVariable Long id) { //con void especificamos q no devolvemos nada

        //aca estamos realizando una condicion donde si la pelicula no existe
        //me va a retornar un mensaje de notFound()
        if (!peliculaRepo.existsById(id)) {

            return ResponseEntity.notFound().build();
        }
        //eliminamos la pelicula x medio de la id
        peliculaRepo.deleteById(id);
        //al eliminar le respondemos q no hay contenido sin un cuerpo como respuesta
        return ResponseEntity.noContent().build();

    }


    //actualiacion de pelicula(modificacion)
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizacionPelicula( @PathVariable Long id , @RequestBody Pelicula actualizacionPeli){

            //comprobramos si el id q quiere modificar existe
            if(!peliculaRepo.existsById(id)){
                return ResponseEntity.notFound().build();
            }

            //si exite, realizamos un setter pasandole los datos nuevos al respectivo id
            actualizacionPeli.setId(id);
            //actualcion con save() a la base de datos los respectivos datos
            Pelicula guardadoActalizacion  = peliculaRepo.save(actualizacionPeli);
            return ResponseEntity.ok(guardadoActalizacion);

    }

    @CrossOrigin
    @GetMapping("/voto/{id}/{clasif}")
    public ResponseEntity<Pelicula> VotacionPeli (@PathVariable  Long id , @PathVariable  double clasif){

        //comprobramos si el id q quiere modificar existe
        if(!peliculaRepo.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        Optional<Pelicula> opcional  = peliculaRepo.findById(id);

        Pelicula pelicula  = opcional.get();

        //realizamos el calculo de votacion y agreacion
        // ( {pelicula.votos * pelicula.clasif} + clasif ) / {pelicula.votos + 1}

        double nuevaClasif = ( (pelicula.getVotos() * pelicula.getClasif() ) + clasif ) / (pelicula.getVotos() + 1);

        pelicula.setVotos(pelicula.getVotos() + 1 );
        pelicula.setClasif(nuevaClasif);

        Pelicula  guardadoClasif  = peliculaRepo.save(pelicula);

        return ResponseEntity.ok(guardadoClasif);
    }
}