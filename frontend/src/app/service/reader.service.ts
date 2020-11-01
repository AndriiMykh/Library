import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Reader } from '../common/reader';

@Injectable({
  providedIn: 'root'
})
export class ReaderService {

  constructor(private http:HttpClient) { }
  private baseURL:string='http://localhost:8080/api/readers/';
  postReader(reader:Reader){
    return this.http.post<Reader>(this.baseURL,reader)
  }
  getReaderByEmailAndPassword(email:string, password:string){
    let params = new HttpParams().set("email",email).set("password", password);
    return this.http.get<Reader>(this.baseURL+"login",{params})
  }
}
