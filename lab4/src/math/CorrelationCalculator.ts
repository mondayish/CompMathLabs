import {Point} from "../models/Point";

export class CorrelationCalculator {

    static calculate(points: Point[]): number {
        const xMiddle = points.map(point => point.x).reduce((a, b) => a + b) / points.length;
        const yMiddle = points.map(point => point.y).reduce((a, b) => a + b) / points.length;

        return points.map(point => (point.x - xMiddle) * (point.y - yMiddle)).reduce((a, b) => a+b) /
            Math.sqrt(points.map(point => Math.pow(point.x - xMiddle, 2)).reduce((a , b) => a+b)
                * points.map(point => Math.pow(point.y - yMiddle, 2)).reduce((a , b) => a+b));
    }
}