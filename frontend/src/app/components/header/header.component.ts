import { Component, OnInit } from '@angular/core';
import { ReaderService } from 'src/app/service/reader.service';
import { OrderService } from 'src/app/service/order.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  number:number=0;
  constructor(public readerService:ReaderService,private orderService:OrderService) { 
  }
  elementIs:boolean=false;
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
  }
  logout(){
    this.readerService.logout();
  }
}
