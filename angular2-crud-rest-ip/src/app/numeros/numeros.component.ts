import { Component, OnInit } from '@angular/core';
import {numerosService} from "./shared/numeros.service";
import {numero} from "./shared/numero";
import {IpGenerada} from "./shared/IpGenerada";

@Component({
  selector: 'app-numeros',
  templateUrl: './numeros.component.html',
  styleUrls: ['./numeros.component.css']
})
export class numerosComponent implements OnInit {

  private numeros: numero[] = [];
  private IpGeneradas: IpGenerada[] = [];

  constructor(private numerosService: numerosService) { }

  ngOnInit() {
    this.numerosService.getnumeros()
      .subscribe(data => this.numeros = data);
  }

  desplegarIps(id){
     this.numerosService.desplegarIps(id)
      .subscribe(data => this.IpGeneradas = data);
  }



  deletenumero(numero){
    if (confirm("Are you sure you want to delete " + numero.name + "?")) {
      var index = this.numeros.indexOf(numero);
      console.log("el index :"+index);
      console.log("el numero id :"+numero.id);
    //  this.numeros.splice(index, 1);

      this.numerosService.deletenumero(numero.id)
        .subscribe(data => this.IpGeneradas = data);
    }
  }

}
