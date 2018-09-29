import { Routes, RouterModule } from '@angular/router';

import { numerosComponent } from './numeros.component';
import {numeroFormComponent} from "./numero-form/numero-form.component";

const numerosRoutes: Routes = [
  { path: 'numeros', component: numerosComponent, pathMatch: 'full' },
  { path: 'numeros/new', component: numeroFormComponent},
  { path: 'numeros/:id', component: numeroFormComponent}
];

export const numerosRouting = RouterModule.forChild(numerosRoutes);
