package hello;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NumeroRepository extends CrudRepository<Numero, Integer> {
	 
          
    @Query("SELECT t FROM Numero t where t.ip =:ip")
    Iterable<Numero> buscaporIp(@Param("ip") String ip);

   
        
     
    
}
