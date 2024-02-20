import { Routes } from '@angular/router';
import { EmployeeComponent } from './components/employee/employee.component';
import { ErrorComponent } from './components/error/error.component';

export const routes: Routes = [
    {path: '', component: EmployeeComponent},
    {path: '**', component: ErrorComponent}
];
