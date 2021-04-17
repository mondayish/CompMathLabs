import {ApproximationManager} from "./ApproximationManager";
import {Point} from "../../models/Point";
import {ApproximatingFunction} from "../../models/ApproximatingFunction";
import {PointUtils} from "../utils/PointUtils";
import {MatrixUtils} from "../utils/MatrixUtils";

export class LogarithmicApproximationManager implements ApproximationManager {

    solve(points: Point[]): ApproximatingFunction {
        const replacedPoints: Point[] = points.map((point)=>{return {x: Math.log(point.x), y: point.y}});
        const characteristics = PointUtils.calculatePointsCharacteristics(replacedPoints);
        const coeffs: number[] = MatrixUtils.solveSLAU([[characteristics.sxx, characteristics.sx],
            [characteristics.sx, characteristics.n]], [characteristics.sxy, characteristics.sy]);
        const a: number = Math.exp(coeffs[1]), b: number = coeffs[0];

        return {fnc: (x: number) => a*Math.log(x) + b, a: a, b: b};
    }
}