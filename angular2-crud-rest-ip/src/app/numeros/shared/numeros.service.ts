import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

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

  addnumero(numero){
    return this.http.post('http://localhost:8080/api/generaIps', JSON.stringify(numero))
      .map(res => res.json());
  }

  updatenumero(numero){
     console.log('lo que se manda');
    console.log(numero);
    return this.http.put('http://localhost:8080/api/actualizaNumero/'+numero.id, JSON.stringify(numero))
      .map(res => res.json());
  }

  deletenumero(id){
    console.log("Esta la id"+id);
    return this.http.delete('http://localhost:8080/api/borrarNumero/'+id)
      .map(res => res.json());
  }

}
