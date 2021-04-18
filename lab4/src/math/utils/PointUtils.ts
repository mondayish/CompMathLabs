import {Point} from "../../models/Point";
import {PointsCharacteristics} from "../../models/PointsCharacteristics";

export class PointUtils {

    static calculatePointsCharacteristics(points: Point[]): PointsCharacteristics {
        return {
            n: points.length,
            sx: this.calculateSumFromPointMapper(points, (point) => point.x),
            sxx: this.calculateSumFromPointMapper(points, (point) => point.x * point.x),
            sxxx: this.calculateSumFromPointMapper(points, (point) => point.x * point.x * point.x),
            sxxxx: this.calculateSumFromPointMapper(points, (point) => Math.pow(point.x, 4)),
            sy: this.calculateSumFromPointMapper(points, (point) => point.y),
            sxy: this.calculateSumFromPointMapper(points, (point) => point.x * point.y),
            sxxy: this.calculateSumFromPointMapper(points, (point) => point.x * point.x * point.y)
        };
    }

    static calculateSumFromPointMapper(points: Point[], mapper: (Point) => number) {
        return points.map(mapper).reduce((a, b) => a + b);
    }
}