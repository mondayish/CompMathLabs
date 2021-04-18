import {ApproximationManager} from "./ApproximationManager";
import {Point} from "../../models/Point";
import {ApproximatingFunction} from "../../models/ApproximatingFunction";
import {PointUtils} from "../utils/PointUtils";
import {MatrixUtils} from "../utils/MatrixUtils";

export class LogarithmicApproximationManager implements ApproximationManager {

    solve(points: Point[]): ApproximatingFunction {
        const replacedPoints: Point[] = points.map((point)=>{return {x: Math.log(point.x), y: point.y}});
        const characteristics = PointUtils.calculatePointsCharacteristics(replacedPoints);
        const coeffs: number[] = MatrixUtils.solveLinear(characteristics);
        const a: number = coeffs[0], b: number = coeffs[1];

        return {fnc: (x: number) => a*Math.log(x) + b, a: a, b: b};
    }
}