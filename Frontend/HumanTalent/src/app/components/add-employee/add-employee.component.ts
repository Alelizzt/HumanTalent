import { Component } from '@angular/core';
import { Employee } from '../../models/employee.model';
import { EmployeesService } from '../../services/employees/employees.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrl: './add-employee.component.css'
})
export class AddEmployeeComponent {
  public employee: Employee;
  minDate: string;
  maxDate: string = new Date().toISOString().split('T')[0];

  constructor(
    private _employeeService: EmployeesService,
    private _route: ActivatedRoute,
    private _router: Router
  ){
    this.employee = new Employee();

    const actualDate = new Date();
    const lastMonth = actualDate.getMonth() - 1;
    let year = actualDate.getFullYear();

    let month;
    if(lastMonth === -1) {
      month = 11;
      year = year - 1;
    } else {
      month = lastMonth;
    }
    this.minDate = new Date(year, month, actualDate.getDate()).toISOString().split('T')[0];
  }

  onSubmit() {
    console.log(this.employee);
  }


}
