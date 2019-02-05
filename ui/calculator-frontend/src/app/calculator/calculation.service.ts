import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class CalculationService {

    constructor(private http: HttpClient) {
    }

    calculate(data): Observable<number> {
        return this.http.post('/calculate', data);
    }
}
