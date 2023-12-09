import { parseFileToStringArray, removeEmptyLines } from "../Utils/fileParser";
import { lusolve, bignumber, BigNumber, multiply, add, round } from 'mathjs'

export abstract class Day09 {
    static Part1(): string {
        const input = removeEmptyLines(parseFileToStringArray('day09'));
        const numbers = input.map(e => e.split(' ').map(Number));
        const coeffs = numbers.map(e => (this.solve(e, this.getOrder(e)).map(e => bignumber(Number(e)))))
        const solutions: BigNumber[] = []
        for(let i = 0; i < numbers.length; i++) {
            solutions.push(this.func(coeffs[i], numbers[i].length + 1))
        }
        return `${round(solutions.reduce((a, b) => add(a, b)))}`
    }

    static Part2(): string {
        const input = removeEmptyLines(parseFileToStringArray('day09'));
        const numbers = input.map(e => e.split(' ').map(Number));
        const coeffs = numbers.map(e => (this.solve(e, this.getOrder(e)).map(e => bignumber(Number(e)))))
        const solutions: BigNumber[] = []
        for(let i = 0; i < numbers.length; i++) {
            solutions.push(this.func(coeffs[i], 0))
        }
        return `${round(solutions.reduce((a, b) => add(a, b)))}`
    }

    static BothParts(): string {
        let start = performance.now();
        const ans1 = this.Part1();
        const t1 = (performance.now() - start).toPrecision(3);

        start = performance.now();
        const ans2 = this.Part2();
        const t2 = (performance.now() - start).toPrecision(3);
        return `Day 9 - 2023: \n\tPart 1: ${ans1} (${t1}ms)\n\tPart 2: ${ans2} (${t2}ms)`
    }

    private static func(solutions: BigNumber[], x: number) {
        let solution = bignumber(0);
        for(let i = 0; i < solutions.length; i++) {
            solution = add(solution, multiply(solutions[i],  bignumber(Math.pow(x, i)))) as BigNumber;
        }
        return solution;
    }

    // for the problem statement to be possible, 
    // these must all simply reduce into simple polynomials
    private static getOrder(series: number[]) {
        let count = 0;
        let working = series;
        while(working.filter(e => e !== 0).length > 0) {
            const newNum: number[] = []
            for(let i = 0; i < working.length-1; i++) {
                newNum.push(working[i+1] - working[i]);
            }
            working = newNum;
            count++;
        }
        return count - 1; //the 0 index doesn't count
    }
    private static solve(series: number[], order: number) {
        //construct matricies
        const matricies: BigNumber[][] = [];
        const seriesSlice: BigNumber[] = [];
        for(let i = 1; i <= order+1; i++) {
            const row = [];
            for(let j = 0; j <= order; j++) {
                row.push(bignumber(Math.pow(i, j)))
            }
            matricies.push(row);
            seriesSlice.push(bignumber(series[i-1]))
        }
        //thanks, math libraries
        return lusolve(matricies, seriesSlice) as BigNumber[];
    }
}