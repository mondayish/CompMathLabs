import {InterpolationCalculator} from "./InterpolationCalculator";
import {Point} from "../models/Point";
import {InterpolationResult} from "../models/InterpolationResult";

export class NewtonInterpolationCalculator implements InterpolationCalculator {

    calculate(points: Point[], x: number): InterpolationResult {
        function f(i1: number, i2: number): number {
            if (i1 === i2) return 0;
            return i2 - i1 === 1 ? (points[i2].y - points[i1].y) / (points[i2].x - points[i1].x) :
                (f(i1 + 1, i2) - f(i1, i2 - 1)) / (points[i2].x - points[i1].x);
        }

        const result: number = points[0].y + points.map((p, i) => {
            return f(0, i) * points.map((point, index) => index < i ? x - point.x : 1).reduce((a, b) => a * b)
        }).reduce((a, b) => a + b);

        return {y: result};
    }
}