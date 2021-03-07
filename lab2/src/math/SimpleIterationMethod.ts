import {SimpleIterationMethodResult} from '../models/SimpleIterationMethodResult';
import {MathFunction} from '../models/MathFunction';
import {VerificationUtils} from '../utils/VerificationUtils';

export class SimpleIterationMethod {

    static calculate(func: MathFunction, a0: number, b0: number, x0: number, fault: number): SimpleIterationMethodResult {
        const verificationResult = VerificationUtils.completeVerification(func, a0, b0);
        if (verificationResult !== undefined) return {errorMessage: verificationResult};

        const lambda = -1/Math.max(Math.abs(func.derivative(a0)), Math.abs(func.derivative(b0)));
        const xFunc = (x: number) => x + lambda*func.fnc(x);
        const xFuncDerivative = (x: number) => 1 + lambda*func.derivative(x);

        const q = Math.max(Math.abs(xFuncDerivative(a0)), Math.abs(xFuncDerivative(b0)));
        if(q >= 1) return {errorMessage: "Достаточное условие метода не выполняется"};
        fault = q <= 0.5 ? fault : (1-q)*fault/q;

        const xValues = [], nextXValues = [], xFuncNext = [], funcNext = [], faults = [],
            functions = [(x: number) => x, xFunc];
        let x = x0;
        do {
            xValues.push(x);
            const nextX = xFunc(x);
            nextXValues.push(nextX);
            xFuncNext.push(xFunc(nextX));
            funcNext.push(func.fnc(nextX));
            faults.push(Math.abs(nextX - x));
            x = nextX;
        } while (faults[faults.length - 1] > fault);

        return {xValues: xValues, nextXValues: nextXValues, xFuncNext: xFuncNext, funcNext: funcNext,
            faults: faults, functions: functions};
    }
}
