import { parseFileToStringArray, removeEmptyLines } from "../Utils/fileParser"

export abstract class Day04 {

    static Part1(): string {
        const input = removeEmptyLines(parseFileToStringArray('day04'))
        const tickets = this.inputToScratchTickets(input);
        const result = tickets.map(e => e.getScore()).reduce((a, b) => a+b)
        return `${result}`
    }

    static Part2(): string {
        const input = removeEmptyLines(parseFileToStringArray('day04'))
        const tickets = this.inputToScratchTickets(input);
        const counts: number[] = Array(tickets.length).fill(1)
        for(let i = 0; i < tickets.length; i++) {
            const workingTicket = tickets[i];
            for(let j = 0; j < workingTicket.getMatches(); j++) {
                counts[i + j + 1] += 1 * counts[i]
            }
        }
        return `${Object.values(counts).reduce((a, b) => a + b)}`
    }

    static BothParts(): string {
        return `Day 4 - 2023: \n\tPart 1: ${this.Part1()}\n\tPart 2: ${this.Part2()}`
    }

    private static inputToScratchTickets(input: string[]): scratchTicket[] {
        const ret: scratchTicket[] = []
        for(const s of input) {
            //first number is id
            const splitColon: string[] = s.split(':')
            const id = splitColon[0].match(/\d+/);
            const ticket = new scratchTicket(Number(id))
            //get 2 sections
            const segments = splitColon[1].split(/\|/);
            ticket.winningNumbers = segments[0].split(/ /).filter(e => e.match(/\d+/)).map(e => Number(e));
            ticket.selectedNumbers = segments[1].split(/ /).filter(e => e.match(/\d+/)).map(e => Number(e));
            ret.push(ticket);
        }
        return ret;
    }
}

class scratchTicket {
    id: number;
    selectedNumbers: number[];
    winningNumbers: number[];
    score?: number;
    matches?: number;

    constructor(id: number) {
        this.id = id;
        this.selectedNumbers = [];
        this.winningNumbers = [];
    }

    getScore(): number {
        return this.score ?? this.calculateScore()
    }
    
    public getMatches() {
        return this.matches ?? this.calculateMatches();
    }

    private calculateScore() : number {

        return Math.floor(Math.pow(2, this.getMatches()-1));
    }

    private calculateMatches(): number {
        let count = 0;
        //console.log(`${this.winningNumbers} | ${this.selectedNumbers}`)
        for(const n of this.selectedNumbers) {
            if(this.winningNumbers.includes(n))
                count++;
        }
        //console.log(count)
        return count;
    }
}