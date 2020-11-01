import { Address } from './address';
import { Book } from './book';
import { Review } from './review';
import { renderFlagCheckIfStmt } from '@angular/compiler/src/render3/view/template';

export class Reader {
    email:string;
    password:string;
    address: Address;
    books:Book[];
    review:Review[];
    constructor(email:string,password:string,address:Address){
        this.email=email;
        this.password=password;
        this.address=address
    }
}
