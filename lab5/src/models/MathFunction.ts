export interface MathFunction {
    view: string,
    fnc: (x: number) => number,
    derivative: (x: number) => number,
}