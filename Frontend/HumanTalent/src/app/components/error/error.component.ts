import { Component, input } from '@angular/core';

/**
 * Componente para indicar algun error en el proyecto.
 */
@Component({
  selector: 'app-error',
  standalone: true,
  imports: [],
  templateUrl: './error.component.html',
  styleUrl: './error.component.css'
})
export class ErrorComponent {
  /**
   * Mensaje a mostrar al usuario.
   */
  errorMessage!: string;
}
