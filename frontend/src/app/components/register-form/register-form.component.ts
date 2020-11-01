import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import {Reader} from '../../common/reader';
import { Address } from 'src/app/common/address';
import {NotWhitspacesValidator} from '../../common/not-whitspaces-validator';
import{ReaderService} from '../../service/reader.service';
@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {
  constructor(private formBuilder: FormBuilder,private readerService:ReaderService) { }
  checkoutFormGroup: FormGroup;
  reader:Reader;
  newReader:Reader;
  address:Address;
  emailPattern= "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  NumberPattern='^\\d+$';
  zipCodePattern='[0-9]{2}-[0-9]{3}';
  ngOnInit(): void {
    this.checkoutFormGroup= this.formBuilder.group({
      reader: this.formBuilder.group({
        email:new FormControl('', [Validators.required,Validators.pattern(this.emailPattern)]),
        password:new FormControl('',[Validators.required, Validators.minLength(4),NotWhitspacesValidator.notWhitespaces])
      }),
      address :this.formBuilder.group({
        flatNumber:new FormControl('',[Validators.required,Validators.pattern(this.NumberPattern)]),
        houseNumber:new FormControl('',[Validators.required,NotWhitspacesValidator.notWhitespaces]),
        street:new FormControl('',[Validators.required,NotWhitspacesValidator.notWhitespaces]),
        city:new FormControl('',[Validators.required, Validators.minLength(3),NotWhitspacesValidator.notWhitespaces]),
        zipCode:new FormControl('',[Validators.required,Validators.pattern(this.zipCodePattern)])
      })
    })
  }
  onSubmit(){
    if(this.checkoutFormGroup.invalid){
      this.checkoutFormGroup.markAllAsTouched();
    }
    this.newReader=this.checkoutFormGroup.get('reader').value
    this.newReader.address=this.checkoutFormGroup.get('address').value
    console.log(this.newReader)
    this.readerService.postReader(this.newReader).subscribe(
      data=>{
        console.log("created")
      },
      error=>{
        alert(error.error.message)
      }
    );

  }
  get email(){return this.checkoutFormGroup.get('reader.email')}
  get password(){return this.checkoutFormGroup.get('reader.password')}
  get flatNumber(){return this.checkoutFormGroup.get('address.flatNumber')}
  get houseNumber(){return this.checkoutFormGroup.get('address.houseNumber')}
  get street(){return this.checkoutFormGroup.get('address.street')}
  get city(){return this.checkoutFormGroup.get('address.city')}
  get zipCode(){return this.checkoutFormGroup.get('address.zipCode')}

}
