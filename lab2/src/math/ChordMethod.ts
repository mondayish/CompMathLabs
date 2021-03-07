import {MathFunction} from '../models/MathFunction';
import {ChordMethodResult} from '../models/ChordMethodResult';
import {VerificationUtils} from '../utils/VerificationUtils';

export class ChordMethod {

    static calculate(func: MathFunction, a0: number, b0: number, fault: number): ChordMethodResult {
        let x0: number;
        const verificationResult = VerificationUtils.completeVerification(func, a0, b0);
        if (verificationResult !== undefined) return {errorMessage: verificationResult};

        if(func.fnc(a0)*func.secondDerivative(a0) > 0) x0 = a0;
        else x0 = b0;

        const aValues = [], bValues = [], xValues = [], funcA = [], funcB = [], funcX = [], faults = [], functions = [];
        let a = a0, b = b0, x = x0;
        do {
            aValues.push(a);
            bValues.push(b);
            funcA.push(func.fnc(a));
            funcB.push(func.fnc(b));
            x = a - (b - a)*func.fnc(a)/(func.fnc(b) - func.fnc(a));
            xValues.push(x);
            funcX.push(func.fnc(x));

            // change interval
            if(func.fnc(x)*func.fnc(a) < 0) b = x;
            else a = x;

            // замыкания )0))
            const aCopy = a, bCopy = b;
            functions.push((x: number) => (x - aCopy)*(func.fnc(bCopy) - func.fnc(aCopy))/(bCopy-aCopy) + func.fnc(aCopy));
            const currentFault = xValues.length > 1 ?
                Math.abs(xValues[xValues.length - 1] - xValues[xValues.length - 2]) : null;
            faults.push(currentFault);
        } while (faults[faults.length - 1] == null || faults[faults.length - 1] > fault);

        return {aValues: aValues, bValues: bValues, xValues: xValues, funcA: funcA, funcB: funcB,
            funcX: funcX, faults: faults, functions: functions};
    }
}
