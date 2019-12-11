#include <stdio.h>
#include <stdlib.h>
int* readFile(char argv[], int *len);
void runProgram(int* program, int len);
void add(int in1, int in2, int* out);
void mul(int in1, int in2, int* out);


int main(int argc, char *argv[]){
    int len;
    int *arr = readFile(argv[1], &len);
    int i;
    *(arr+1) = 12;
    *(arr+2) = 2;    
    runProgram(arr, len);
    printf("%d\n", *arr);
}

void runProgram(int* program, int len){
    int *ip;
    for(ip = program; ip < program+len; ip += 4){
        switch(*(ip)){
            case 1:
                add(program[*(ip+1)], program[*(ip+2)], program + *(ip + 3));
                break;
            case 2:
                mul(program[*(ip+1)], program[*(ip+2)], program + *(ip + 3));
                break;
            case 99:
                return;
         }
    }
}
/*
    These add/mul functions aren't strictly required, but I preferred
    modelling the program like this
*/
void add(int in1, int in2, int* out){
    *out = in1 + in2;
}

void mul(int in1, int in2, int* out){
    *out = in1 * in2;
}

int* readFile(char *argv, int *len){
    FILE *fp;
    fp = fopen(argv, "r");
    
    int* ptr = (int*) malloc(sizeof(int));
    int index = 0;
    
    /*
        fscanf() returns the number of elements scanned, then moves the file pointer
        we only want to break the loop when it stops reciving valid input, or, when it
        returns any number other than 1

        By using "%d," instead of "%d" we automatically strip the delimiting commas  
    */
    while(fscanf(fp, "%d,", ptr+index) == 1){
        index++;
        ptr = (int*) realloc(ptr, (index+1)*sizeof(int));
    }
    fclose(fp);
    
    *len = index;
    return ptr;
}
