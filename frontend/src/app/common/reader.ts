import { Address } from './address';
import { Book } from './book';
import { Review } from './review';

export class Reader {
    email:string;
    password:string;
    private _adress: Address;
    public get adress(): Address {
        return this._adress;
    }
    public set adress(value: Address) {
        this._adress = value;
    }
    books:Book[];
    review:Review[];
}
