import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/service/order.service';
import { Book } from 'src/app/common/book';
import { ReaderService } from 'src/app/service/reader.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-actual-order',
  templateUrl: './actual-order.component.html',
  styleUrls: ['./actual-order.component.css']
})
export class ActualOrderComponent implements OnInit {

  constructor(private orderService:OrderService,private readerService:ReaderService, private router:Router) { }
  orderedBooks:Book[]=[];
  ngOnInit(): void {
   this.orderedBooks=this.orderService.books
  }
  removeBookFromOrder(book:Book){
    this.orderService.removeBook(book);
    this.orderedBooks=this.orderService.books
  }
  confirmOrder(){
    this.readerService.addBooksToReader(this.orderedBooks).subscribe(
      data=>{
        console.log("Added successfully")
        this.orderService.removeAllBooks();
        this.router.navigate(['/allBooks'])
      },error=>{
        alert(error.error.message)
      }
    );
  }
}
