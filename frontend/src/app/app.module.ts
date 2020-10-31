import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { RegisterFormComponent } from './common/register-form/register-form.component';
import { FooterComponent } from './common/footer/footer.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HeaderComponent } from './common/header/header.component';
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
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
