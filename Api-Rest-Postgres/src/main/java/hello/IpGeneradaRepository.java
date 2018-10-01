/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author walter
 */
public interface IpGeneradaRepository extends CrudRepository<IpGenerada, Integer> {
	
	@Query("SELECT t FROM IpGenerada t inner join t.numero nu where nu.id =:idnum")
	Iterable<IpGenerada> findAllByIdnum(@Param("idnum") Integer idnum);
	 
}
