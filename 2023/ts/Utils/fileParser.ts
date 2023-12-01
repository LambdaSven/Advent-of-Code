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

export {
    parseFileToStringArray,
    parseFileToString,
    parseFileToCharArray,
    removeEmptyLines
}