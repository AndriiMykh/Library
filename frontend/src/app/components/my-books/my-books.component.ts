import { Component, OnInit } from '@angular/core';
import { ReaderService } from 'src/app/service/reader.service';
import { Book } from 'src/app/common/book';
import { Router } from '@angular/router';

@Component({
  selector: 'app-my-books',
  templateUrl: './my-books.component.html',
  styleUrls: ['./my-books.component.css']
})
export class MyBooksComponent implements OnInit {

  constructor(private readerService:ReaderService, private router: Router) { }
  books:Book[]=[]
  ngOnInit(): void {
    this.getBooks()
  }
  getBooks(){
    this.readerService.getBooks().subscribe(
      data=>{
        this.books=data
      }
    )
  }
  takeBookBack(book:Book){
    this.readerService.takeBookBack(book).subscribe(
      data=>{
        console.log("successfully")
        this.getBooks()
      },error=>{
        console.log("Error happened")
      }
    )
  }

  leaveReview(id:number){
    this.router.navigate(['/bookReviews', id])
  }

}
