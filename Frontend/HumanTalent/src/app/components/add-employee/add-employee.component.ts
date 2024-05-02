import { Component } from '@angular/core';
import { Employee } from '../../models/employee.model';
import { EmployeesService } from '../../services/employees/employees.service';
import { ActivatedRoute, Router } from '@angular/router';

/**
 * Componente que se encarga de agregar los empleados y dar formato a
 * los datos segun se soliciten, en particular la fecha de registro
 */
@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrl: './add-employee.component.css'
})
export class AddEmployeeComponent {
  /**
   * Referencia local del empleado
   */
  public employee: Employee;

  /**
   * Fecha minima de ingreso
   */
  minDate: string;

  /**
   * Fecha maxima de ingreso
   */
  maxDate: string = new Date().toISOString().split('T')[0];

  constructor(
    private _employeeService: EmployeesService,
    private _route: ActivatedRoute,
    private _router: Router
  ){
    this.employee = new Employee();

    /**
     * Asigna la fecha minima de ingreso valida
     */
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

  /**
   * Al enviar el formulario, si todo fue correcto se espera que redireccione a '/employees'
   */
  onSubmit() {
    this._employeeService.addEmployee(this.employee).subscribe({
      next: (response: any) => {
        console.log(response);
        if(response.success === true){
          this._router.navigate(["/employees"])
        }
      },
        error: (e) => {
          console.error(e);
        }
    });
  }


}
