import {ApproximationService} from "./ApproximationService";
import {Point} from "../models/Point";
import {ApproximatingFunction} from "../models/ApproximatingFunction";
import {PointUtils} from "./utils/PointUtils";
import {MatrixUtils} from "./utils/MatrixUtils";

export class ExponentialApproximationService implements ApproximationService {

    solve(points: Point[]): ApproximatingFunction {
        const replacedPoints: Point[] = points.map((point)=>{return {x: point.x, y: Math.log(point.y)}});
        const characteristics = PointUtils.calculatePointsCharacteristics(replacedPoints);
        const coeffs: number[] = MatrixUtils.solveSLAU([[characteristics.sxx, characteristics.sx],
            [characteristics.sx, characteristics.n]], [characteristics.sxy, characteristics.sy]);
        const a: number = Math.exp(coeffs[1]), b: number = coeffs[0];

        return {fnc: (x: number) => a*Math.exp(b*x), a: a, b: b};
    }
}