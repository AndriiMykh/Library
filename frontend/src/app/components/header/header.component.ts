import { Component, OnInit } from '@angular/core';
import { ReaderService } from 'src/app/service/reader.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public readerService:ReaderService) { 
  }
  elementIs:boolean=false;
  ngOnInit(): void {
    this.readerService.LoggedIn.subscribe(
      data=>{
        this.elementIs=data
        console.log("button"+this.elementIs)
      }
    )
  }
  logout(){
    this.readerService.logout();
  }
}
