import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/service/order.service';
import { Book } from 'src/app/common/book';

@Component({
  selector: 'app-actual-order',
  templateUrl: './actual-order.component.html',
  styleUrls: ['./actual-order.component.css']
})
export class ActualOrderComponent implements OnInit {

  constructor(private orderService:OrderService) { }
  orderedBooks:Book[]=[];
  ngOnInit(): void {
   this.orderedBooks=this.orderService.books
  }
  removeBookFromOrder(book:Book){
    this.orderService.removeBook(book);
    this.orderedBooks=this.orderService.books
  }
}
