#include <stdio.h>
#include <stdlib.h>
int* readFile(char argv[], int *len);
void runProgram(int* program, int len);
void add(int* in1, int* in2, int* out);
void mul(int* in1, int* in2, int* out);


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
    int ip;
    for(ip = 0; ip < len; ip+=4){
        switch(*(program+ip)){
            case 1:
                add(program + program[ip+1], program + program[ip+2], program + program[ip+3]);
                break;
            case 2:
                mul(program + program[ip+1], program + program[ip+2], program + program[ip+3]);
                break;
            case 99:
                return;
         }
    }
}

void add(int* in1, int* in2, int* out){
    *out = *in1 + *in2;
}

void mul(int* in1, int* in2, int* out){
    *out = *in1 * *in2;
}

int* readFile(char *argv, int *len){
    FILE *fp;
    fp = fopen(argv, "r");
    
    int* ptr = (int*) malloc(sizeof(int));
    int index = 0;
    short terminate = 0;

    while(terminate == 0){
        if(fscanf(fp, "%d,", ptr+index) == 1){
            index++;
            ptr = (int*) realloc(ptr, (index+1)*sizeof(int));
        } else {
            terminate = 1;
        }
    }
    fclose(fp);
    
    *len = index;
    return ptr;
}
