import {ResearchResult} from "../../models/ResearchResult";
import {Point} from "../../models/Point";
import {ApproximationManager} from "../managers/ApproximationManager";
import {LinearApproximationManager} from "../managers/LinearApproximationManager";
import {QuadraticApproximationManager} from "../managers/QuadraticApproximationManager";
import {SedateApproximationManager} from "../managers/SedateApproximationManager";
import {ExponentialApproximationManager} from "../managers/ExponentialApproximationManager";
import {LogarithmicApproximationManager} from "../managers/LogarithmicApproximationManager";
import {ApproximatingFunction} from "../../models/ApproximatingFunction";
import {FinalResult} from "../../models/FinalResult";
import {CorrelationCalculator} from "../CorrelationCalculator";
import {MathUtils} from "../utils/MathUtils";

export class FunctionResearcher {

    private static readonly FUNCTION_COLORS: string[] = ['green', 'red', 'blue', 'pink', 'yellow'];
    private static readonly MANAGERS: ApproximationManager[] = [new LinearApproximationManager(),
        new QuadraticApproximationManager(), new SedateApproximationManager(),
        new ExponentialApproximationManager(), new LogarithmicApproximationManager()];
    private static readonly FUNCTION_VIEWS: string[] = ['ax+b', 'ax^2+bx+c', 'ax^b', 'ae^(bx)', 'a*ln(x)+b'];

    research(points: Point[]): FinalResult {
        const functions: ApproximatingFunction[] =
            FunctionResearcher.MANAGERS.map((m) => m.solve(points));

        const resultFunctions: ResearchResult[] = functions.map((f, i) => {
            const s: number = points.map((point) => Math.pow(f.fnc(point.x) - point.y, 2))
                .reduce((a, b) => a + b);
            return {
                color: FunctionResearcher.FUNCTION_COLORS[i],
                view: FunctionResearcher.FUNCTION_VIEWS[i],
                fnc: f.fnc,
                a: MathUtils.roundToFixed(f.a),
                b: MathUtils.roundToFixed(f.b),
                c: f.c === undefined ? f.c : MathUtils.roundToFixed(f.c),
                deviationMeasure: MathUtils.roundToFixed(s),
                standardDeviation: MathUtils.roundToFixed(Math.sqrt(s / points.length))
            };
        });
        const correlationCoeff: number = MathUtils.roundToFixed(CorrelationCalculator.calculate(points));

        return {functions: resultFunctions, correlationCoeff: correlationCoeff};
    }
}