import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class UsersService {

  //private url: string = "http://jsonplaceholder.typicode.com/users";
  constructor(private http: Http) { }

  getUsers(){
    return this.http.get('http://localhost:8080/api/listaNumeros')
      .map(res => res.json());
  }

  getUser(id){
    return this.http.get('http://localhost:8080/api/buscaNumero/'+id)
      .map(res => res.json());
  }

  addUser(user){
    return this.http.post('http://localhost:8080/api/guardaNumero1', JSON.stringify(user))
      .map(res => res.json());
  }

  updateUser(user){
    return this.http.put('http://localhost:8080/api/actualizaNumero/'+user.id, JSON.stringify(user))
      .map(res => res.json());
  }

  deleteUser(id){
    return this.http.delete('http://localhost:8080/api/borrarNumero/'+id)
      .map(res => res.json());
  }

}
