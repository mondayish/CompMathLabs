import {ApproximationService} from "./ApproximationService";
import {ApproximatingFunction} from "../models/ApproximatingFunction";
import {Point} from "../models/Point";
import {MatrixUtils} from "./utils/MatrixUtils";
import {PointUtils} from "./utils/PointUtils";

export class LinearApproximationService implements ApproximationService {

    solve(points: Point[]): ApproximatingFunction {
        const characteristics = PointUtils.calculatePointsCharacteristics(points);
        const coeffs: number[] = MatrixUtils.solveSLAU([[characteristics.sxx, characteristics.sx],
            [characteristics.sx, characteristics.n]], [characteristics.sxy, characteristics.sy]);
        const a: number = coeffs[0], b: number = coeffs[1];

        return {fnc: (x: number) => a*x + b, a: a, b: b};
    }
}