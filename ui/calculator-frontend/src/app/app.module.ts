import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";

import {AppComponent} from './app.component';
import {APIInterceptor} from "./http/http.interceptor";
import {CalculatorComponent} from './calculator/calculator.component';
import {
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule,
    MatSnackBarModule,
    MatToolbarModule
} from "@angular/material";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
    declarations: [
        AppComponent,
        CalculatorComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        MatToolbarModule,
        MatFormFieldModule,
        MatSelectModule,
        MatOptionModule,
        MatInputModule,
        MatButtonModule,
        MatSnackBarModule,
        ReactiveFormsModule,
        BrowserAnimationsModule
    ],
    providers: [{
        provide: HTTP_INTERCEPTORS,
        useClass: APIInterceptor,
        multi: true,
    }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
