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

const routes:Routes=[
  {path: 'registrationForm', component:RegisterFormComponent}
];
@NgModule({
  declarations: [
    AppComponent,
    RegisterFormComponent,
    FooterComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    AppRoutingModule,
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ReaderService],
  bootstrap: [AppComponent]
})
export class AppModule { }
