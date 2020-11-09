import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/common/book';
import { BookService } from '../../service/book.service';
import { OrderService } from '../../service/order.service'
import { ActivatedRoute, Router } from '@angular/router';
import { ReaderService } from 'src/app/service/reader.service';
import { Input, ViewChild } from '@angular/core';
@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  adminButtons: boolean = false;
  constructor(private bookService: BookService, private orderService: OrderService, private readerService: ReaderService, private route: ActivatedRoute,private router:Router) { }
  books: Book[];
  ngOnInit(): void {
    this.adminButtons = this.readerService.loggedInAdmin
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
    } else if (this.route.snapshot.paramMap.has('keyword')) {
      const keyword: string = this.route.snapshot.paramMap.get('keyword')
      this.bookService.getBooksByKeyword(keyword).subscribe(
        data => {
          this.books = data;
          console.log(data)
        })
    }
    else {
      this.bookService.getAllBooks().subscribe(
        data => {
          this.books = data;
          console.log(data)
        })
    }
  }

  increaseQuantityBook(book: Book) {
    book.quantity++
    console.log("quantity:" + book.quantity)
    this.bookService.updateBook(book).subscribe(
      data => {
        console.log("sucessfully")
      }, error => {
        console.error("Error happened")
      }
    )
  }

  decreaseQuantityBook(book: Book) {
    if (book.quantity > 0) {
      book.quantity--;
      console.log("quantity:" + book.quantity)
      this.bookService.updateBook(book).subscribe(
        data => {
          console.log("sucessfully")
        }, error => {
          console.error("Error happened")
        }
      )
    }else{
      alert('Book quantity can not be less than 0');
    }
  }

  showReviews(book){
    this.router.navigate(["/bookReviews",book.id])
  }
}
