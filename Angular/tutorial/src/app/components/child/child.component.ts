import { Component, EventEmitter, input, Input, output, Output } from '@angular/core';

@Component({
  selector: 'app-child',
  standalone: true,
  imports: [],
  templateUrl: './child.component.html',
  styleUrl: './child.component.css'
})
export class ChildComponent {

  //////// Comunicación padre -> hijo ////////
  // Usando decorador
  // @Input()
  // msg: string = "";

  // Usando signals
  msg = input<string>("");

  // @Input()
  // person: any;

  person = input<any>();

  //////// Comunicación hijo -> padre ////////

  // Evento
  // Usando decorador
  // @Output()
  // loginEvent: EventEmitter<string> = new EventEmitter<string>;

  // Evento
  // Usando signals
  loginEvent = output<string>();
  
  userName: string = "Rafael";

  handleLogin(){
    // Emite el evento
    this.loginEvent.emit(this.userName);
  }

}
