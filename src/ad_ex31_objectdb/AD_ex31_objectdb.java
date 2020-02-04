
package ad_ex31_objectdb;

import java.util.List;
import javax.persistence.*;

public class AD_ex31_objectdb {
public void Conexion(){
    
}

// Algunas veces el proyecto puede dar error
// Eso es debido a que la base de datos esta corrompida o se da algun problema
//En ese caso, es necesario borrar los archivos que se generan en la carpeta db que tengo en el escritorio("p2.odb")
    public static void main(String[] args) {
        

        //Interfaz empleada para interactuar con un entity factory:
        // el createEntityManager crea una nueva entity factory:
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
        EntityManager em = emf.createEntityManager();

         
        // Almacenando 10 objetos en la base de datos :
        em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            Point p = new Point(i, i);
            
            //Permite que la instancia pueda ser manejada y persistente :
            em.persist(p);
        }
        //Borrado de lineas :
        // Instancia d eentity manager se emplea para comenzar y hacer commits de multiples transactions:
        
        em.getTransaction().begin();
        Query q0 = em.createQuery("DELETE from Point");
        q0.executeUpdate();
        em.getTransaction().commit();

        // Permite contar el numero de point objects de nuestra base de datos:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Permite buscar la media X 
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
       System.out.println("Average X: " + q2.getSingleResult());

        // Recupera todos los point object de nuestra base de datos:
       // Interfaz empleada para el control de la ejecucion de typed querys
        TypedQuery<Point> query
                = em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println(p);
        }
        
        //Seleccion de objetos especificos de nuestra base de datos
        //Se crea un nuevo query que selecciona un objeto de nuestra base de datos mediante su id
        //es necesario usar un alias si no da error
        //Query.getSingleResult() es un metodo que no necesita parametros, ejecuta una select QUery y que nos devuelve un tipo Object
        Query q3 = em.createQuery("select a from Point as a where a.id = 1 ");
        System.out.println(q3.getSingleResult());
        
        em.getTransaction().begin();
        Query q4 = em.createQuery("select p from Point p where p.id = 5");
        //COmo modificar un objeto de una base de datos de objectDB
        // Se puede emplear el metodo Query.getSingleResult() para obtener asi el resultado
        //Y almacenarlo en una variable de la clase Point =????
        
        //Nuevo Query de update para poder actualizar el valor ???
        Point point = (Point) q4.getSingleResult();
        
        
        em.getTransaction().commit();

        //Cerrando las conexiones :
        em.close();
        emf.close();
    }
}
    
    

