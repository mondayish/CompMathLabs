import {Component, OnInit} from '@angular/core';
import {Chart} from 'chart.js';
import {MathFunction} from '../models/MathFunction';
import {ChordMethod} from '../math/ChordMethod';
import {ChordMethodResult} from '../models/ChordMethodResult';
import {SecantMethodResult} from '../models/SecantMethodResult';
import {SecantMethod} from '../math/SecantMethod';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

    chart: any;
    errorInFile: boolean = false;
    file: File;
    inputTypes: string[] = ['Из файла', 'Из формы'];
    functions: MathFunction[] = [
        {
            view: 'x^3 - 1.89x^2 - 2x + 1.76', fnc: x => Math.pow(x, 3) - 1.89 * x * x - 2 * x + 1.76,
            derivative: x => 3 * x * x - 3.78 * x - 2, secondDerivative: x => 3 * (2 * x - 63 / 50)
        },
        {
            view: '3sin(3x) + 1.5', fnc: x => 3 * Math.sin(3 * x) + 1.5,
            derivative: x => 9 * Math.cos(3 * x), secondDerivative: x => -27 * Math.sin(3 * x)
        },
        {
            view: 'ln(2.5x) - 3.7', fnc: x => Math.log(2.5 * x) - 3.7,
            derivative: x => 1 / x, secondDerivative: x => -1 / x / x
        },
        {
            view: '-x^5 + 1.8x^4 - 2x^3 + 3x + 6',
            fnc: x => -Math.pow(x, 5) + 1.8 * Math.pow(x, 4) - 2 * Math.pow(x, 3) + 3 * x + 6,
            derivative: x => -5 * Math.pow(x, 4) + 7.2 * Math.pow(x, 3) - 6 * x * x + 3,
            secondDerivative: x => -20 * x * (x * x - 1.08 * x + 0.6)
        }
    ];
    methods: string[] = ['Метод хорд', 'Метод секущих', 'Метод простой итерации'];
    selectedMethod: string = this.methods[0];
    selectedFunction: MathFunction = this.functions[0];
    selectedInputType: string = this.inputTypes[1];
    start: number = 0;
    finish: number = 5;
    startApproximation: number;
    fault: number = 0.01;
    calculationErrorMessage: string;

    iterationArray: number[];

    // results
    chordMethodResult: ChordMethodResult;
    secantMethodResult: SecantMethodResult;
    simpleIterationMethodResult;

    ngOnInit(): void {
        Chart.pluginService.register({
            beforeInit: function(chart) {
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

    onChangeFile(event): void {
        let eventObj: MSInputMethodContext = <MSInputMethodContext> event;
        let target: HTMLInputElement = <HTMLInputElement> eventObj.target;
        let files: FileList = target.files;
        this.file = files[0];
        this.file.text().then((result) => {
            try {
                const params = JSON.parse(result);
                this.start = parseFloat(params.start);
                this.finish = parseFloat(params.finish);
                this.startApproximation = parseFloat(params.x0);
                this.fault = parseFloat(params.fault);
                this.errorInFile = this.checkParamsNotNan();
            } catch (e) {
                this.errorInFile = true;
            }
        });
    }

    private checkParamsNotNan(): boolean {
        return isNaN(this.start) || isNaN(this.finish) || isNaN(this.fault);
    }

    calculate() {
        this.chordMethodResult = undefined;
        this.secantMethodResult = undefined;
        this.simpleIterationMethodResult = undefined;

        if (this.selectedMethod == this.methods[1]) {
            if(this.startApproximation !== this.start && this.startApproximation !== this.finish){
                this.calculationErrorMessage = "Введите начальное приближение (либо начало, либо конец промежутка)";
                return;
            } else this.calculationErrorMessage = undefined;
        }

        if (!this.errorInFile) {
            if (this.selectedMethod === this.methods[0]) {
                const methodResult: ChordMethodResult = ChordMethod.calculate(this.selectedFunction, this.start, this.finish, this.fault);
                if (methodResult.errorMessage !== undefined) {
                    this.calculationErrorMessage = methodResult.errorMessage;
                } else {
                    this.calculationErrorMessage = undefined;
                    this.iterationArray = AppComponent.getIterationArray(methodResult.functions.length);
                    this.chordMethodResult = methodResult;
                    this.drawChart(methodResult.functions);
                }
            } else if (this.selectedMethod === this.methods[1]){
                const methodResult: SecantMethodResult = SecantMethod.calculate(this.selectedFunction, this.start, this.finish,
                    this.startApproximation, this.fault);
                console.log(methodResult);
                if (methodResult.errorMessage !== undefined) {
                    this.calculationErrorMessage = methodResult.errorMessage;
                } else {
                    this.calculationErrorMessage = undefined;
                    this.iterationArray = AppComponent.getIterationArray(methodResult.functions.length);
                    this.secantMethodResult = methodResult;
                    this.drawChart(methodResult.functions);
                }
            }
        }
    }

    private drawChart(functions: ((x: number) => number)[]): void {
        const step = this.round((this.finish - this.start) / 3, 3);
        this.chart = new Chart('canvas', {
                type: 'line',
                width: 500,
                height: 500,
                data: {
                    labels: [this.start - step, this.start, this.start + step, this.start + 2 * step, this.finish, this.finish + step],
                    datasets: functions.map(f => {
                        return {
                            function: f,
                            data: [],
                            borderColor: 'rgba(0, 0, 0, 1)',
                            pointRadius: 1,
                            borderWidth: 1,
                            borderJoinStyle: 'round',
                            fill: false
                        };
                    }).concat({
                        function: this.selectedFunction.fnc,
                        data: [],
                        borderColor: 'rgba(0, 0, 0, 1)',
                        borderWidth: 1,
                        pointRadius: 1,
                        borderJoinStyle: 'round',
                        fill: false
                    })
                },
                options: {
                    legend: {
                        display: false
                    }
                }
            }
        );
    }

    private static getIterationArray(n: number): number[] {
        return Array.from(Array(n).keys());
    }

    round(x: number, pow: number) {
        return Math.round(x * Math.pow(10, pow)) / Math.pow(10, pow);
    }
}

