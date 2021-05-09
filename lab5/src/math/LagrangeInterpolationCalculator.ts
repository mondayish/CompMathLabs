import {InterpolationCalculator} from "./InterpolationCalculator";
import {Point} from "../models/Point";
import {InterpolationResult} from "../models/InterpolationResult";

export class LagrangeInterpolationCalculator implements InterpolationCalculator {

    calculate(points: Point[], x: number): InterpolationResult {
        const lCoeffs: number[] = points.map((p, i) => {
            const filteredPoints: Point[] = points.filter((point, index) => index != i);
            return p.y * filteredPoints.map(point => x - point.x).reduce((a, b) => a * b) /
                filteredPoints.map(point => p.x - point.x).reduce((a, b) => a * b);
        });
        return {y: lCoeffs.reduce((a, b) => a+b)};
    }
}