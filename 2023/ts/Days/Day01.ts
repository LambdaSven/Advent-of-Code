import { parseFileToStringArray, removeEmptyLines } from "../Utils/fileParser";

export abstract class Day01 {

    static Part1(): string {
        const input: string[] = removeEmptyLines(parseFileToStringArray('day01'))
        const numStrings: string[] = input.map(e => this.getNumbers(e));
        const numbers: number[] = numStrings.map(e => `${e[0]}${e[e.length-1]}`)
            .map(e => Number(e))
        return `${numbers.reduce((a, b) => a+b)}`
    }
    
    static Part2(): string {
        const input: string[] = removeEmptyLines(parseFileToStringArray('day01'))
        const regexEdStrings: string[] = input.map(e => this.spelledToDigits(e))
        const numStrings: string[] = regexEdStrings.map(e => this.getNumbers(e));
        const numbers: number[] = numStrings.map(e => `${e[0]}${e[e.length-1]}`)
            .map(e => Number(e))
        return `${numbers.reduce((a, b) => a+b)}`
    }

    static BothParts(): string {
        let start = performance.now();
        const ans1 = this.Part1();
        const t1 = (performance.now() - start).toPrecision(3);

        start = performance.now();
        const ans2 = this.Part2();
        const t2 = (performance.now() - start).toPrecision(3);
        return `Day 1 - 2023: \n\tPart 1: ${ans1} (${t1}ms)\n\tPart 2: ${ans2} (${t2}ms)`
    }

    private static getNumbers(input: string): string {
        const match = '0123456789'
        return input.split('').map(e => match.includes(e) ? e : '').join('');
    }
    //hell regex
    private static spelledToDigits(input: string): string {
        let x = 0;
        let output = '';
        for(let i = 0; i <= input.length; i++) {
            let working = input.substring(x, i)
            working = working.replace(/one/, '1');
            working = working.replace(/two/, '2');
            working = working.replace(/three/, '3');
            working = working.replace(/four/, '4');
            working = working.replace(/five/, '5');
            working = working.replace(/six/, '6');
            working = working.replace(/seven/, '7');
            working = working.replace(/eight/, '8');
            working = working.replace(/nine/, '9');
            if(working !== input.substring(x, i)) {
                x = i-1;
                output += working;
            }
        }
        output += input.substring(x)
        return output;
    }
}