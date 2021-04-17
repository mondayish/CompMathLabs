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

    private static readonly MANAGERS: ApproximationManager[] = [new LinearApproximationManager(),
        new QuadraticApproximationManager(), new SedateApproximationManager(),
        new ExponentialApproximationManager(), new LogarithmicApproximationManager()];
    private static readonly FUNCTION_VIEWS: string[] = ['&#632;(x) = ax + b', '&#632;(x) = ax^2 + bx + c',
        '&#632;(x) = ax^b', '&#632;(x) = ae^(bx)', '&#632;(x) = a*ln(x) + b'];

    research(points: Point[]): ResearchResult[] {
        const functions: ApproximatingFunction[] =
            FunctionResearcher.MANAGERS.map((m) => m.solve(points));

        return functions.map((f, i) => {
            const s: number = points.map((point) => Math.pow(f.fnc(point.x) - point.y, 2))
                .reduce((a, b) => a + b);
            return {
                view: FunctionResearcher.FUNCTION_VIEWS[i],
                a: f.a,
                b: f.b,
                c: f.c,
                deviationMeasure: s,
                standardDeviation: Math.sqrt(s / points.length)
            };
        });
    }
}