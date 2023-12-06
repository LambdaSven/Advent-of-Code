import { parseFileToStringArray } from "../Utils/fileParser";

export abstract class Day05 {

    static Part1(): string {
        const input = parseFileToStringArray('day05');
        const seedIds = this.getSeedIds(input);
        const maps = this.makeMaps(input);
        const seeds: seed[] = []
        for(const n of seedIds) {
            seeds.push(this.createSeed(n, maps))
        }
        return `${Math.min( ... seeds.map(e => e.location))}`
    }

    //get the lowest possible from the mappings
    //, figure out which lowest value is in my range
    static Part2(): string {
        const input = parseFileToStringArray('day05');
        const seedIds = this.getSeedIds(input);
        const seedRanges: {start: number, length: number}[] = [];
        for(let i = 0; i < seedIds.length; i+=2) {
            seedRanges.push({start: seedIds[i], length: seedIds[i+1]})
        }
        let seed: seed | undefined = undefined;
        const maps = this.makeMaps(input);
        for(const range of seedRanges) {
            for(let i = range.start; i < range.start + range.length; i++) {
                const s = this.createSeed(i, maps);
                if(!seed){
                    seed = s;
                } else {
                    if(s.location < seed.location)
                        seed = s
                }
            }
        }
        //go through ranges, storing the lowest seed at every stage
        return `${seed!.location}`
    }

    static BothParts(): string {
        let start = performance.now();
        const ans1 = this.Part1();
        const t1 = (performance.now() - start).toPrecision(3);

        start = performance.now();
        //const ans2 = this.Part2();
        const ans2 = `this takes too long to run every time - will be sped up in the future`
        const t2 = (performance.now() - start).toPrecision(3);
        return `Day 5 - 2023: \n\tPart 1: ${ans1} (${t1}ms)\n\tPart 2: ${ans2} (${t2}ms)`    
    }
    //for each seed
    // grab the number in the map closest to the seed number && n <= seed
    // get the diff between to two
    // add that diff to the destination from the same row
    // profit
    private static getSeedIds(input: string[]): number[] {
        const index = input.indexOf(input.find(e => e.includes("seeds:"))!)
        const str = input[index];
        const split = str.split(/:/)
        //second list is the seeds
        return split[1].split(/ /).filter(e => e.match(/\d+/)).map(e => Number(e));
    }

    private static getMap(input: string[], src: string, dst: string): map[] {
        const str = `${src}-to-${dst} map:`;
        let index = input.indexOf(input.find(e => e.includes(str))!)
        const ret: map[] = []
        while(input[++index]) {
            const nums = input[index].split(/ /).map(e => Number(e))
            ret.push({dst: nums[0], src: nums[1], range: nums[2]})
        }
        return ret;
    }

    private static createSeed(id: number, maps: mapChain): seed {
        const soil = this.map(id, maps.seedToSoil);
        const fertilizer = this.map(soil, maps.soilToFertilizer);
        const water = this.map(fertilizer, maps.fertilizerToWater);
        const light = this.map(water, maps.waterToLight);
        const temperature = this.map(light, maps.lightToTemperature);
        const humidity = this.map(temperature, maps.temperatureToHumidity);
        const location = this.map(humidity, maps.humidityToLocation)
        return {
            id,
            soil,
            fertilizer,
            water,
            light,
            temperature,
            humidity,
            location 
        }
    }

    private static map(src: number, maps: map[]): number {
        let min = Math.pow(2, 32) // big
        let map = maps[0];
        for(const m of maps) {
            if(src >= m.src && (src - m.src) <= min) {
                min = src - m.src;
                map = m;
            }
        }
        if (min == Math.pow(2, 32) || map.src + map.range < src)
            return src;
        return map.dst + min;
    }

    private static makeMaps(input: string[]): mapChain {
        return {
            seedToSoil: this.getMap(input, 'seed', 'soil'),
            soilToFertilizer: this.getMap(input, 'soil', 'fertilizer'),
            fertilizerToWater: this.getMap(input, 'fertilizer', 'water'),
            waterToLight:this.getMap(input, 'water', 'light'),
            lightToTemperature: this.getMap(input, 'light', 'temperature'),
            temperatureToHumidity: this.getMap(input, 'temperature', 'humidity'),
            humidityToLocation: this.getMap(input, 'humidity', 'location')
        }
    }

    private static lowestMap(maps : mapChain) {
        const ret:{id: number, location: number}[] = []
        for(const m of maps.seedToSoil) {
            const seed = this.createSeed(m.src, maps)
            ret.push({id: seed.id, location: seed.location})
        }
        return ret;
    }
}

interface mapChain {
    seedToSoil: map[],
    soilToFertilizer: map[],
    fertilizerToWater: map[],
    waterToLight: map[],
    lightToTemperature: map[],
    temperatureToHumidity: map[],
    humidityToLocation: map[]
}

interface map {
    dst: number,
    src: number,
    range: number
}

interface seed {
    id: number,
    soil: number,
    fertilizer: number,
    water: number,
    light: number,
    temperature: number,
    humidity: number,
    location: number
}