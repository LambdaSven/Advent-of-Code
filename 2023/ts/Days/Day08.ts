import { parseFileToStringArray, removeEmptyLines } from "../Utils/fileParser";
import { lcm } from 'mathjs';

export abstract class Day08 {

    static Part1(): string {
        const input = removeEmptyLines(parseFileToStringArray('day08'));
        const nodes = this.inputToNodes(input);
        const directions = input[0];

        let i = 0;
        let count = 0;
        let node = 'AAA';
        while(node !== 'ZZZ') {
            switch(directions[i]){
                case 'L':
                    node = nodes[node][0]
                    break;
                case 'R':
                    node = nodes[node][1];
                    break;
            }
            count++;
            i = count % directions.length;
            
        }
        return `${count}`

    }
     //Notice - all paths will loop. 
    // There are no locations that aren't also in the input list
    //So, find out how long it is until the first Z,
    //  and LCM all those bad boys
    static Part2(): string {
        const input = removeEmptyLines(parseFileToStringArray('day08'));
        const nodes = this.inputToNodes(input);
        const directions = input[0];

        const currentNodes = Object.keys(nodes).filter(e => e.match(/..A/))
        const zindices: number[] = [];
        for(const n of currentNodes) {
            let node = n;
            let i = 0;
            let count = 0;
            let firstz: number | undefined = undefined;

            while(!firstz) {
                switch(directions[i]){
                    case 'L':
                        node = nodes[node][0]
                        break;
                    case 'R':
                        node = nodes[node][1];
                        break;
                }
                count++;
                i = count % directions.length;

                if(node.match(/..Z/))
                    firstz = count
            }
            zindices.push(firstz);
        }
        return `${zindices.reduce((a, b) => lcm(a, b))}`
    }

    static BothParts(): string {
        let start = performance.now();
        const ans1 = this.Part1();
        const t1 = (performance.now() - start).toPrecision(3);

        start = performance.now();
        const ans2 = this.Part2();
        const t2 = (performance.now() - start).toPrecision(3);
        return `Day 8 - 2023: \n\tPart 1: ${ans1} (${t1}ms)\n\tPart 2: ${ans2} (${t2}ms)`
    }

    private static inputToNodes(input: string[]): nodes {
        const ret: nodes = {};
        //only map nodes
        for(const s of input.filter(e => e.includes('='))) {
            const eqSplit = s.split('=');
            const id = eqSplit[0].trim();
            const leaves = eqSplit[1].replace(/[(),]/g, '').trim();
            const leavesArr = leaves.split(/ /);
            ret[id] = [leavesArr[0], leavesArr[1]]
        }
        return ret;
    }
}

interface nodes {
    [index: string]: string[]
}