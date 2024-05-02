import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GLOBAL } from './global';
import { Observable, catchError, map, retry } from 'rxjs';
import { Employee } from '../../models/employee.model';
import { DatePipe } from '@angular/common';

/**
 * Servicio que se encarga de las peticiones relacionadas con el empleado al API.
 */
@Injectable({
  providedIn: 'root',
})
export class EmployeesService {
  /**
   * Direccion del API.
   */
  private url: string = GLOBAL.url;

  constructor(private _http: HttpClient) {}

  /**
   * Metodo encargado de recoger todos los empleados del API.
   * @returns Arreglo de empleados en formato JSON.
   */
  getAll(): Observable<Employee[]> {
    return this._http.get<Employee[]>(`${this.url}employees`);
  }

  /**
   * Metodo encargado de agregar el empleado al API.
   * @param employee empleado con todos los atributos generados por el usuario.
   * @returns peticion POST al API con los parametros necesarios.
   */
  addEmployee(employee: Employee): Observable<any> {
    let json = JSON.stringify(employee);
    const headers = new HttpHeaders()
      .set('Vary', 'Origin')
      .set('Vary', 'Access-Control-Request-Method')
      .set('Vary', 'Access-Control-Request-Headers')
      .set('Content-Type', 'application/json');

    return this._http.post<Employee>(this.url + 'employees', json, {
      headers: headers,
    });
  }

  /**
   * Metodo que trae la informacion de los empleados segun la pagina.
   * @param page numero de pagina actual.
   * @returns empleados ubicados en la pagina proporcionada.
   */
  getPaginatedData(page: number): Observable<Employee> {
    const params = new HttpParams().set('page', page.toString());
    return this._http.get<Employee>(this.url + 'employees', { params });
  }

  /**
   * Metodo que organiza los empleados segun filtro.
   * @param filter filtro seleccionado por el usuario.
   * @returns lista de empleados ordenados segun el filtro seleccionado.
   */
  getPaginatedSortedData(filter: string) {
    return this._http.get<Employee>(this.url + 'employees/'+filter);
  }

  /**
   * Metodo encargado de obtener un empleado segun su id.
   * @param id identificacion del empleado en el API.
   * @returns empleado registrado en el API.
   */
  getEmployee(id: number): Observable<Employee> {
    return this._http.get<Employee>(`${this.url}employees/id/` + id);
  }

  /**
   * Metodo para editar los atributos de un empleado.
   * @param id identificacion del usuario en el API.
   * @param employee empleado a editar con la informacion ya editada.
   * @returns peticion PUT al API con la informacion del empleado.
   */
  editEmployee(id: number, employee: Employee) {
    let json = JSON.stringify(employee);
    const headers = new HttpHeaders()
      .set('Vary', 'Origin')
      .set('Vary', 'Access-Control-Request-Method')
      .set('Vary', 'Access-Control-Request-Headers')
      .set('Content-Type', 'application/json');

    return this._http.put<Employee>(this.url + 'employees/' + id, json, {
      headers: headers,
    });
  }

  /**
   * Metodo para eliminar el empleado seleccionado.
   * @param id identificacion en el API del empleado.
   * @returns peticion DELETE al API.
   */
  deleteEmployee(id: number) {
    return this._http.delete(this.url + 'employees/' + id);
  }
}
