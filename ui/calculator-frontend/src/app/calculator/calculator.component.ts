import {Component, OnInit} from '@angular/core';
import {CountryService} from "../country/country.service";
import {CalculationService} from "./calculation.service";
import {MatSnackBar} from "@angular/material";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CalculationData} from "./calculationData.class";


@Component({
    selector: 'app-calculator',
    templateUrl: './calculator.component.html',
    styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent implements OnInit {

    countries: Array<any>;
    calculationData = new CalculationData();

    calculationForm = new FormGroup({
        "amount": new FormControl(this.calculationData.amount, [Validators.required, Validators.pattern('/^\\d+\\.?\\d*$/')]),
        "country": new FormControl(this.calculationData.country, Validators.required)
    });

    result = new FormControl('');

    constructor(private countryService: CountryService, private calculationService: CalculationService, private snackBar: MatSnackBar) {
    }

    ngOnInit() {
        this.countryService.getAll().subscribe(data => {
            this.countries = data
        });
    }

    onSubmit() {
        this.calculationService.calculate(this.calculationForm.value).subscribe(
            data => this.result.setValue(data),
            error => this.displayErrors(error)
        );
    }

    displayErrors(response) {
        console.log(response);

        for (let validationError of response.error.errors) {
            this.snackBar.open(validationError.defaultMessage, 'Zamknij');
        }
    }

}
