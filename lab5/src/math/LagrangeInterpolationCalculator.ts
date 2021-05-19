import {Point} from "../models/Point";

export class LagrangeInterpolationCalculator {

    public static POINTS: Point[];

    calculateYValue(x: number): number {
        return LagrangeInterpolationCalculator.POINTS.map((p, i) => {
            const filteredPoints: Point[] = LagrangeInterpolationCalculator.POINTS.filter((point, index) => index != i);
            return p.y * filteredPoints.map(point => x - point.x).reduce((a, b) => a * b) /
                filteredPoints.map(point => p.x - point.x).reduce((a, b) => a * b);
        }).reduce((a, b) => a + b);
    }
}