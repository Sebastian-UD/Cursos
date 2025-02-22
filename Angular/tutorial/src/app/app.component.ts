import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/header/header.component'
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ChildComponent } from "./components/child/child.component";
import Product from './models/Product';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgClass, ReactiveFormsModule, HeaderComponent, FormsModule, ChildComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  username: string;
  number: number;
  condition: boolean;
  condition2: string;
  movies: string[];
  product: Product;
  isDark: boolean;
  movieForm: FormGroup;
  name: FormControl;
  duration: FormControl;
  director: FormControl;

  constructor(){
    this.username = 'Sebastian'; 
    this.number = 0;
    this.condition = false;
    this.condition2 = "dasdasds";
    this.movies = ['Pelicula1', 'Pelicula2', 'Pelicula3']
    this.product = {
      name: "Computer",
      price: 1000,
      isForSale: true
    }
    this.isDark = false;

    this.name = new FormControl("");
    this.duration = new FormControl("");
    this.director = new FormControl("");
    this.movieForm = new FormGroup({
      name: this.name,
      duration: this.duration,
      director: this.director
    });
  }

  animals: any = [
    {
        id: 1,
        name: "dog",
        img: "https://www.clinicaveterinariaaguilar.es/wp-content/uploads/2020/01/cachorro.jpg"
    },
    {
        id: 2,
        name: "cat",
        img: "https://static.nationalgeographicla.com/files/styles/image_3200/public/nationalgeographic_1468962.webp?w=1023&h=576"
    },
    {
        id: 3,
        name: "bird",
        img: "https://www.arteescuela.com/wp-content/uploads/2022/10/como-dibujar-un-pajaro-paso-a-paso.jpg"
    }

  ];

  text: string = "Info desde el componente padre"

  person: any = {
    nombre: "Pedro",
    apellido: "Jimenez"
  };

  name2: string = "";

  setName(event: string){
    this.name2 = event;
  }

  addOne(){
    this.number++;
  }

  toggleDark(){
    this.isDark = !this.isDark;
  }

  handleSubmit(): void{
    console.log(this.movieForm)
  }

}
