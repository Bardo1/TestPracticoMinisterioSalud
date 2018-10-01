package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController    // Anotación especifica de controlador rest
@RequestMapping(path="/api") // url inicial del controlador
public class IpGeneradaController {
	
	@Autowired 
	private IpGeneradaRepository IpGeneradaRepository;
	
	@GetMapping(path="/guardaIpGenerada") //Creamos por get
	public @ResponseBody String creaIpGeneradaGet(@RequestParam String ip) {		
		IpGenerada n = new IpGenerada();
		n.setIp(ip);
		IpGeneradaRepository.save(n);
		return "Guardado por get";
	}
        
   
        @GetMapping("/buscaIps/{id}")//Traemos todos por get
	public @ResponseBody Iterable<IpGenerada> buscabuscaIpsGet(@PathVariable Integer id) {		            
          Iterable<IpGenerada> lista =  IpGeneradaRepository.findAllByIdnum(id);
          return lista;
	}
        
	
	@PostMapping("/guardaIpGenerada1") //Creamos por post
	public IpGenerada creaIpGeneradaPost(@RequestBody IpGenerada IpGenerada) {
	    IpGeneradaRepository.save(IpGenerada);
		return IpGenerada;
	}
	
	@GetMapping(path="/listaIpGeneradas") //Traemos todos por get
	public @ResponseBody Iterable<IpGenerada> getListaIpGeneradas() {//Retorna todas las ips generadas
          Iterable<IpGenerada> lista =  IpGeneradaRepository.findAll();
            return lista;
	}
	
	@GetMapping("/buscaIpGenerada/{id}") //Traemos solo uno por get
	public IpGenerada buscaNumero(@PathVariable Integer id) throws Exception {
		java.util.Optional<IpGenerada> IpGenerada = IpGeneradaRepository.findById(id);
		if (!IpGenerada.isPresent())
			throw new Exception("id-" + id);
		return IpGenerada.get();
	}
	
	@DeleteMapping("/borrarIpGenerada/{id}")//borramos uno por id
	public void  borrarIpGenerada(@PathVariable Integer id){		
		IpGeneradaRepository.deleteById(id);
		//return "Eliminado con exito";
	}
		
	@PutMapping("/actualizaIpGenerada/{id}")
	public ResponseEntity<Object> actualizaNumero1(@RequestBody IpGenerada IpGenerada, @PathVariable Integer id) {
		java.util.Optional<IpGenerada> personaOptional = IpGeneradaRepository.findById(id);
		if (!personaOptional.isPresent())
			return ResponseEntity.notFound().build();
		IpGenerada.setId(id);
		IpGeneradaRepository.save(IpGenerada);
		return ResponseEntity.noContent().build();
	}
		
}
