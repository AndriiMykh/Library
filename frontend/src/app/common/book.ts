import { Reader } from './reader';
import { Review } from './review';

export class Book {
    title:string;
    descr:string;
    quantity:number;
    readers:Reader[];
    reviews:Review[];
    category:Category;
    id:number;
}
enum Category{
    FANTASY,
	ADVENTURE,
	ROMANCE,
	CONTENMPORARY,
	DYSTOPIAN,
	MYSTERY,
	HORROR,
	THRILLER,
	PARANORMAL,
	HISTORICAL,
	MEMOIR,
	COOKING,
	ART,
	SELFHELP,
	DEVELOPMENT,
	MOTIVATIONAL,
	HEALTH,
	TRAVEL,
	HUMOR
}