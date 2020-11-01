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
  constructor(private readerService:ReaderService,private router: Router) { }

  ngOnInit(): void {
  }
  login(){
    console.log(this.email)
    console.log(this.password)
    this.readerService.getReaderByEmailAndPassword(this.email,this.password).subscribe(
      data=>{
        console.log(data)
        this.router.navigate(['/welcome',data.id])
      }
    )
  }

}
