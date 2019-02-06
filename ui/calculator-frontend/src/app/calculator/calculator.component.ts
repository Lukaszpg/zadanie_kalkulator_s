import {Component, OnInit} from '@angular/core';
import {CountryService} from "../country/country.service";
import {CalculationService} from "./calculation.service";
import {MatSnackBar} from "@angular/material";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CalculationData} from "./calculationData.class";
import {TranslateService} from "@ngx-translate/core";


@Component({
    selector: 'app-calculator',
    templateUrl: './calculator.component.html',
    styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent implements OnInit {

    tax: string;
    fixedCost: number;
    currencyCode: string;
    countries: Array<any>;
    calculationData = new CalculationData();

    calculationForm = new FormGroup({
        "amount": new FormControl(this.calculationData.amount, [Validators.required, Validators.pattern('^[0-9]+(\\.[0-9]{1,2})?$')]),
        "country": new FormControl(this.calculationData.country, Validators.required)
    });

    result = new FormControl('');

    constructor(private countryService: CountryService, private calculationService: CalculationService, private snackBar: MatSnackBar, private translateService: TranslateService) {
    }

    ngOnInit() {
        this.countryService.getAll().subscribe(
            data => this.countries = data,
            error => this.displayCountryGetError()
        );
    }

    onSubmit() {
        this.calculationService.calculate(this.calculationForm.value).subscribe(
            data => this.result.setValue(data["result"]),
            error => this.displayErrors(error)
        );
    }

    onCountryChange(selectedEvent) {
        this.currencyCode = selectedEvent.value.currencyCode
        this.fixedCost = selectedEvent.value.fixedCost;
        this.tax = this.parseTaxToPercentNotation(selectedEvent.value.tax);
    }

    parseTaxToPercentNotation(tax) {
        return String(tax * 100 + "%");
    }

    displayErrors(response) {
        if (response.error.errors) {
            this.displayMultipleErrors(response);
        } else if (response.error.message) {
            this.displaySnackBarNotification(response.error.message);
        }
    }

    displayMultipleErrors(response) {
        for (let validationError of response.error.errors) {
            this.displaySnackBarNotification(validationError.defaultMessage);
        }
    }

    displayCountryGetError() {
        this.translateService.get('country.get.error').subscribe(value => {
            this.displaySnackBarNotification(value);
        })
    }

    displaySnackBarNotification(message) {
        this.translateService.get('close').subscribe(value => {
            this.snackBar.open(message, value);
        })
    }

}
