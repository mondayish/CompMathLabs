export interface ApproximatingFunction {
    fnc: (number) => number,
    a: number,
    b: number,
    c?: number
}