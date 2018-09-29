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
public class NumeroController {
	
	@Autowired 
	private NumeroRepository NumeroRepository;
	
	@GetMapping(path="/guardaNumero") //Creamos por get
	public @ResponseBody String creaNumeroGet(@RequestParam Integer ip) {		
		Numero n = new Numero();
		n.setIp(ip);
		NumeroRepository.save(n);
		return "Guardado por get";
	}
	
	@PostMapping("/guardaNumero1") //Creamos por post
	public Numero creaNumeroPost(@RequestBody Numero numero) {
	    NumeroRepository.save(numero);
		return numero;
	}
	
	@GetMapping(path="/listaNumeros") //Traemos todos por get
	public @ResponseBody Iterable<Numero> getListaNumeros() {//Retorna las personas en formato json
		return NumeroRepository.findAll();
	}
	
	@GetMapping("/buscaNumero/{id}") //Traemos solo uno por get
	public Numero buscaNumero(@PathVariable Integer id) throws Exception {
		java.util.Optional<Numero> persona = NumeroRepository.findById(id);
		if (!persona.isPresent())
			throw new Exception("id-" + id);
		return persona.get();
	}
	
	@DeleteMapping("/borrarNumero/{id}")//borramos uno por id
	public void  borrarNumero(@PathVariable Integer id){		
		NumeroRepository.deleteById(id);
		//return "Eliminado con exito";
	}
		
	@PutMapping("/actualizaNumero/{id}")
	public ResponseEntity<Object> actualizaNumero1(@RequestBody Numero persona, @PathVariable Integer id) {
		java.util.Optional<Numero> personaOptional = NumeroRepository.findById(id);
		if (!personaOptional.isPresent())
			return ResponseEntity.notFound().build();
		persona.setId(id);
		NumeroRepository.save(persona);
		return ResponseEntity.noContent().build();
	}
		
}