export interface ResearchResult {
    color: string;
    view: string;
    fnc: (number) => number;
    a: number;
    b: number;
    c?: number;
    deviationMeasure: number;
    standardDeviation: number;
}