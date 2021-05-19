import {Point} from "../models/Point";

export class NewtonInterpolationCalculator {

    public static POINTS: Point[];

    calculateYValue(x: number): number {
        const f = (i1: number, i2: number): number => {
            if (i1 === i2) return 0;
            return i2 - i1 === 1 ? (NewtonInterpolationCalculator.POINTS[i2].y - NewtonInterpolationCalculator.POINTS[i1].y) / (NewtonInterpolationCalculator.POINTS[i2].x - NewtonInterpolationCalculator.POINTS[i1].x) :
                (f(i1 + 1, i2) - f(i1, i2 - 1)) / (NewtonInterpolationCalculator.POINTS[i2].x - NewtonInterpolationCalculator.POINTS[i1].x);
        };

        return NewtonInterpolationCalculator.POINTS[0].y + NewtonInterpolationCalculator.POINTS.map((p, i) => {
            return f(0, i) * NewtonInterpolationCalculator.POINTS.map((point, index) => index < i ? x - point.x : 1).reduce((a, b) => a * b)
        }).reduce((a, b) => a + b);
    }
}