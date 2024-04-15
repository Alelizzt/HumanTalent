import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute, Route, Router, Params } from '@angular/router';
import { EmployeesService } from '../../services/employees/employees.service';
import { Employee } from '../../models/employee.model';

@Component({
  selector: 'app-employee',
  templateUrl: './employees-list.component.html',
  styleUrl: './employees-list.component.css',
})
export class EmployeesListComponent {
  employees?: Employee[];
  currentEmployee: Employee = {};
  confirmed: any;

  currentPage: number = 0;
  totalItems: number = 0;
  totalPages: number = 0;
  filter: string = '';

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _employeeService: EmployeesService
  ) {
    this.confirmed = null;
  }

  ngOnInit() {
    this._route.queryParams.subscribe(params => {
      this.filter = params['filter'] || ''; // Usar el valor del filtro de los parámetros de la URL, o cadena vacía si no hay filtro
      this.retrieveEmployees();
    });
  }

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

  applyFilter(filterValue: string) {
    this.filter = filterValue;
    /*this._router.navigate([], {
      relativeTo: this._route,
      queryParams: { filter: filterValue }, // Agregar el filtro como parámetro de la URL
      queryParamsHandling: 'merge', // Mantener otros parámetros de la URL
    });*/

    this.filter = filterValue;

    if (filterValue) {
      this.retrieveSortedEmployees();
    } else {
      this.retrieveEmployees();
    }
  }

  onPageChange(page: number) {
    this.currentPage = page;
    if (this.filter) {
      this.retrieveSortedEmployees();
    } else {
      this.retrieveEmployees();
    }
  }

  goToPreviousPage() {
    if (this.currentPage >= 1) {
      this.currentPage--;
      this.retrieveEmployees();
    }
  }

  goToNextPage() {
    if (this.currentPage <= this.totalPages) {
      this.currentPage++;
      this.retrieveEmployees();
    }
  }

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

  goEmployeeDetail(id: number) {
    this._router.navigate(['/employee', id]);
  }

  goEmployeeEdit(id: number) {
    this._router.navigate(['/edit-employee', id]);
  }

  gotoTop() {
    window.scroll({
      top: 0,
      left: 0,
      behavior: 'smooth',
    });
  }

  deleteConfirmed(id: number) {
    this.confirmed = id;
  }

  cancelConfirmed() {
    this.confirmed = null;
  }

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
