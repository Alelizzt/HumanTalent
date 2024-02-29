import { Component } from '@angular/core';
import { ActivatedRoute, Route } from '@angular/router';
import { EmployeesService } from '../../services/employees/employees.service';
import { Employee } from '../../models/employee.model';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [],
  templateUrl: './employees-list.component.html',
  styleUrl: './employees-list.component.css'
})
export class EmployeesListComponent {

  allEmployees?: Employee[];
  confirm: any;

  constructor(
    private _route: ActivatedRoute,
    private _employeeService: EmployeesService,
    
    ){
      this.confirm = null;
  }

  ngOnInit(){
    this.getEmployees();
    //console.log(this.allEmployees)
  }


  getEmployees() {
    this._employeeService.getAll().subscribe({
          next: (response: any) => {
          this.allEmployees = response.data;
        },
          error: (e) => {
            console.error(e);
          }
    });
  }

  handleError(error: any) {
    
  }

}
