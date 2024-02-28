import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GLOBAL } from './global';
import { Observable, catchError, map } from 'rxjs';
import { Employee } from '../../models/employee.model';


@Injectable({
  providedIn: 'root'
})
export class EmployeesService {
  private url :string = GLOBAL.url;

  constructor( private _http: HttpClient) {
   }

   getAll(): Observable<Employee[]> {
    return this._http.get<Employee[]>(`${this.url}employees`);
   }

   /*addEmployee(empleado: Employee): {
    let json = JSON.stringify(empleado);
    let headers = new Headers([{'Content-Type':'application/json'}]);
    let options = new RequestOptions({headers: headers});

    return this._http.put(this.url+'employee/'+id, json, options);
   }*/
}
