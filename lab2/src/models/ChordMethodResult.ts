export interface ChordMethodResult {
    errorMessage?: string;
    aValues?: number[];
    bValues?: number[];
    xValues?: number[];
    funcA?: number[];
    funcB?: number[];
    funcX?: number[];
    faults?: number[];
    functions?: ((x: number) => number)[];
}
