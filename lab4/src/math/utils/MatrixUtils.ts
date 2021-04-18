import * as math from "mathjs";
import {PointsCharacteristics} from "../../models/PointsCharacteristics";

export class MatrixUtils {

    static solveLinear(characteristics: PointsCharacteristics): number[] {
        const det: number = math.det([[characteristics.sxx, characteristics.sx], [characteristics.sx, characteristics.n]]);
        const det1: number = math.det([[characteristics.sxy, characteristics.sx], [characteristics.sy, characteristics.n]]);
        const det2: number = math.det([[characteristics.sxx, characteristics.sxy], [characteristics.sx, characteristics.sy]]);

        return [det1/det, det2/det];
    }

    static solveQuadratic(characteristics: PointsCharacteristics){
        const det: number = math.det([[characteristics.n, characteristics.sx, characteristics.sxx],
            [characteristics.sx, characteristics.sxx, characteristics.sxxx],
            [characteristics.sxx, characteristics.sxxx, characteristics.sxxxx]]);
        const det1: number = math.det([[characteristics.sy, characteristics.sx, characteristics.sxx],
            [characteristics.sxy, characteristics.sxx, characteristics.sxxx],
            [characteristics.sxxy, characteristics.sxxx, characteristics.sxxxx]]);
        const det2: number = math.det([[characteristics.n, characteristics.sy, characteristics.sxx],
            [characteristics.sx, characteristics.sxy, characteristics.sxxx],
            [characteristics.sxx, characteristics.sxxy, characteristics.sxxxx]]);
        const det3: number = math.det([[characteristics.n, characteristics.sx, characteristics.sy],
            [characteristics.sx, characteristics.sxx, characteristics.sxy],
            [characteristics.sxx, characteristics.sxxx, characteristics.sxxy]]);

        return [det1/det, det2/det, det3/det];
    }
}