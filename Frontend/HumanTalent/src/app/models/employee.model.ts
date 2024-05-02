/**
 * Estructura del empleado, se utiliza para facilitar la manipulacion de los datos con el API.
 */
export class Employee {
    id?: any;
    type?: string = "employee";
    firstName?: string;
    otherNames?: string;
    firstLastName?: string;
    secondLastName?: string;
    country?: string;
    idType?: string;
    identificationNum?: string;
    email?: string;
    entryDate?: string;
    workArea?: string;
    state?: boolean;
    regDateTime?: string;
}
