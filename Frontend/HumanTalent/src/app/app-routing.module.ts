import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeesListComponent } from './components/employees-list/employees-list.component';
import { ErrorComponent } from './components/error/error.component';
import { AddEmployeeComponent } from './components/add-employee/add-employee.component';
import { DetailEmployeeComponent } from './components/detail-employee/detail-employee.component';
import { EditEmployeeComponent } from './components/edit-employee/edit-employee.component';

const routes: Routes = [
    {path: '', component: EmployeesListComponent},
    {path: 'employees', component: EmployeesListComponent},
    {path: 'register', component: AddEmployeeComponent},
    {path: 'employee/:id', component: DetailEmployeeComponent},
    {path: 'edit-employee/:id', component: EditEmployeeComponent},
    {path: '**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
