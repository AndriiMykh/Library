import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/common/book';
import { BookService } from '../../service/book.service';
import { OrderService } from '../../service/order.service'
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(private bookService: BookService, private orderService: OrderService, private route: ActivatedRoute) { }
  books: Book[];
  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.getBooks();
    });
  }
  orderTheBook(book: Book) {
    this.orderService.addBookToOrder(book);
    console.log("orderTheBook:" + book)
  }
  getBooks() {
    if (this.route.snapshot.paramMap.has('category')) {
      const category: string = this.route.snapshot.paramMap.get('category')
      this.bookService.getBooksByCategory(category).subscribe(
        data => {
          this.books = data;
          console.log(data)
        })
      }else{
        this.bookService.getAllBooks().subscribe(
          data => {
            this.books = data;
            console.log(data)
          })
      }
  }

}
