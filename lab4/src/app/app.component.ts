import {Component, OnInit} from '@angular/core';
import {Chart} from 'chart.js';
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {Point} from "../models/Point";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

    private static readonly MIN_POINTS_COUNT = 12;
    private static readonly MAX_POINTS_COUNT = 20;

    chart: any;
    file: File;
    points: Point[];
    inputTypes: string[] = ['Из файла', 'Из формы'];
    selectedInputType: string = this.inputTypes[1];
    pointsCount: number = 12;
    pointsForm: FormGroup;
    errorInFile: boolean = false;

    ngOnInit(): void {
        this.initializeForm();

        Chart.pluginService.register({
            beforeInit: function (chart) {
                // We get the chart data
                const data = chart.config.data;

                // For every dataset ...
                for (let i = 0; i < data.datasets.length; i++) {

                    // For every label ...
                    for (let j = 0; j < data.labels.length; j++) {

                        // We get the dataset's function and calculate the value
                        const fct = data.datasets[i].function,
                            x = data.labels[j],
                            y = fct(x);
                        // Then we add the value to the dataset data
                        data.datasets[i].data.push(y);
                    }
                }
            }
        });

        this.chart = new Chart('canvas', {
            type: 'line',
            width: 500,
            height: 500,
        });
    }

    private initializeForm(): void {
        const xValues = new FormArray(Array(AppComponent.MIN_POINTS_COUNT).fill(0).map(() => new FormControl("", Validators.required)));
        const yValues = new FormArray(Array(AppComponent.MIN_POINTS_COUNT).fill(0).map(() => new FormControl("", Validators.required)));

        this.pointsForm = new FormGroup({
            "xValues": xValues,
            "yValues": yValues
        });
    }

    addPoint(): void {
        (<FormArray>this.pointsForm.controls["xValues"]).push(new FormControl("", Validators.required));
        (<FormArray>this.pointsForm.controls["yValues"]).push(new FormControl("", Validators.required));
    }

    removePoint(): void {
        const xValues: FormArray = <FormArray>this.pointsForm.controls["xValues"];
        const yValues: FormArray = <FormArray>this.pointsForm.controls["yValues"];
        xValues.removeAt(xValues.length - 1);
        yValues.removeAt(yValues.length - 1);
    }

    private getPointsArrayLength(): number {
        return (<FormArray>this.pointsForm.controls["xValues"]).length;
    }

    isMaximumPointsArrayLength(): boolean {
        return this.getPointsArrayLength() === AppComponent.MAX_POINTS_COUNT;
    }

    isMinimumPointsArrayLength(): boolean {
        return this.getPointsArrayLength() === AppComponent.MIN_POINTS_COUNT;
    }

    isInvalidInput(): boolean {
        return this.selectedInputType === this.inputTypes[1] && this.pointsForm.invalid ||
        this.selectedInputType === this.inputTypes[0] && this.errorInFile;
    }

    onChangeFile(event): void {
        let eventObj: MSInputMethodContext = <MSInputMethodContext> event;
        let target: HTMLInputElement = <HTMLInputElement> eventObj.target;
        let files: FileList = target.files;
        this.file = files[0];
        this.file.text().then((result) => {
            try {
                const params = JSON.parse(result);
                const xValues: number[] = params.xValues.filter((x) => !isNaN(parseFloat(x)));
                const yValues: number[] = params.yValues.filter((y) => !isNaN(parseFloat(y)));
                if(xValues.length === yValues.length && xValues.length <= AppComponent.MAX_POINTS_COUNT &&
                    xValues.length >= AppComponent.MIN_POINTS_COUNT){
                    this.points = xValues.map((x, i) => { return {x: x, y: yValues[i]} });
                } else this.errorInFile = true;
            } catch (e) {
                this.errorInFile = true;
            }
        });
    }


    onSolveClick(): void {
        if(this.selectedInputType === this.inputTypes[1]){
            const xValues: FormArray = <FormArray>this.pointsForm.controls["xValues"];
            const yValues: FormArray = <FormArray>this.pointsForm.controls["yValues"];
            this.points = Array(xValues.length).fill(0)
                .map((v, i) => { return {x: xValues.at(i).value, y: yValues.at(i).value} });
        }

        console.log(this.points);
    }
}
