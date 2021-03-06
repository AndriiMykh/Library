import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Reader } from '../common/reader';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { Book } from '../common/book';

@Injectable({
  providedIn: 'root'
})
export class ReaderService {

  constructor(private http:HttpClient,private router: Router) { 

  }
  private baseURL:string='http://localhost:8080/api/readers/';
  public LoggedIn = new BehaviorSubject<boolean>(false);
  public wrongPasswordOrEmail = new BehaviorSubject<boolean>(false);

  postReader(reader:Reader){
    return this.http.post<Reader>(this.baseURL,reader)
  }

  getReaderByEmailAndPassword(email:string, password:string){
    let params = new HttpParams().set("email",email).set("password", password);
    return this.http.get<Reader>(this.baseURL+"login",{params}).subscribe(
        data=>{
          sessionStorage.setItem('authenticatedUser', data.id.toString());
          this.router.navigate(['/allBooks'])
          this.LoggedIn.next(true); 
          this.wrongPasswordOrEmail.next(false)
        },
        error=>{
          console.error("Wrong data")
          this.wrongPasswordOrEmail.next(true)
        }
    )
  }

  logout() {  
    sessionStorage.removeItem('authenticatedUser');
    this.router.navigate(['login'])  
    this.LoggedIn.next(false);
  }  

  public get loggedInAdmin(): boolean {  
    return (sessionStorage.getItem('authenticatedUser') === '9999');
  } 

  public get loggedIn(): boolean {  
    return (sessionStorage.getItem('authenticatedUser') !== null);  
  }  


  addBooksToReader( books:Book[]){
    const id=sessionStorage.getItem('authenticatedUser');
    return this.http.post(this.baseURL+`addBooksToReader/${id}`,books)
  }

  getBooks(){
    const id=sessionStorage.getItem('authenticatedUser');
    return this.http.get<Book[]>(this.baseURL+`getBooks/${id}`);
  }

  takeBookBack(book:Book){
    const id=sessionStorage.getItem('authenticatedUser');
    return this.http.post(this.baseURL+`deleteBooksFromReader/${id}`, book);
  }
}
