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
  numero: numero = new numero();

  constructor(
    formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private numerosService: numerosService
  ) {
    this.form = formBuilder.group({
        ip: ['', Validators.pattern('^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$')]
    });
  }

  ngOnInit() {
    var id = this.route.params.subscribe(params => {
      var id = params['id'];
    // this.laip='';
      this.title = id ? 'Editar IP' : 'Ingrese nueva IP';

      if (!id)
        return;

      this.numerosService.getnumero(id)
        .subscribe(
          numero => this.numero = numero,
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

        console.log('numeroValue');
    console.log(numeroValue);

    if (numeroValue.id){
      result = this.numerosService.updatenumero(numeroValue);
    } else {
      result = this.numerosService.addnumero(numeroValue);
    }

    result.subscribe(data => this.router.navigate(['numeros']));
  }
}
