import { Component } from '@angular/core';
import { Employee } from '../../models/employee.model';
import { EmployeesService } from '../../services/employees/employees.service';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrl: './edit-employee.component.css'
})
export class EditEmployeeComponent {

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

  ngOnInit(){
    this.getEmployee();
  }

  onSubmit() {
    this._route.params.forEach((params: Params) => {
      let id = params['id'];
      this.employee.type="employee";
      this._employeeService.editEmployee(id, this.employee).subscribe({
        next: (response: any) => {
          console.log(response);
          if(response.success === true){
            this._router.navigate(["/employee", id])
          }
        },
          error: (e) => {
            console.error(e);
          }
      });  
    }); 
  }

  getEmployee() {
    this._route.params.forEach((params: Params) => {
      let id = params['id'];
      this._employeeService.getEmployee(id).subscribe({
        next: (response: any) => {
          if(response.success === true){
            this.employee = response.data;
          }
        },
          error: (e) => {
            console.error(e);
          }
      });
    });
  }
}
