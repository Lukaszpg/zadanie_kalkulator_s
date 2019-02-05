import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class CalculationService {

    constructor(private http: HttpClient) {
    }

    calculate(data): Observable<object> {
        return this.http.post('http://localhost:8080/calculate', this.prepareData(data));
    }

    prepareData(data) {
        return {
            "country": data.country.countryEnum,
            "amount": data.amount
        }
    }
}
