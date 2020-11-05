import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/common/book';
import {BookService} from '../../service/book.service';
import{OrderService} from '../../service/order.service'
@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(private bookService:BookService, private orderService:OrderService) { }
  books:Book[];
  ngOnInit(): void {
    this.bookService.getAllBooks().subscribe(
      data=>{
        this.books=data;
        console.log(data)
      }
    )
  }
  orderTheBook(book:Book){
    this.orderService.addBookToOrder(book);
    console.log("orderTheBook:"+book)
  }

}
