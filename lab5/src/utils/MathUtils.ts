export class MathUtils {

    static roundToFixed(x: number, positions: number = 4): number {
        return Math.round(x * Math.pow(10, positions)) / Math.pow(10, positions);
    }
}