import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import {Reader} from '../../common/reader';
import { Address } from 'src/app/common/address';
@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  checkoutFormGroup: FormGroup;
  reader:Reader;
  newReader:Reader;
  address:Address;
  emailPattern= "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
  NumberPattern='^\\d+$';
  zipCodePattern='[0-9]{2}-[0-9]{3}';
  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.checkoutFormGroup= this.formBuilder.group({
      reader: this.formBuilder.group({
        email:new FormControl('', [Validators.required,Validators.pattern(this.emailPattern)]),
        password:new FormControl('',[Validators.required, Validators.minLength(4)])
      }),
      address :this.formBuilder.group({
        flatNumber:new FormControl('',[Validators.required,Validators.pattern(this.NumberPattern)]),
        houseNumber:new FormControl('',[Validators.required]),
        street:new FormControl('',[Validators.required]),
        city:new FormControl('',[Validators.required, Validators.minLength(3)]),
        zipCode:new FormControl('',[Validators.required,Validators.pattern(this.zipCodePattern)])
      })
    })
  }
  onSubmit(){
    if(this.checkoutFormGroup.invalid){
      this.checkoutFormGroup.markAllAsTouched();
    }
    console.log(this.checkoutFormGroup.get('reader').value)
    console.log(this.checkoutFormGroup.get('address').value)
  }
  get email(){return this.checkoutFormGroup.get('reader.email')}
  get password(){return this.checkoutFormGroup.get('reader.password')}
  get flatNumber(){return this.checkoutFormGroup.get('address.flatNumber')}
  get houseNumber(){return this.checkoutFormGroup.get('address.houseNumber')}
  get street(){return this.checkoutFormGroup.get('address.street')}
  get city(){return this.checkoutFormGroup.get('address.city')}
  get zipCode(){return this.checkoutFormGroup.get('address.zipCode')}

}
