import { parseFileToStringArray, removeEmptyLines } from "../Utils/fileParser";

export abstract class Day06 {

    static Part1(): string {
        const input = removeEmptyLines(parseFileToStringArray('day06'));
        const waysToWin: number[] = [];
        const races = this.getRaces(input);
        for (const e of races) {
            waysToWin.push(this.solve(e.time, e.distance))
        }
        return `${waysToWin.reduce((a, b) => a * b, 1)}`
    }

    static Part2(): string {
        const input = removeEmptyLines(parseFileToStringArray('day06'));
        const race = this.getRace(input);
        return `${this.solve(race.time, race.distance)}`
    }

    static BothParts(): string {
        let start = performance.now();
        const ans1 = this.Part1();
        const t1 = (performance.now() - start).toPrecision(3);

        start = performance.now();
        const ans2 = this.Part2();
        const t2 = (performance.now() - start).toPrecision(3);
        return `Day 6 - 2023: \n\tPart 1: ${ans1} (${t1}ms)\n\tPart 2: ${ans2} (${t2}ms)`
    }
    private static getRaces(input: string[]): race[] {
        const timeString = input[0];
        const distString = input[1];
        const ret: race[] = []
        const timeNums = timeString.split(/:/)[1].split(/ /).filter(e => e.match(/\d+/)).map(e => Number(e))
        const distNums = distString.split(/:/)[1].split(/ /).filter(e => e.match(/\d+/)).map(e => Number(e))
        for (let i = 0; i < timeNums.length; i++) {
            ret.push({ time: timeNums[i], distance: distNums[i] });
        }
        return ret;
    }


    private static getRace(input: string[]): race {
        const timeString = input[0];
        const distString = input[1];
        const time = Number(timeString.split(/:/)[1].replace(/ /g, ''))
        const distance = Number(distString.split(/:/)[1].replace(/ /g, ''))
        return { time, distance }
    }

    // -x^2 + bx - m where x is time and b is max-time, and m is the highscore
    // zeros will be the 2 x values that produce the high score
    // once we have those two roots, we need ceil and floor them to get
    // our scores. If roots _are_ ints, we still need to win
    // so we increment/decrement
    private static solve(maxTime: number, highScore: number) {
        const a = -1;
        const b = maxTime;
        const c = -highScore;
        const disc = b * b - 4 * a * c;
        //we will never have equal roots or non-existant roots
        const root1 = (-b + Math.sqrt(disc)) / (2 * a)
        const root2 = (-b - Math.sqrt(disc)) / (2 * a)
        let fakeRoot1 = Math.ceil(root1);
        let fakeRoot2 = Math.floor(root2);

        if(fakeRoot1 == root1)
            fakeRoot1++;

        if(fakeRoot2 == root2)
            fakeRoot2--;

        //+1 fencepost
        return fakeRoot2 - fakeRoot1 + 1;
    }
}

interface race {
    time: number,
    distance: number
}