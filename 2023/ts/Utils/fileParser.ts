import * as fs from 'fs';

function parseFileToStringArray(inputName: string): string[] {
    return parseFileToString(inputName).split('\n');
}

function parseFileToString(inputName: string): string {
    return fs.readFileSync(`./Inputs/${inputName}.txt`, 'utf-8')
}

function parseFileToCharArray(inputName: string): string[] {
    return parseFileToString(inputName).split('');
}

function removeEmptyLines(inputs: string[]): string[] {
    return inputs.filter(e => e !== '')
}

function parseFileTo2dStringArray(inputName: string): string[][] {
    return removeEmptyLines(parseFileToStringArray(inputName)).map(e => e.split(''));
}

export {
    parseFileToStringArray,
    parseFileToString,
    parseFileToCharArray,
    removeEmptyLines,
    parseFileTo2dStringArray
}