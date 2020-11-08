import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookService } from 'src/app/service/book.service';
import { Review } from 'src/app/common/review';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-book-review',
  templateUrl: './book-review.component.html',
  styleUrls: ['./book-review.component.css']
})
export class BookReviewComponent  {

  reviews:Review[]=[];
  textOfReview:string='';
  constructor(private route: ActivatedRoute,private bookService:BookService) { }

  ngOnInit(): void {
    this.getReviews()
  }
  getReviews(){
    let id:number;
      id = +this.route.snapshot.paramMap.get('id')
    this.bookService.getReviews(id).subscribe(
      data=>{
        this.reviews=data
      }
    );
  }
  LeaveAReview(){
    let bookId:number;
    bookId = +this.route.snapshot.paramMap.get('id')
    this.bookService.addReview(this.textOfReview,bookId).subscribe(
      data=>{
        this.getReviews()
        this.textOfReview=''
      },error=>{
        console.error('error')
      }
    )
  }
}
