import {Point} from "../../models/Point";
import {ApproximatingFunction} from "../../models/ApproximatingFunction";

export interface ApproximationManager {

    solve(points: Point[]): ApproximatingFunction;
}