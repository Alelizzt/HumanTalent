import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute, Route, Router, Params } from '@angular/router';
import { EmployeesService } from '../../services/employees/employees.service';
import { Employee } from '../../models/employee.model';

/**
 * Componente que se encarga de listar todos los empleados registrados.
 */
@Component({
  selector: 'app-employee',
  templateUrl: './employees-list.component.html',
  styleUrl: './employees-list.component.css',
})
export class EmployeesListComponent {
  /**
   * Referencia local a la lista de empleados.
   */
  employees?: Employee[];

  /**
   * Referencia del empleado seleccionado por el usuario.
   */
  currentEmployee: Employee = {};

  /**
   * Variable bandera para rectificar la eliminacion de un empleado.
   */
  confirmed: any;

  /**
   * Inicializacion de la pagina actual.
   */
  currentPage: number = 0;

  /**
   * Inicializacion del numero total de empleados.
   */
  totalItems: number = 0;

  /**
   * Inicializacion del total de paginas.
   */
  totalPages: number = 0;

  /**
   * Inicializacion del filtro para buscar empleados.
   */
  filter: string = '';

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _employeeService: EmployeesService
  ) {
    this.confirmed = null;
  }

  /**
   * Al arrancar el componente, verifica si se ha implementado un filtro mediante parametro.
   */
  ngOnInit() {
    this._route.queryParams.subscribe(params => {
      this.filter = params['filter'] || ''; // Usar el valor del filtro de los parámetros de la URL, o cadena vacía si no hay filtro
      this.retrieveEmployees();
    });
  }

  /**
   * Metodo para recoger los empleados del servicio 'employeeService'.
   * Actualiza cada vez los empleados a mostrar segun las variables currentPage, totalItems y totalPages.
   */
  retrieveEmployees() {
    this._employeeService.getPaginatedData(this.currentPage).subscribe({
      next: (response: any) => {
        this.employees = response.data.content;
        this.currentPage = response.data.pageable.pageNumber;
        this.totalItems = response.data.totalElements;
        this.totalPages = response.data.totalPages;
      },
      error: (e) => {
        console.error(e);
      },
    });
  }

  /**
   * Metodo para recoger los empleados del servicio 'employeeService' de forma filtrada.
   * Actualiza cada vez los empleados a mostrar segun las variables currentPage, totalItems y totalPages.
   */
  retrieveSortedEmployees() {
    this._employeeService.getPaginatedSortedData(this.filter).subscribe({
      next: (response: any) => {
        this.employees = response.data.content;
        this.currentPage = response.data.pageable.pageNumber;
        this.totalItems = response.data.totalElements;
        this.totalPages = response.data.totalPages;
      },
      error: (e) => {
        console.error(e);
      },
    });
  }

  /**
   * Metodo que aplica el filtro seleccionado, si hay algun filtro, se aplica paginacion filtrada,
   * en caso contrario solo trae los empleados en el orden que envia por default la API.
   * @param filterValue valor del filtro, ejemplo: Name, LastName ...
   */
  applyFilter(filterValue: string) {
    this.filter = filterValue;

    if (filterValue) {
      this.retrieveSortedEmployees();
    } else {
      this.retrieveEmployees();
    }
  }

  /**
   * Metodo para especificar que hacer en caso de intentar cambiar de pagina.
   * @param page numero de la pagina a donde se quiere mover.
   */
  onPageChange(page: number) {
    this.currentPage = page;
    if (this.filter) {
      this.retrieveSortedEmployees();
    } else {
      this.retrieveEmployees();
    }
  }

  /**
   * Metodo para especificar ir en la anterior pagina.
   */
  goToPreviousPage() {
    if (this.currentPage >= 1) {
      this.currentPage--;
      this.retrieveEmployees();
    }
  }

  /**
   * Metodo para especifciar ir a la siguiente pagina.
   */
  goToNextPage() {
    if (this.currentPage <= this.totalPages) {
      this.currentPage++;
      this.retrieveEmployees();
    }
  }

  /**
   * Metodo para especificar la pagina donde se quiere dirigir.
   * @param page numero de pagina a direccionar.
   */
  goToPage(page: string) {
    const pageNumber = parseInt(page, 10);
    if (
      pageNumber &&
      pageNumber >= 1 &&
      pageNumber <= this.totalPages &&
      pageNumber !== this.currentPage
    ) {
      this.currentPage = pageNumber;
      this.retrieveEmployees();
    }
  }

  /**
   * Metodo para redireccionar a la informacion del empleado en '/employee'.
   * @param id identificacion del usuario en el API.
   */
  goEmployeeDetail(id: number) {
    this._router.navigate(['/employee', id]);
  }

  /**
   * Metodo para redireccionar a la edicion del empleado en '/edit-employee'.
   * @param id identificacion del usuario en el API.
   */
  goEmployeeEdit(id: number) {
    this._router.navigate(['/edit-employee', id]);
  }

  /**
   * Metodo para mover la pantalla del usuario al inicio de la pagina.
   */
  gotoTop() {
    window.scroll({
      top: 0,
      left: 0,
      behavior: 'smooth',
    });
  }

  /**
   * Metodo para confirmar el empleado a eliminar.
   * @param id identificacion del usuario en el API.
   */
  deleteConfirmed(id: number) {
    this.confirmed = id;
  }

  /**
   * Metodo para cancelar la eliminacion del empleado.
   */
  cancelConfirmed() {
    this.confirmed = null;
  }

  /**
   * Metodo para eliminar el empleado mediante el sercvicio 'employeeService'.
   * @param id identificacion del empleado en el API.
   */
  onDeleteEmployee(id: number) {
    this._employeeService.deleteEmployee(id).subscribe({
      next: (response: any) => {
        this.retrieveEmployees();
      },
      error: (e) => {
        console.error(e);
      },
    });
  }
}
