import {SecantMethodResult} from '../models/SecantMethodResult';
import {MathFunction} from '../models/MathFunction';
import {VerificationUtils} from '../utils/VerificationUtils';

export class SecantMethod {

    static calculate(func: MathFunction, a0: number, b0: number, x0: number, fault: number): SecantMethodResult {
        const verificationResult = VerificationUtils.completeVerification(func, a0, b0);
        if (verificationResult !== undefined) return {errorMessage: verificationResult};

        if (func.fnc(x0) * func.secondDerivative(x0) < 0)
            return {errorMessage: 'Начальное приближение выбрано неправильно'};

        const x1 = a0 + (b0 - a0)/2;
        const xValues = [], nextXValues = [], funcXNext = [], prevXValues = [], faults = [], functions = [];
        let x = x1, prevX = x0;
        do {
            prevXValues.push(prevX);
            xValues.push(x);
            const nextX = x - func.fnc(x) * (x - prevX)/(func.fnc(x) - func.fnc(prevX));
            nextXValues.push(nextX);
            funcXNext.push(func.fnc(nextX));

            // замыкания )0))
            const xCopy = x;
            functions.push((x: number) => func.fnc(xCopy) + func.derivative(xCopy) * (x - xCopy));
            faults.push( Math.abs(nextXValues[nextXValues.length - 1] - xValues[xValues.length - 1]));
            prevX = x;
            x = nextX;
        } while (faults[faults.length - 1] > fault);

        return {xValues: xValues, nextXValues: nextXValues, funcXNext: funcXNext, prevXValues: prevXValues,
            faults: faults, functions: functions};
    }
}
