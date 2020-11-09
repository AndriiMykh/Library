import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Book } from '../common/book';
import { Observable } from 'rxjs';
import { Review } from '../common/review';

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

  getBooksByKeyword(keyword:string){
    return this.http.get<Book[]>(this.baseURL+`searchByKeyword/${keyword}/`)
  }

  getReviews(id:number):Observable<Review[]>{
    return this.http.get<Review[]>(this.baseURL+`/getReviews/${id}`);
  }

  addReview(review:string,bookId:number){
    let id = sessionStorage.getItem('authenticatedUser');
    const params = new HttpParams()
      .set('id', id)
      .set('review', review);
    return this.http.post(this.baseURL+`/addReviewToBook/${bookId}`,params)
  }

  updateBook(book:Book){
    return this.http.put(this.baseURL+`${book.id}`,book)
  }



}
