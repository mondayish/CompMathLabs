import {Point} from "../../models/Point";
import {PointsCharacteristics} from "../../models/PointsCharacteristics";

export class PointUtils {

    static calculatePointsCharacteristics(points: Point[]): PointsCharacteristics {
        const n: number = points.length;
        const sx: number = this.calculateSumFromPointMapper(points,(point) => point.x);
        const sy: number = this.calculateSumFromPointMapper(points,(point) => point.y);
        const sxx: number = this.calculateSumFromPointMapper(points,(point) => point.x * point.x);
        const sxxx: number = this.calculateSumFromPointMapper(points,(point) => point.x * point.x * point.x);
        const sxxxx: number = this.calculateSumFromPointMapper(points,(point) => Math.pow(point.x, 4));
        const sxy: number = this.calculateSumFromPointMapper(points,(point) => point.x * point.y);
        const sxxy: number = this.calculateSumFromPointMapper(points,(point) => point.x * point.x * point.y);

        return {n: n, sx: sx, sxx: sxx, sxxx: sxxx, sxxxx: sxxxx, sy: sy, sxy: sxy, sxxy: sxxy};
    }

    static calculateSumFromPointMapper(points: Point[], mapper: (Point) => number){
        return points.map(mapper).reduce((a, b) => a + b);
    }
}