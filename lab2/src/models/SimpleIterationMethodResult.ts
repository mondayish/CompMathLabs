export interface SimpleIterationMethodResult {
    errorMessage?: string,
    xValues?: number[],
    nextXValues?: number[],
    xFuncNext?: number[],
    funcNext?: number[],
    faults?: number[],
    functions?: ((x: number) => number)[]
}
