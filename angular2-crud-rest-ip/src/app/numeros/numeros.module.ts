import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule }  from '@angular/router';
import { HttpModule }  from '@angular/http';
import { numerosComponent } from './numeros.component';
import { numerosService } from './shared/numeros.service';
import { numeroFormComponent } from './numero-form/numero-form.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    HttpModule
  ],
  declarations: [
    numerosComponent,
    numeroFormComponent
  ],
  exports: [
    numerosComponent
  ],
  providers: [
    numerosService
  ]
})
export class numerosModule { }
