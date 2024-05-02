import { Component } from '@angular/core';
import { Employee } from '../../models/employee.model';
import { EmployeesService } from '../../services/employees/employees.service';
import { ActivatedRoute, Params, Router } from '@angular/router';

/**
 * Componente que se encarga de la edicion de los atributos de un empleado.
 */
@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrl: './edit-employee.component.css'
})
export class EditEmployeeComponent {

  /**
   * Referencia local del empleado.
   */
  public employee: Employee;

  /**
   * Fecha minima de ingreso.
   */
  minDate: string;

  /**
   * Fecha maxima de ingreso.
   */
  maxDate: string = new Date().toISOString().split('T')[0];

  constructor(
    private _employeeService: EmployeesService,
    private _route: ActivatedRoute,
    private _router: Router
  ){
    this.employee = new Employee();
    /**
     * Se calcula con la fecha actual, la fecha minima de ingreso.
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
   * Al arrancar el componente se ejecuta el metodo 'getEmployee'.
   */
  ngOnInit(){
    this.getEmployee();
  }

  /**
   * Al enviar el formulario, se agrega el parametro que solicita el servidor REST para identificar
   * el tipo de usuario a modificar, se espera que al terminar redireccione a '/employee'.
   */
  onSubmit() {
    this._route.params.forEach((params: Params) => {
      let id = params['id'];
      this.employee.type="employee";
      this._employeeService.editEmployee(id, this.employee).subscribe({
        next: (response: any) => {
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

  /**
   * Obtiene el empleado seleccionado segun su id recogido por parametro, se envia al servicio
   * 'employeeService' para realizar la peticion REST.
   */
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
