import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({
    providedIn: 'root'
  })
export class AdminGuard {
    constructor(private _router: Router) { }  
    canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {  
        if (sessionStorage.getItem('authenticatedUser') ==="9999") {  
            return true;  
        }  
        this._router.navigate(['/login']);
        console.log("you are'nt an admin")  
        return false;  
    }  
}
