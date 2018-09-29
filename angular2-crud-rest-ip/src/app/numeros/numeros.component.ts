import { Component, OnInit } from '@angular/core';
import {numerosService} from "./shared/numeros.service";
import {numero} from "./shared/numero";

@Component({
  selector: 'app-numeros',
  templateUrl: './numeros.component.html',
  styleUrls: ['./numeros.component.css']
})
export class numerosComponent implements OnInit {

  private numeros: numero[] = [];

  constructor(private numerosService: numerosService) { }

  ngOnInit() {
    this.numerosService.getnumeros()
      .subscribe(data => this.numeros = data);
  }

  deletenumero(numero){
    if (confirm("Are you sure you want to delete " + numero.name + "?")) {
      var index = this.numeros.indexOf(numero);
      this.numeros.splice(index, 1);

      this.numerosService.deletenumero(numero.id)
        .subscribe(null,
          err => {
            alert("Could not delete numero.");
            // Revert the view back to its original state
            this.numeros.splice(index, 0, numero);
          });
    }
  }

}
