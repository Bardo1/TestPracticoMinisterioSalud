import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { numero } from '../shared/numero';
import { numerosService } from '../shared/numeros.service';
import { BasicValidators } from '../../shared/basic-validators';

@Component({
  selector: 'app-numero-form',
  templateUrl: './numero-form.component.html',
  styleUrls: ['./numero-form.component.css']
})
export class numeroFormComponent implements OnInit {

  form: FormGroup;
  title: string;
  ipcosa: string;
  resp: boolean;
  lista: string [];
  unid: string;
  numero: numero = new numero();


  constructor(
    formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private numerosService: numerosService
  ) {
    this.form = formBuilder.group({
        ip: ['',  Validators.required ]
    });

          //  ip: ['', Validators.pattern('^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$')]
  }

  ngOnInit() {
    var id = this.route.params.subscribe(params => {
      var id = params['id'];
      this.title = id ? 'Editar IP' : 'Ingrese nueva IP';

      if (!id)
        return;
      this.numerosService.getnumero(id)
        .subscribe(
            data => 
        { 
         this.ipcosa = data.ip;
         this.unid =data.id;
        },
          response => {
            if (response.status == 404) {
              this.router.navigate(['NotFound']);
            }
          });
    });
  }

  

  save() {
    var result,
        numeroValue = this.form.value;

    if (this.title==='Editar IP'){
       console.log("entra por acá")
      this.numerosService.updatenumero(this.ipcosa,this.unid)
        .subscribe(
          resp => this.resp = resp,
          response => {
            if (response.status == 404) {
              this.router.navigate(['NotFound']);
            }
          });
       this.router.navigate(['numeros']);
    } else {
    result =  this.numerosService.addnumero(this.ipcosa)
        .subscribe(
          lista => this.lista = lista,
          response => {
            if (response.status == 404) {
              this.router.navigate(['NotFound']);
            }
          });

    }

    }


guardarIps() {
      this.numerosService.guardaIpsGeneradas(this.ipcosa)
        .subscribe(
          resp => this.resp = resp,
          response => {
            if (response.status == 404) {
              this.router.navigate(['NotFound']);
            }
          });
       this.router.navigate(['numeros']);

  }

}



