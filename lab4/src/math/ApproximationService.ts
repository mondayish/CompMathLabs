import {Point} from "../models/Point";
import {ApproximatingFunction} from "../models/ApproximatingFunction";

export interface ApproximationService {

    solve(points: Point[]): ApproximatingFunction;
}