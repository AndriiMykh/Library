import { Reader } from './reader';
import { Review } from './review';

export class Book {
    title:string;
    descr:string;
    quantity:number;
    readers:Reader;
    reviews:Review[];
    category:string;
    id:number;
}
