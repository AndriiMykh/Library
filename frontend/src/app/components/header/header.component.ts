import { Component, OnInit } from '@angular/core';
import { ReaderService } from 'src/app/service/reader.service';
import { OrderService } from 'src/app/service/order.service';
import { BookService } from 'src/app/service/book.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  number:number=0;
  categories:string[]=[];
  actualUrl:string;
  keyword:string='';
  constructor(public readerService:ReaderService,
    private orderService:OrderService,
    private bookService:BookService,
    private router:Router
    ) { 
  }
  elementIs:boolean=false;
  // && ===
  ngOnInit(): void {
    this.readerService.LoggedIn.subscribe(
      data=>{
        this.elementIs=data
        console.log("button"+this.elementIs)
      }
    )
    this.orderService.totalQuantity.subscribe(
      data=>{
        this.number=data
      }
    )
    this.bookService.getAllCategories().subscribe(
      data=>{
      this.categories=data
      }
    )
    this.categories.forEach(cat=>{
      console.log(cat)
    })
  }
  logout(){
    this.readerService.logout();
  }
  chooseCategory(category:string){
    this.router.navigate(['allBooks/',category.toLowerCase()])
  }
  search(){
    console.log()
    this.router.navigate(['searchByKeyword/',this.keyword])
  }
}
