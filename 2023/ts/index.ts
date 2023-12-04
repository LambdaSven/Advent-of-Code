import { Day01} from "./Days/Day01";
import { Day02 } from "./Days/Day02";
import { Day03 } from "./Days/Day03";
import { Day04 } from "./Days/Day04";

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
    case undefined: 
        console.log(Day01.BothParts())
        console.log(Day02.BothParts());
        console.log(Day03.BothParts());
        console.log(Day04.BothParts());
        break;
    default:
        throw new Error(`invalid arg ${arg}`);

}