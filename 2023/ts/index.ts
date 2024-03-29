import { Day01} from "./Days/Day01";
import { Day02 } from "./Days/Day02";
import { Day03 } from "./Days/Day03";
import { Day04 } from "./Days/Day04";
import { Day05 } from "./Days/Day05";
import { Day06 } from "./Days/Day06";
import { Day07 } from "./Days/Day07";
import { Day08 } from "./Days/Day08";
import { Day09 } from "./Days/Day09";

//get day to run
const arg: string | undefined = process.argv[2]

switch(arg) {
    case '1':
        console.log(Day01.BothParts());
        break;
    case '2':
        console.log(Day02.BothParts());
        break;
    case '3':
        console.log(Day03.BothParts());
        break;
    case '4':
        console.log(Day04.BothParts());
        break;
    case '5':
        console.log(Day05.BothParts());
        break; 
    case '6': 
        console.log(Day06.BothParts());
        break;
    case '7':
        console.log(Day07.BothParts());
        break;
    case '8': 
        console.log(Day08.BothParts());
        break;
    case '9': 
        console.log(Day09.BothParts());
        break;
    case undefined: 
        console.log(Day01.BothParts())
        console.log(Day02.BothParts());
        console.log(Day03.BothParts());
        console.log(Day04.BothParts());
        console.log(Day05.BothParts());
        console.log(Day06.BothParts());
        console.log(Day07.BothParts());
        console.log(Day08.BothParts());
        console.log(Day09.BothParts());

        break;
    default:
        throw new Error(`Invalid arg ${arg}`);

}