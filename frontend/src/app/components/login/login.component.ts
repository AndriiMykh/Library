import { Component, OnInit } from '@angular/core';
import { ReaderService } from 'src/app/service/reader.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email:string='';
  password:string='';
  wrongData:boolean = false;
  constructor(private readerService:ReaderService,private router: Router) { }

  ngOnInit(): void {
    this.readerService.wrongPasswordOrEmail.subscribe(
      res=>{
        this.wrongData=res
      }
    )
  }
  login(){
    console.log(this.email)
    console.log(this.password)
      this.readerService.getReaderByEmailAndPassword(this.email,this.password)

    }
  

}
