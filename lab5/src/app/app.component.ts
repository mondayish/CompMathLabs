import {Component, OnInit} from '@angular/core';
import {Chart} from 'chart.js';
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {Point} from "../models/Point";
import {MathFunction} from "../models/MathFunction";
import {NewtonInterpolationCalculator} from "../math/NewtonInterpolationCalculator";
import {InterpolationResult} from "../models/InterpolationResult";
import {LagrangeInterpolationCalculator} from "../math/LagrangeInterpolationCalculator";
import {MathUtils} from "../utils/MathUtils";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

    readonly MIN_POINTS_COUNT = 5;
    readonly MAX_POINTS_COUNT = 20;

    chart: any;
    result: number;
    points: Point[];
    functions: MathFunction[] = [
        {view: 'y = sin(x)', fnc: x => Math.sin(x)},
        {view: 'y = e^x', fnc: x => Math.exp(x)},
        {view: 'y = x^3', fnc: x => Math.pow(x, 3)}
    ];
    selectedFunction: MathFunction = this.functions[0];
    inputTypes: string[] = ['На основе функции', 'Таблица значений'];
    methods: string[] = ['Многочлен Лагранжа', 'Многочлен Ньютона с раделенными разностями'];
    selectedInputType: string = this.inputTypes[1];
    selectedMethod: string = this.methods[0];
    xToSolve: number;
    pointsForm: FormGroup;

    ngOnInit(): void {
        this.initializeForm();

        Chart.pluginService.register({
            beforeInit(chart): void {
                const data = chart.config.data;
                for (let i = 0; i < data.datasets.length; i++) {
                    for (let j = 0; j < data.labels.length; j++) {
                        if (data.datasets[i].data.length === 0) {
                            const fct = data.datasets[i].function;
                            const x = data.labels[j];
                            const y = fct(x, data.datasets[i].a, data.datasets[i].b, data.datasets[i].c);
                            data.datasets[i].data.push(y);
                        }
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
        const xValues = new FormArray(Array(this.MIN_POINTS_COUNT).fill(0).map(() => new FormControl("", Validators.required)));
        const yValues = new FormArray(Array(this.MIN_POINTS_COUNT).fill(0).map(() => new FormControl("", Validators.required)));

        this.pointsForm = new FormGroup({
            "xValues": xValues,
            "yValues": yValues,
            "xToSolve": new FormControl("", Validators.required)
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
        return this.getPointsArrayLength() === this.MAX_POINTS_COUNT;
    }

    isMinimumPointsArrayLength(): boolean {
        return this.getPointsArrayLength() === this.MIN_POINTS_COUNT;
    }

    isInvalidInput(): boolean {
        return this.selectedInputType === this.inputTypes[1] && this.pointsForm.invalid ||
            this.selectedInputType === this.inputTypes[0] &&
            (this.pointsForm.controls["xValues"].invalid || this.pointsForm.controls["xToSolve"].invalid);
    }

    onSolveClick(): void {
        this.collectPointsFromForm();
        LagrangeInterpolationCalculator.POINTS = this.points;
        NewtonInterpolationCalculator.POINTS = this.points;

        this.result = this.selectedMethod === this.methods[0] ?
            new LagrangeInterpolationCalculator().calculateYValue(this.xToSolve) :
            new NewtonInterpolationCalculator().calculateYValue(this.xToSolve);

        console.log(this.result);

        this.drawPlots();

        if (this.selectedInputType === this.inputTypes[0]) {

        } else {

        }
    }

    drawPlots(): void {
        const xValues = this.points.map(p => p.x);
        const yValues = this.points.map(p => p.y);

        let datasets = [{label: "Real", data: yValues, borderColor: "green", fill: false},
            this.selectedMethod === this.methods[0] ?
                {
                    label: "Lagrange",
                    data: [],
                    borderColor: "red",
                    fill: false,
                    function: new LagrangeInterpolationCalculator().calculateYValue
                } :
                {
                    label: "Newton",
                    data: [],
                    borderColor: "red",
                    fill: false,
                    function: new NewtonInterpolationCalculator().calculateYValue
                }
        ];
        datasets = this.selectedInputType === this.inputTypes[0] ? datasets.concat({
            label: this.selectedFunction.view,
            data: [],
            borderColor: "blue",
            fill: false,
            function: this.selectedFunction.fnc
        }) : datasets;

        this.chart = new Chart('canvas', {
            type: 'line',
            data: {
                labels: xValues,
                datasets: datasets
            },
            options: {
                legend: {
                    display: true
                },
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });
    }

    handleNumber(x: number): string {
        return x === undefined || isNaN(x) || x === null ? '-' : x.toString();
    }

    private collectPointsFromForm() {
        const xValues: FormArray = <FormArray>this.pointsForm.controls["xValues"];
        const yValues: FormArray = <FormArray>this.pointsForm.controls["yValues"];
        this.points = Array(xValues.length).fill(0)
            .map((v, i) => {
                return {
                    x: xValues.at(i).value, y: this.selectedInputType === this.inputTypes[1] ?
                        yValues.at(i).value : this.selectedFunction.fnc(xValues.at(i).value)
                };
            });
    }
}



















