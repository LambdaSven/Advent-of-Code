import { parseFileTo2dStringArray } from "../Utils/fileParser";

export abstract class Day03 {

    static Part1(): string {
        const engine = this.createEngine();
        const result = engine.connectors
            .map(e => Array.from(e.parts.values())
                .reduce((a, b) => a + b))
            .filter(e => e > 0)
            .reduce((a, b) => a + b)
        return `${result}`
    }

    static Part2(): string {
        const engine = this.createEngine();
        const result = engine.connectors
            .filter(e => e.symbol == '*')
            .map(e => {
                const arr = Array.from(e.parts.values());
                arr.splice(arr.indexOf(0), 1)
                return arr;
            })
            .filter(e => e.length === 2)
            .map(e => e.reduce((a, b) => a * b, 1))
            .reduce((a, b) => a + b)
        
            return `${result}`
    }

    static BothParts(): string {
        let start = performance.now();
        const ans1 = this.Part1();
        const t1 = (performance.now() - start).toPrecision(3);

        start = performance.now();
        const ans2 = this.Part2();
        const t2 = (performance.now() - start).toPrecision(3);
        return `Day 3 - 2023: \n\tPart 1: ${ans1} (${t1}ms)\n\tPart 2: ${ans2} (${t2}ms)`    
    }
    private static createEngine(): Engine {
        const engine: Engine = new Engine()
        const data = parseFileTo2dStringArray('day03')
        const numData = this.inputToNumArray(data);
        for(let i = 0; i < numData.length; i++) {
            for(let j = 0; j < numData[i].length; j++) {
                if(numData[i][j] === -1) {
                    //symbol located.
                    const connector: Connector = {parts: new Set<number>, symbol: data[i][j]}
                    connector.parts.add(numData[i-1][j-1] ?? 0)
                    connector.parts.add(numData[i-1][j] ?? 0)
                    connector.parts.add(numData[i-1][j+1] ?? 0)
                    connector.parts.add(numData[i][j-1] ?? 0)
                    connector.parts.add(numData[i][j+1] ?? 0)
                    connector.parts.add(numData[i+1][j-1] ?? 0)
                    connector.parts.add(numData[i+1][j] ?? 0)
                    connector.parts.add(numData[i+1][j+1] ?? 0)
                    engine.addConnector(connector);
                    //console.log(connector.parts)
                }
            }
        }
        return engine;
    }

    private static inputToNumArray(input: string[][]): number[][] {
        let numberStack = '';
        const ret: number[][] = []
        for (let i = 0; i < input.length; i++) {
            const numRet = [];
            for (let j = 0; j < input[i].length; j++) {
                const pos = input[i][j]
                if (pos.match(/\./)) {
                    //push the stack
                    for (let i = 0; i < numberStack.length; i++) {
                        numRet.push(Number(numberStack));
                    }
                    numberStack = ''
                    numRet.push(0);
                } else if (pos.match(/\d/)) {
                    numberStack += pos;
                } else {
                    //push the stack
                    for (let i = 0; i < numberStack.length; i++) {
                        numRet.push(Number(numberStack));
                    }
                    numberStack = ''
                    numRet.push(-1)
                }
            }
            for (let i = 0; i < numberStack.length; i++) {
                numRet.push(Number(numberStack));
            }
            numberStack = ''
            ret.push(numRet);
        }
        return ret;
    }
}

class Engine {
    connectors: Connector[];

    constructor() {
        this.connectors = [];
    }

    addConnector(c: Connector) {
        this.connectors.push(c);
    }
}

interface Connector {
    symbol: string
    parts: Set<number>
}