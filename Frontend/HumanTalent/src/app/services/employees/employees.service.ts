import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GLOBAL } from './global';
import { Observable, catchError, map } from 'rxjs';
import { Employee } from '../../models/employee.model';
import { DatePipe } from '@angular/common';


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

   addEmployee(employee: Employee): Observable<any> {
    let json = JSON.stringify(employee);
    const headers = new HttpHeaders().set('Vary','Origin')
    .set('Vary','Access-Control-Request-Method')
    .set('Vary','Access-Control-Request-Headers')
    .set('Content-Type','application/json');

    return this._http.post<Employee>(this.url+'employee',json, {'headers': headers});
  }
}
