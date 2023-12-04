import { parseFileToStringArray, removeEmptyLines } from "../Utils/fileParser";

export abstract class Day02 {

    static Part1(): string {
        const bags = this.createBags().slice(1); //ids 1 indexed
        let sum = 0;
        for(const e of bags) {
            if (e.maxRed <= 12
             && e.maxBlue <= 14
             && e.maxGreen <= 13) {
                sum += e.id;
             }
        }
        return `${sum}`
    }
    
    static Part2(): string {
        const bags = this.createBags().slice(1);
        let sum = 0;
        for(const e of bags) {
            const power = e.maxBlue * e.maxGreen * e.maxRed
            sum += power
        }
        return `${sum}`
    }

    static BothParts(): string {
        let start = performance.now();
        const ans1 = this.Part1();
        const t1 = (performance.now() - start).toPrecision(3);

        start = performance.now();
        const ans2 = this.Part2();
        const t2 = (performance.now() - start).toPrecision(3);
        return `Day 2 - 2023: \n\tPart 1: ${ans1} (${t1}ms)\n\tPart 2: ${ans2} (${t2}ms)`    }

    static createBags() {
        const raw = removeEmptyLines(parseFileToStringArray('day02'));
        const bags: Bag[] = [];
        for(const s of raw) {
            let parsing = s.split(':');
            const id = Number(parsing[0].match(/\d/g)?.join(''))
            bags[id] = new Bag(id);
            parsing = parsing[1].split(';');
            //each pull
            for(const p of parsing) {
                const pa = p.split(',');
                for(const e of pa) {
                    const count = Number(e.match(/\d/g)?.join(''))
                    if(e.includes('red')) {
                        bags[id].bagPull(count, 'red')
                    } else if (e.includes('blue')) {
                        bags[id].bagPull(count, 'blue')
                    } else if (e.includes('green')) {
                        bags[id].bagPull(count, 'green')
                    }else {
                        throw new Error(`cannot parse ${p}`)
                    }
                }
            }
        }
        return bags;
    }
}

class Bag {
    id: number;
    private _maxRed: number 
    private _maxBlue: number
    private _maxGreen: number;

    constructor(id: number) {
        this.id = id;
        this._maxRed = 0;
        this._maxGreen = 0;
        this._maxBlue = 0
    }

    bagPull(count: number, colour: ('red' | 'blue' | 'green')) {
        switch(colour) {
            case 'red':
                this._maxRed = Math.max(this._maxRed, count)
                break;
            case 'blue': 
                this._maxBlue = Math.max(this._maxBlue, count);
                break;
            case 'green':
                this._maxGreen = Math.max(this._maxGreen, count)
        }
    }
    get maxRed() {return this._maxRed}
    get maxBlue() {return this._maxBlue}
    get maxGreen() {return this._maxGreen}
}