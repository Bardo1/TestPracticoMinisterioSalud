import { Routes, RouterModule } from '@angular/router';

import { numerosComponent } from './numeros.component';
import {numeroFormComponent} from "./numero-form/numero-form.component";
import { DespliegueComponent } from '../despliegue/despliegue.component';

const numerosRoutes: Routes = [
  { path: 'numeros', component: numerosComponent, pathMatch: 'full' },
  { path: 'numeros/new', component: numeroFormComponent},
  { path: 'numeros/:id', component: numeroFormComponent},
  { path: 'despliegue/:id', component: DespliegueComponent }
];

export const numerosRouting = RouterModule.forChild(numerosRoutes);
