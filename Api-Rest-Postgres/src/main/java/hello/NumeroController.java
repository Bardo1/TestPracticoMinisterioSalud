package hello;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
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
	public @ResponseBody String creaNumeroGet(@RequestParam String ip) {		
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
	public @ResponseBody Iterable<Numero>  borrarNumero(@PathVariable Integer id){	
                //borrarNumero
		NumeroRepository.deleteById(id);
                    System.out.println("se supone que borró ");
                return NumeroRepository.findAll();
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
        
        
        @PostMapping("/generaIps") //Creamos por post
	public @ResponseBody Iterable<Numero> generaIpsPost(@RequestBody String ip) {
                    
                    ip="123.123.1.1";
                    String[] parts = ip.split(Pattern.quote("."));
                    String part1 = parts[0]; // 123
                    String part2 = parts[1]; // 654321
                    String part3 = parts[2]; // 123
                    String part4 = parts[3]; // 654321

                    boolean valida=true;
                    List<String> list = new ArrayList<>();

                    String ipingreso="";
                    while(valida){
                            if (part1.length() > 1 && part1.length() <= 3 && part2.length() >= 1 && part2.length() < 3) {
                                String ultimo1 = part1.substring(part1.length() - 1);
                                String nuevo1 = part1.substring(0, part1.length() - 1);
                                ipingreso=""+nuevo1+"."+ultimo1+""+part2+"."+part3+"."+part4;
                                part1=nuevo1;
                                part2=ultimo1+""+part2;
                         //             if(validate(ipingreso)){
                                          System.out.println("es buena: "+ipingreso);
                                          list.add(ipingreso);
                         //             }
                            }
                            else if (part2.length() > 1 && part2.length() <= 3 && part3.length() >= 1 && part3.length() < 3) {
                                String ultimo2 = part2.substring(part2.length() - 1);
                                String nuevo2 = part2.substring(0, part2.length() - 1);
                                ipingreso=""+part1+"."+nuevo2+"."+ultimo2+""+part3+"."+part4;
                                part2=nuevo2;
                                part3=ultimo2+""+part3;
                       //          if(validate(ipingreso)){
                                          System.out.println("es buena: "+ipingreso);
                                          list.add(ipingreso);
                       //               }
                            }
                            else if (part3.length() > 1 && part3.length() <= 3 && part4.length() >= 1 && part4.length() < 3) {
                                String ultimo3 = part3.substring(part3.length() - 1);
                                String nuevo3 = part3.substring(0, part3.length() - 1);
                                ipingreso=""+part1+"."+part2+"."+nuevo3+"."+ultimo3+""+part4;
                                part3=nuevo3;
                                part4=ultimo3+""+part4;
                          //      if(validate(ipingreso)){
                                          System.out.println("es buena: "+ipingreso);
                                          list.add(ipingreso);
                           //           }
                            }else{
                            valida=false;
                            }

                    }

                    valida=true;
                    while(valida){

                            if (part4.length() > 1  &&  part3.length() <= 2) {
                                String primero4 = part4.substring(0,1);
                                String nuevo4 = part4.substring(1, part4.length());
                                ipingreso=""+part1+"."+part2+"."+part3+""+primero4+"."+nuevo4;
                                part4=nuevo4;
                                part3=part3+""+primero4;
                             //         if(validate(ipingreso)){
                                        //  System.out.println("es buena otra: "+ipingreso);
                                          list.add(ipingreso);
                              //        }
                               // list.add(ipingreso);
                            }
                            else if (part3.length() > 1 && part2.length() <= 2) {
                                String primero3 = part3.substring(0,1);
                       //       System.out.println("el primero del 3"+primero3);
                                String nuevo3 = part3.substring(1, part3.length());
                                ipingreso=""+part1+"."+part2+""+primero3+"."+nuevo3+"."+part4;
                                part3=nuevo3;
                                part2=part2+""+primero3;
                            //     if(validate(ipingreso)){
                                       //   System.out.println("es buena otra: "+ipingreso);
                                          list.add(ipingreso);
                             //         }
                            }
                            else if (part2.length() > 1  && part1.length() <=2) {
                                String primero2 = part2.substring(0,1);
                                String nuevo2 = part2.substring(1, part2.length());
                                ipingreso=""+part1+""+primero2+"."+nuevo2+"."+part3+"."+""+part4;
                                part2=nuevo2;
                                part1=part1+""+primero2;
                              //  if(validate(ipingreso)){
                                        //  System.out.println("es buena otra: "+ipingreso);
                                          list.add(ipingreso);
                                //      }
                            }else{
                               valida=false;
                            }
                    }
                    
                    
                            Iterator i = list.iterator();
                             while(i.hasNext())
                            {
                                System.out.println(i.next());
                            }
      
                             
                             //return  "123.123.123.123";
                         return    NumeroRepository.findAll();
	}
	
	
        
 }       
