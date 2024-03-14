import { Component } from '@angular/core';
import { Employee } from '../../models/employee.model';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { EmployeesService } from '../../services/employees/employees.service';

@Component({
  selector: 'app-detail-employee',
  templateUrl: './detail-employee.component.html',
  styleUrl: './detail-employee.component.css'
})
export class DetailEmployeeComponent {
  public employee?: Employee;

  constructor(
    private _router: Router,
    private _route: ActivatedRoute,
    private _employeeService: EmployeesService
  ){}

  ngOnInit(){
    this.getEmployeeFromService();
  }

  getEmployeeFromService(){
    this._route.params.forEach((params: Params) => {
      let id:number = params['id'];
      this._employeeService.getEmployee(id).subscribe({
        next: (response: any) => {
          this.employee = response.data;
        },
          error: (e) => {
            console.error(e);
          }
      });
    });
  }

}
