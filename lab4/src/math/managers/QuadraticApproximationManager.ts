import {ApproximationManager} from "./ApproximationManager";
import {ApproximatingFunction} from "../../models/ApproximatingFunction";
import {Point} from "../../models/Point";
import {PointUtils} from "../utils/PointUtils";
import {MatrixUtils} from "../utils/MatrixUtils";

export class QuadraticApproximationManager implements ApproximationManager {

    solve(points: Point[]): ApproximatingFunction {
        const characteristics = PointUtils.calculatePointsCharacteristics(points);
        const coeffs: number[] = MatrixUtils.solveQuadratic(characteristics);
        const a0: number = coeffs[0], a1: number = coeffs[1], a2: number = coeffs[2];

        return {fnc: (x: number) => a0 + a1 * x + a2 * x * x, a: a2, b: a1, c: a0};
    }
}