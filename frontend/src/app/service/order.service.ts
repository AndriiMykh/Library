import { Injectable } from '@angular/core';
import { Book } from '../common/book';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  books: Book[] = [];
  totalQuantity: Subject<number> = new Subject<number>();
  alreadyAdded:boolean=false;
  constructor() { }

  addBookToOrder(newBook: Book) {
    let quantity: number = 0;
    let existingBook: Book = undefined;
    console.log("newBook" + newBook)
    if (this.books.length > 0) {
      existingBook=this.books.find(book=>book.id===newBook.id)
      console.log("existingBook"+JSON.stringify(existingBook))
      if(existingBook===undefined){
        this.books.push(newBook);
      }else
      alert("This book is already present in your order")
    }else{
      this.books.push(newBook);
    }
    for (let book of this.books)
      console.log("Added book:" + book.title);
    this.totalQuantity.next(this.books.length)
  }
  removeBook(bookToRemove: Book) {
    console.log("bookToRemove:"+bookToRemove.title)
    this.books=this.books.filter(book=>book!==bookToRemove);
    this.books.forEach(book=>{
      console.log("Filtered book:"+book.title)
    })
    this.totalQuantity.next(this.books.length)
  }
  
  removeAllBooks(){
    this.books=[]
    this.totalQuantity.next(this.books.length)
  }
  

}
