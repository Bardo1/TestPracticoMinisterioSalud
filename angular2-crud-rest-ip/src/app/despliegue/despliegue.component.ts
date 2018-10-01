import { Component, OnInit } from '@angular/core';
import {IpGenerada} from '../numeros/shared/ipgenerada';
import {numero} from '../numeros/shared/numero';
import {numerosService} from "../numeros/shared/numeros.service";
import { Router, ActivatedRoute } from '@angular/router';



@Component({
  selector: 'app-despliegue',
  templateUrl: './despliegue.component.html',
  styleUrls: ['./despliegue.component.css']
})

export class DespliegueComponent implements OnInit {

  private IpGeneradas: IpGenerada[] = [];

 constructor(
    private router: Router,
    private route: ActivatedRoute,
    private numerosService: numerosService
  ){ }

ngOnInit() {
    var id = this.route.params.subscribe(params => {
      var id = params['id'];

      console.log("el id "+id);

      if (!id)
        return;

      this.numerosService.desplegarIps(id)
      .subscribe(data => this.IpGeneradas = data);
    });
  }



}

  

 