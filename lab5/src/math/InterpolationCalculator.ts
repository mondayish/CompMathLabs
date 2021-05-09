import {Point} from "../models/Point";
import {InterpolationResult} from "../models/InterpolationResult";

export interface InterpolationCalculator {

    calculate(points: Point[], x: number): InterpolationResult;
}