import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { numero } from '../shared/numero';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class numerosService {

  //private url: string = "http://jsonplaceholder.typicode.com/numeros";
  constructor(private http: Http) { }

  getnumeros(){
    return this.http.get('http://localhost:8080/api/listaNumeros')
      .map(res => res.json());
  }

  getnumero(id){
    return this.http.get('http://localhost:8080/api/buscaNumero/'+id)
      .map(res => res.json());
  }

  desplegarIps(id){
    return this.http.get('http://localhost:8080/api/buscaIps/'+id)
     .map(res => res.json());
  }
  
  guardaIpsGeneradas(id){
    return this.http.get('http://localhost:8080/api/guardaIpsGeneradas/'+id)
    .map(res => res.json());
  }

  addnumero(id){
     console.log("Esta la id"+id);
    return this.http.get('http://localhost:8080/api/generaIps/'+id)
      .map(res => res.json());
  }

  updatenumero(ip,id){
     console.log("Esta la id"+id);
      console.log("Esta la ip"+ip);
    return this.http.get('http://localhost:8080/api/actualizaNumero/'+id+'/'+ip)
      .map(res => res.json());
  }

  deletenumero(id){
    console.log("Esta la id"+id);
    return this.http.delete('http://localhost:8080/api/borrarNumero/'+id)
      .map(res => res.json());
  }

}
