import {ResearchResult} from "../../models/ResearchResult";
import {Point} from "../../models/Point";
import {ApproximationManager} from "../managers/ApproximationManager";
import {LinearApproximationManager} from "../managers/LinearApproximationManager";
import {QuadraticApproximationManager} from "../managers/QuadraticApproximationManager";
import {SedateApproximationManager} from "../managers/SedateApproximationManager";
import {ExponentialApproximationManager} from "../managers/ExponentialApproximationManager";
import {LogarithmicApproximationManager} from "../managers/LogarithmicApproximationManager";
import {ApproximatingFunction} from "../../models/ApproximatingFunction";

export class FunctionResearcher {

    private static readonly PLACES_TO_ROUND: number = 4;
    private static readonly FUNCTION_COLORS: string[] = ['green', 'red', 'blue', 'pink', 'yellow'];
    private static readonly MANAGERS: ApproximationManager[] = [new LinearApproximationManager(),
        new QuadraticApproximationManager(), new SedateApproximationManager(),
        new ExponentialApproximationManager(), new LogarithmicApproximationManager()];
    private static readonly FUNCTION_VIEWS: string[] = ['ax + b', 'ax^2 + bx + c', 'ax^b', 'ae^(bx)', 'a*ln(x) + b'];

    research(points: Point[]): ResearchResult[] {
        const functions: ApproximatingFunction[] =
            FunctionResearcher.MANAGERS.map((m) => m.solve(points));

        return functions.map((f, i) => {
            const s: number = points.map((point) => Math.pow(f.fnc(point.x) - point.y, 2))
                .reduce((a, b) => a + b);
            return {
                color: FunctionResearcher.FUNCTION_COLORS[i],
                view: FunctionResearcher.FUNCTION_VIEWS[i],
                a: FunctionResearcher.roundToFixed(f.a),
                b: FunctionResearcher.roundToFixed(f.b),
                c: f.c === undefined ? f.c : FunctionResearcher.roundToFixed(f.c),
                deviationMeasure: FunctionResearcher.roundToFixed(s),
                standardDeviation: FunctionResearcher.roundToFixed(Math.sqrt(s / points.length))
            };
        });
    }

    private static roundToFixed(x: number): number {
        return Math.round(x * Math.pow(10, this.PLACES_TO_ROUND)) / Math.pow(10, this.PLACES_TO_ROUND);
    }
}