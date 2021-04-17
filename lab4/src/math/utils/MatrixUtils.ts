import * as math from "mathjs";

export class MatrixUtils {

    static solveSLAU(coeffs: number[][], freeMembers: number[]): number[] {
        if (coeffs === undefined || freeMembers === undefined || coeffs.length != freeMembers.length) return undefined;

        let matrix: number[][];
        const det: number = math.det(coeffs);
        const dets: number[] = [];

        for (let i = 0; i < freeMembers.length; i++) {
            matrix = coeffs;
            for (let j = 0; j < freeMembers.length; j++) {
                matrix[j][i] = freeMembers[j];
            }
            dets.push(math.det(matrix));
        }

        return Array(freeMembers.length).fill(0).map((v, i) => dets[i] / det);
    }
}