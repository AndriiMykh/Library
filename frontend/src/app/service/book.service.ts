import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Book } from '../common/book';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http:HttpClient,private router: Router) { }

  private baseURL:string='http://localhost:8080/api/books/';

  getAllBooks():Observable<Book[]>{
    return this.http.get<Book[]>(this.baseURL);
  }

  getAllCategories():Observable<string[]>{
    return this.http.get<string[]>(this.baseURL+"categories/")
  }

  getBooksByCategory(category:string):Observable<Book[]>{
    return this.http.get<Book[]>(this.baseURL+`categories/${category}/`)
  }

}
