package hello;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
        
        @Autowired 
	private IpGeneradaRepository IpGeneradaRepository;
	
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
		java.util.Optional<Numero> numero = NumeroRepository.findById(id);
		if (!numero.isPresent())
			throw new Exception("id-" + id);
		return numero.get();
	}
	
	@DeleteMapping("/borrarNumero/{id}")//borramos uno por id
	public @ResponseBody Iterable<Numero>  borrarNumero(@PathVariable Integer id){	
                //borrarNumero
		NumeroRepository.deleteById(id);
                return NumeroRepository.findAll();
		//return "Eliminado con exito";
	}
                
  	
	@GetMapping("/actualizaNumero/{id}/{ip}")
	public ResponseEntity<Object> actualizaNumero1(@PathVariable String id, @PathVariable String ip) {
		int result = Integer.parseInt(id);	
                java.util.Optional<Numero> numeroOptional = NumeroRepository.findById(result);
	        Numero num=numeroOptional.get();
               
                Iterable<IpGenerada> lista = IpGeneradaRepository.findAllByIdnum(result);
                Iterator itr1 = lista.iterator();
                IpGenerada ipgen;
                while(itr1.hasNext()){
                ipgen = (IpGenerada) itr1.next();
                IpGeneradaRepository.delete(ipgen);
                }
                num.setIp(ip);
                int ip1 = Integer.parseInt(ip);
                guardaIpsGenePost(ip1);
                System.out.println("treeees");
                NumeroRepository.save(num);  
		return ResponseEntity.noContent().build();
	}
        
        
        @GetMapping("/guardaIpsGeneradas/{id}")//Traemos todos por get	
        public boolean guardaIpsGenePost(@PathVariable Integer id) {
            

          Iterable<String> listaIngresar = generaIpsPost(id);

          Numero numero = new Numero();
          String valor= Integer.toString(id);
          numero.setIp(valor);
          NumeroRepository.save(numero);     
          Iterable<Numero> listan = NumeroRepository.buscaporIp(valor); 
          Iterator itr = listan.iterator();
          Numero num = new Numero();
              while(itr.hasNext()) {   
                num = (Numero) itr.next();
              }
         
          String ip="";
               
          Iterator itr1 = listaIngresar.iterator();
              while(itr1.hasNext()) {   
                  
                ip = (String) itr1.next();
                System.out.println("se esta gestionando el ingreso de esta ip: "+ip);
                IpGenerada ipgen= new IpGenerada();             
                ipgen.setIdNumero(num);
                ipgen.setIp(ip);
                IpGeneradaRepository.save(ipgen); 
              }
              
		return true;
	}
        
        @GetMapping("/generaIps/{id}") //Traemos solo uno por get
	public @ResponseBody Iterable<String> generaIpsPost(@PathVariable Integer id)
       {
            
        String ip = String.valueOf(id); 
        String sCadena= ip; 
        char[] num = sCadena.toCharArray();     
        int largo =num.length;
        String valorf="";

            if(largo==4){
                 valorf =num[0]+"."+num[1]+"."+num[2]+"."+num[3];
            }if(largo==5){
                 valorf = num[0]+""+num[1]+"."+num[2]+"."+num[3]+"."+num[4];
            }if(largo==6){
                 valorf = num[0]+""+num[1]+""+num[2]+"."+num[3]+"."+num[4]+"."+num[5];
            }if(largo==7){
                valorf = num[0]+""+num[1]+""+num[2]+"."+num[3]+""+num[4]+"."+num[5]+"."+num[6];
            }if(largo==8){
                valorf = num[0]+""+num[1]+""+num[2]+"."+num[3]+""+num[4]+""+num[5]+"."+num[6]+"."+num[7];
            }if(largo==9){
                valorf = num[0]+""+num[1]+""+num[2]+"."+num[3]+""+num[4]+""+num[5]+"."+num[6]+""+num[7]+"."+num[8];
            }if(largo==10){
                valorf = num[0]+""+num[1]+""+num[2]+"."+num[3]+""+num[4]+""+num[5]+"."+num[6]+""+num[7]+""+num[8]+"."+num[9];
            }if(largo==11){
                valorf = num[0]+""+num[1]+""+num[2]+"."+num[3]+""+num[4]+""+num[5]+"."+num[6]+""+num[7]+""+num[8]+"."+num[9]+""+num[10];
            }if(largo==12){
                valorf = num[0]+""+num[1]+""+num[2]+"."+num[3]+""+num[4]+""+num[5]+"."+num[6]+""+num[7]+""+num[8]+"."+num[9]+""+num[10]+""+num[11];
            }

        String[] parts = valorf.split(Pattern.quote("."));
        String part1 = parts[0]; 
        String part2 = parts[1]; 
        String part3 = parts[2]; 
        String part4 = parts[3]; 

                    boolean valida=true;

                    List<String> list = new ArrayList<>();
                     if(validate(ip)){
                      list.add(ip);
                     }

                    String ipingreso="";
                     // validamos de derecha a izquierda
                    while(valida){
                            if (part1.length() > 1 && part1.length() <= 3 && part2.length() >= 1 && part2.length() < 3) {
                                String ultimo1 = part1.substring(part1.length() - 1);
                                String nuevo1 = part1.substring(0, part1.length() - 1);
                                ipingreso=""+nuevo1+"."+ultimo1+""+part2+"."+part3+"."+part4;
                                part1=nuevo1;
                                part2=ultimo1+""+part2;
                                      if(validate(ipingreso)){
                                          list.add(ipingreso);
                                      }
                                             if (part2.length() > 1 && part2.length() <= 3 && part3.length() >= 1 && part3.length() < 3) {
                                             String ultimo2 = part2.substring(part2.length() - 1);
                                             String nuevo2 = part2.substring(0, part2.length() - 1);
                                             ipingreso=""+part1+"."+nuevo2+"."+ultimo2+""+part3+"."+part4;
                                             part2=nuevo2;
                                             part3=ultimo2+""+part3;
                                                 if(validate(ipingreso)){
                                                     list.add(ipingreso);
                                                 }
                                                             if (part3.length() > 1 && part3.length() <= 3 && part4.length() >= 1 && part4.length() < 3) {
                                                                String ultimo3 = part3.substring(part3.length() - 1);
                                                                String nuevo3 = part3.substring(0, part3.length() - 1);
                                                                ipingreso=""+part1+"."+part2+"."+nuevo3+"."+ultimo3+""+part4;
                                                                part3=nuevo3;
                                                                part4=ultimo3+""+part4;
                                                                   if(validate(ipingreso)){
                                                                         list.add(ipingreso);
                                                                   }
                                                            }

                                                }

                            }
                            else if (part2.length() > 1 && part2.length() <= 3 && part3.length() >= 1 && part3.length() < 3) {
                                String ultimo2 = part2.substring(part2.length() - 1);
                                String nuevo2 = part2.substring(0, part2.length() - 1);
                                ipingreso=""+part1+"."+nuevo2+"."+ultimo2+""+part3+"."+part4;
                                part2=nuevo2;
                                part3=ultimo2+""+part3;
                                    if(validate(ipingreso)){
                                        list.add(ipingreso);
                                    }        
                                            if (part3.length() > 1 && part3.length() <= 3 && part4.length() >= 1 && part4.length() < 3) {
                                            String ultimo3 = part3.substring(part3.length() - 1);
                                            String nuevo3 = part3.substring(0, part3.length() - 1);
                                            ipingreso=""+part1+"."+part2+"."+nuevo3+"."+ultimo3+""+part4;
                                            part3=nuevo3;
                                            part4=ultimo3+""+part4;
                                               if(validate(ipingreso)){
                                                     list.add(ipingreso);
                                               }
                                            }
                            }
                            else if (part3.length() > 1 && part3.length() <= 3 && part4.length() >= 1 && part4.length() < 3) {
                                String ultimo3 = part3.substring(part3.length() - 1);
                                String nuevo3 = part3.substring(0, part3.length() - 1);
                                ipingreso=""+part1+"."+part2+"."+nuevo3+"."+ultimo3+""+part4;
                                part3=nuevo3;
                                part4=ultimo3+""+part4;
                                   if(validate(ipingreso)){
                                   list.add(ipingreso);
                                   }
                            }else{
                            valida=false;
                            }
                    }

                    // validamos de izquierda a derecha
                    valida=true;
                    while(valida){
                            if (part4.length() > 1  &&  part3.length() <= 2) {
                                String primero4 = part4.substring(0,1);
                                String nuevo4 = part4.substring(1, part4.length());
                                ipingreso=""+part1+"."+part2+"."+part3+""+primero4+"."+nuevo4;
                                part4=nuevo4;
                                part3=part3+""+primero4;
                                      if(validate(ipingreso)){
                                          list.add(ipingreso);
                                      }
                            }
                            else if (part3.length() > 1 && part2.length() <= 2) {
                                String primero3 = part3.substring(0,1);
                                String nuevo3 = part3.substring(1, part3.length());
                                ipingreso=""+part1+"."+part2+""+primero3+"."+nuevo3+"."+part4;
                                part3=nuevo3;
                                part2=part2+""+primero3;
                                 if(validate(ipingreso)){
                                          list.add(ipingreso);
                                      }
                            }
                            else if (part2.length() > 1  && part1.length() <=2) {
                                String primero2 = part2.substring(0,1);
                                String nuevo2 = part2.substring(1, part2.length());
                                ipingreso=""+part1+""+primero2+"."+nuevo2+"."+part3+"."+""+part4;
                                part2=nuevo2;
                                part1=part1+""+primero2;
                                if(validate(ipingreso)){
                                    list.add(ipingreso);
                                      }
                            }else{
                               valida=false;
                            }
                    }

                    Set<String> hs = new HashSet<>();
                    hs.addAll(list);
                    list.clear();
                    list.addAll(hs);
                    
                    return list;   
                    
	}
	
private static final Pattern PATTERN = Pattern.compile(
        "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

public static boolean validate(final String ip) {
    return PATTERN.matcher(ip).matches();
}	
        
 }       
