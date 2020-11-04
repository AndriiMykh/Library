import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/common/book';
import {BookService} from '../../service/book.service'
@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor(private bookService:BookService) { }
  books:Book[];
  ngOnInit(): void {
    this.bookService.getAllBooks().subscribe(
      data=>{
        this.books=data;
        console.log(data)
      }
    )
  }

}
