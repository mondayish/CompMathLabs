export interface SecantMethodResult {
    errorMessage?: string,
    xValues?: number[],
    funcXNext?: number[],
    prevXValues?: number[],
    nextXValues?: number[],
    faults?: number[],
    functions?: ((x: number) => number)[];
}
