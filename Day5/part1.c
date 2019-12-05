#include <stdio.h>
#include <stdlib.h>
int* readFile(char argv[], int *len);
void runProgram(int* program, int len);
void add(int in1, int in2, int* out);
void mul(int in1, int in2, int* out);
void input(int* address);
void output(int* address);

int main(int argc, char *argv[]){
    int len;
    int *arr = readFile(argv[1], &len);
    int i;
    runProgram(arr, len);
    printf("%d", *(arr+4));
}

void runProgram(int* program, int len){
    int *ip;
    int i;
    
    for(ip = program; ip < program+len;){
        int opcode = (*ip)%100;
        short op1_mode = (*ip)/100 % 10;
        short op2_mode = (*ip)/1000 % 10;
        short op3_mode = (*ip)/10000;
        //printf("\n%d,%d,%d,%d\n\t", *ip, *(ip+1), *(ip+2), *(ip+3));
        //printf("IP = %d: OPERAND: %d MODE: %d%d\n\t",ip-program, *ip, op1_mode, op2_mode);
        //printf("\t OP1: %d, OP2: %d, OP3: %d\n", op1_val, op2_val, op3_val);
        int  op1_val, op2_val, op3_val;
        op1_val = *(ip+1);
        op2_val = *(ip+2);
        op3_val = *(ip+3);
        switch(opcode){
            case 1:
                if(op1_mode == 0){
                    if(op2_mode == 0){
                        add(*(program+op1_val), *(program+op2_val), program + op3_val);
                    } else {
                        add(*(program+op1_val), op2_val, program+op3_val);
                    }
                } else {
                    if(op2_mode == 0){
                        add(op1_val, *(program+op2_val), program+op3_val);
                    } else {
                        add(op1_val, op2_val, program+op3_val);
                    }
                }
                ip += 4;
                break;
            case 2:
                if(op1_mode == 0){
                    if(op2_mode == 0){
                        mul(*(program+op1_val), *(program+op2_val), program + op3_val);
                    } else {
                        mul(*(program+op1_val), op2_val, program+op3_val);
                    }
                } else {
                    if(op2_mode == 0){
                        (op1_val, *(program+op2_val), program+op3_val);
                    } else {
                        mul(op1_val, op2_val, program+op3_val);
                    }
                }
                ip += 4;
                break;
            case 3:
                input(ip+op1_val);
                ip += 2;
                break;
            case 4:
                if(op1_mode == 0){
                    output(program+op1_val);
                } else {
                    output(&op1_val);
                }
                ip += 2;
                break;
            case 99:
                return;
        }
    }
}

void output(int *address){
    printf("%d", *address);
}

void input(int* address){
    printf("Please enter your value: ");
    scanf("%d", address);
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
