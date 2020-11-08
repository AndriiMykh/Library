import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { RegisterFormComponent } from './components/register-form/register-form.component';
import { FooterComponent } from './components/footer/footer.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HeaderComponent } from './components/header/header.component';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { ReaderService } from './service/reader.service';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import {AuthGuard} from './common/auth-guard';
import { ActualOrderComponent } from './components/actual-order/actual-order.component';
import { MyBooksComponent } from './components/my-books/my-books.component';
import { BookReviewComponent } from './components/book-review/book-review.component';
import { CommonModule } from "@angular/common";

const routes:Routes=[

  {path: '', component:LoginComponent},
  {path: 'actualOrder', component:ActualOrderComponent, canActivate: [AuthGuard]},
  {path: 'registrationForm', component:RegisterFormComponent},
  {path: 'login', component:LoginComponent},
  {path: 'allBooks', component:WelcomeComponent,canActivate: [AuthGuard]},
  {path: 'categories/:category', component:WelcomeComponent,canActivate: [AuthGuard]},
  {path: 'searchByKeyword/:keyword', component:WelcomeComponent,canActivate: [AuthGuard]},
  {path: 'myBooks/:id', component:MyBooksComponent,canActivate: [AuthGuard]},
  {path: 'bookReviews/:id', component:BookReviewComponent,canActivate: [AuthGuard]},

];
@NgModule({
  declarations: [
    BookReviewComponent,
    AppComponent,
    RegisterFormComponent,
    FooterComponent,
    HeaderComponent,
    LoginComponent,
    WelcomeComponent,
    ActualOrderComponent,
    MyBooksComponent,
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    AppRoutingModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [ReaderService],
  bootstrap: [AppComponent]
})
export class AppModule { }
