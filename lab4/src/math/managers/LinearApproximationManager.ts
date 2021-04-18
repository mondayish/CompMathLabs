import {ApproximationManager} from "./ApproximationManager";
import {ApproximatingFunction} from "../../models/ApproximatingFunction";
import {Point} from "../../models/Point";
import {MatrixUtils} from "../utils/MatrixUtils";
import {PointUtils} from "../utils/PointUtils";

export class LinearApproximationManager implements ApproximationManager {

    solve(points: Point[]): ApproximatingFunction {
        const characteristics = PointUtils.calculatePointsCharacteristics(points);
        const coeffs: number[] = MatrixUtils.solveLinear(characteristics);
        const a: number = coeffs[0], b: number = coeffs[1];

        return {fnc: (x: number) => a*x + b, a: a, b: b};
    }
}