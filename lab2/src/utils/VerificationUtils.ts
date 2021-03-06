import {MathFunction} from '../models/MathFunction';

export class VerificationUtils {

    static completeVerification(func: MathFunction, a0: number, b0: number): string | undefined {
        if(func.fnc(a0)*func.fnc(b0) > 0)
            return "На данном промежутке f(x) = 0 не имеет корней или их четное кол-во";
        if(isNaN(func.fnc(a0)) || isNaN(func.fnc(b0)) || !isFinite(func.fnc(a0)) || !isFinite(func.fnc(b0)))
            return 'На интервале функция не определена';
        if(func.derivative(a0)*func.derivative(b0) < 0)
            return 'Производная не сохраняет знак на интервале';
        if(func.secondDerivative(a0)*func.secondDerivative(b0) < 0)
            return 'Вторая производная не сохраняет знак на интервале';
        return undefined;
    }
}
