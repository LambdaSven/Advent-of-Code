import { parseFileToStringArray, removeEmptyLines } from "../Utils/fileParser";

export abstract class Day07 {

    static Part1(): string {
        const input = removeEmptyLines(parseFileToStringArray('day07'))
        const rounds = this.inputToRounds(input);
        rounds.sort((a, b) => this.handComparator(a.hand, b.hand))
        //reverse for the math
        rounds.reverse();

        return `${rounds.reduce((prev: number, current: round, index: number) => 
            prev + (current.bet * (index+1)), 0)}`
    }

    static Part2(): string {
        const input = removeEmptyLines(parseFileToStringArray('day07'))
        const rounds = this.inputToRounds(input);
        rounds.sort((a, b) => this.handComparator2(a.hand, b.hand))
        //reverse for the math
        rounds.reverse();

        return `${rounds.reduce((prev: number, current: round, index: number) => 
            prev + (current.bet * (index+1)), 0)}`
    }

    static BothParts(): string {
        let start = performance.now();
        const ans1 = this.Part1();
        const t1 = (performance.now() - start).toPrecision(3);

        start = performance.now();
        const ans2 = this.Part2();
        const t2 = (performance.now() - start).toPrecision(3);
        return `Day 7 - 2023: \n\tPart 1: ${ans1} (${t1}ms)\n\tPart 2: ${ans2} (${t2}ms)`
    }

    private static inputToRounds(input: string[]): round[] {
        const ret: round[] = [];
        for(const s of input) {
            const split = s.split(/ /);
            ret.push({hand: split[0], bet: Number(split[1])})
        }
        return ret;
    }

    private static cardComparator(a: string, b: string) {
        const map: {[index: string]: number} = {
            '2': 2,
            '3': 3,
            '4': 4,
            '5': 5,
            '6': 6,
            '7': 7,
            '8': 8,
            '9': 9,
            'T': 10,
            'J': 11,
            'Q': 12,
            'K': 13,
            'A': 14
        }
        return map[b] - map[a]
    }

    private static cardComparator2(a: string, b: string) {
        const map: {[index: string]: number} = {
            'J': 2,
            '2': 3,
            '3': 4,
            '4': 5,
            '5': 6,
            '6': 7,
            '7': 8,
            '8': 9,
            '9': 10,
            'T': 11,
            'Q': 12,
            'K': 13,
            'A': 14
        }
        return map[b] - map[a];
    }

    private static handComparator(a: string, b: string): number {
        //we're using buckets here! 
        const buckets: {[index: string]: {[index: string]: number}}= {}
        buckets[a] = {};
        buckets[b] = {};
        for(const c of a.split('')) {
            buckets[a][c] ??= 0;
            buckets[a][c]++;
        }
        for(const c of b.split('')) {
            buckets[b][c] ??= 0;
            buckets[b][c]++;
        }
        // get values sorted
        const valuesA = Object.values(buckets[a]).sort((a, b) => b - a);
        const valuesB = Object.values(buckets[b]).sort((a, b) => b - a);

        if(valuesA[0] > valuesB[0]) {
            return -1;
        } else if (valuesA[0] < valuesB[0]) {
            return 1
        } else {
            //highest count is equal
            //we must identify full house and 2=pair options
            if(valuesA[0] === 3 && valuesA[1] == 2 && valuesB[1] !== 2) {
                return -1;
            } else if(valuesB[0] === 3 && valuesB[1] === 2 && valuesA[1] !== 2) {
                return 1;
            } else if (valuesA[0] === 2 && valuesA[1] === 2 && valuesB[1] !== 2) {
                return -1
            } else if (valuesB[0] === 2 && valuesB[1] === 2 && valuesA[1] !== 2) {
                return 1;
            } else {
                // cardwise comparison - the hands must be the same rank
                // compare values of each bucket
                for(let i = 0; i < a.length; i++) {
                    const comparison = this.cardComparator(a[i], b[i]);
                    if(comparison !== 0) {
                        return comparison;
                    }
                }
                return 0;
            }
        }
    }

    private static handComparator2(a: string, b: string): number {
        //we're using buckets here! 
        const buckets: {[index: string]: {[index: string]: number}}= {}
        buckets[a] = {};
        buckets[b] = {};
        let aJs = 0;
        let bJs = 0;
        for(const c of a.split('')) {
            //assign 0 if null
            buckets[a][c] ??= 0;
            c == 'J' ? aJs++ : buckets[a][c]++;
        }
        for(const c of b.split('')) {
            //assign 0 if null
            buckets[b][c] ??= 0;
            c == 'J' ? bJs++ : buckets[b][c]++;
        }
        // get values sorted
        const valuesA = Object.values(buckets[a]).sort((a, b) => b - a);
        const valuesB = Object.values(buckets[b]).sort((a, b) => b - a);
        
        //apply jokers
        valuesA[0] += aJs;
        valuesB[0] += bJs;

        if(valuesA[0] > valuesB[0]) {
            return -1;
        } else if (valuesA[0] < valuesB[0]) {
            return 1
        } else {
            //highest count is equal
            //we must identify full house and 2=pair options
            if(valuesA[0] === 3 && valuesA[1] == 2 && valuesB[1] !== 2) {
                return -1;
            } else if(valuesB[0] === 3 && valuesB[1] === 2 && valuesA[1] !== 2) {
                return 1;
            } else if (valuesA[0] === 2 && valuesA[1] === 2 && valuesB[1] !== 2) {
                return -1
            } else if (valuesB[0] === 2 && valuesB[1] === 2 && valuesA[1] !== 2) {
                return 1;
            } else {
                // cardwise comparison - the hands must be the same rank
                // compare values of each bucket
                for(let i = 0; i < a.length; i++) {
                    const comparison = this.cardComparator2(a[i], b[i]);
                    if(comparison !== 0) {
                        return comparison;
                    }
                }
                return 0;
            }
        }
    }
}

interface round {
    hand: string,
    bet: number;
}