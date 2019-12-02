#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int* readFile(char argv[], int *len);
void runProgram(int* program, int len);
void add(int in1, int in2, int* out);
void mul(int in1, int in2, int* out);

int main(int argc, char *argv[]){
    int len;
    int *arr = readFile(argv[1], &len);
    int i, j;
    int req = 19690720;
    for(i =0; i< 100 && *arr != req; i++){
        for(j = 0; j < 100 && *arr != req; j++){
            //ensuring the same array every time
            arr = readFile(argv[1], &len);
            *(arr+1) = i;
            *(arr+2) = j;
            runProgram(arr, len);
        }
    }
    //To negate the final increment the for loop does before terminating
    i--;
    j--;
    printf("%d\n", 100 * i + j);
}

void runProgram(int* program, int len){
    int* ip;
    for(ip = program; ip < program+len; ip += 4){
        switch(*ip){
            case 1:
                add(program[*(ip+1)], program[*(ip+2)], program + *(ip+3));
                break;
            case 2:
                mul(program[*(ip+1)], program[*(ip+2)], program + *(ip+3));
                break;
            case 99:
                return;
            default:
                return;
         }
    }
}

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
