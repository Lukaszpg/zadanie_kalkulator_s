import {Component} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {Title} from "@angular/platform-browser";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    constructor(private translate: TranslateService, private title: Title) {
        translate.setDefaultLang('pl');
        translate.use('pl');

        this.translate.get('app.title').subscribe(value => {
            title.setTitle(value);
        });
    }
}
