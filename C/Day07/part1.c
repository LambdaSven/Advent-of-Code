#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int* readFile(char argv[], int *len);
int  runProgram(int* program, int len, int input1, int input2);
void add(int in1, int in2, int* out);
void mul(int in1, int in2, int* out);
void input(int* address, int input);
void read(int address, int* output);
void JNZ(int* program, int** ip, int op1, int op2);
void JZ(int* program, int** ip, int op1, int op2);
void LT(int op1, int op2, int* out);
void EQ(int op1, int op2, int* out);

int main(int argc, char *argv[]){
    int len;
    int *arr = readFile(argv[1], &len);
    int i,j,k,l,m;
    int index;
    int *arr_orig = malloc(len*sizeof(int));
    arr_orig = memcpy(arr_orig, arr, len*sizeof(int));
    int output = 0;
    int *input =malloc(5*sizeof(int));
    int result = 0;
    for(i = 0; i < 5; i++){
        for(j = 0; j < 5; j++){
            for(k = 0; k < 5; k++){
                for(l = 0; l < 5; l++){
                    for(m = 0; m < 5; m++){
                        if(i != j && i != k && i != l && i != m 
                        && j != k && j != l && j != m
                        && k != l && k != m 
                        && l != m){
                            output = 0;
                            input[0] = i;
                            input[1] = j;
                            input[2] = k;
                            input[3] = l;
                            input[4] = m;
                            for(index = 0; index < 5; index++){
                                memcpy(arr, arr_orig, len*sizeof(int));
                                output = runProgram(arr, len, input[index], output);
                                if(output > result){result = output;}
                                printf("Input: %d%d%d%d%d, Output: %d\n", i, j, k, l, m, output);
                            }
                        }
                    }
                }
            }
        }
    }
    printf("%d\n", result);
}

int runProgram(int* program, int len, int input1, int input2){
    int *ip;
    int output;
    int i;
    int numInputs = 2; 
    for(ip = program;;){
        int opcode = (*ip)%100;
        if(opcode == 99){
            return output;
        }    
        short op1_mode = ((*ip)/100) % 10;
        short op2_mode = ((*ip)/1000) % 10;
        int  op1, op2;
        int op3;
        int j;
        if(op1_mode == 1){
            op1 = *(ip+1);
        } else {
            op1 = *(program + *(ip+1));
        }
        if(op2_mode == 1){
            op2 = *(ip+2);
        } else {
            op2 = *(program + *(ip+2));
        }
        op3 = *(ip+3);        
        switch(opcode){
            case 1: // ADD
                add(op1, op2, program + op3);
                ip += 4;
                break;
            case 2: // MUL
                mul(op1, op2, program + op3);
                ip += 4;
                break;
            case 3: // IN
                if(--numInputs == 1){
                    input(program + *(ip+1), input1);            
                } else {
                    input(program + *(ip+1), input2);
                }
                ip += 2;
                break;
            case 4: //OUT
                read(op1, &output);
                ip += 2;
                break;
            case 5: //JNZ
                JNZ(program, &ip, op1, op2);
                break;
            case 6: //JZ
                JZ(program, &ip, op1, op2);
                break;
            case 7: //LT
                LT(op1, op2, program + op3);
                ip += 4;
                break;
            case 8://EQ
                EQ(op1, op2, program + op3);
                ip += 4;
                break;
        }   
    }
    return -1;
}

void JNZ(int* program, int** ip, int op1, int op2){
    if(op1 != 0){
        *ip = program+op2;
    } else {
        *ip += 3;
    }
    return;
}

void JZ(int* program, int** ip, int op1, int op2){
    if(op1 == 0){
        *ip = program+op2;
    } else {
        *ip += 3;
    }
    return;
}

void LT(int op1, int op2, int* op3){
    if(op1 < op2){
        *op3 = 1;
    } else {
        *op3 = 0;
    }
    return;
}

void EQ(int op1, int op2, int* op3){
    if(op1 == op2){
        *op3 = 1;
    } else {
        *op3 = 0;
    }
    return;
}
void read(int address, int* output){
    *output = address;
    return;
}

void input(int* address, int input){
    *address = input;
    return;
}

void add(int in1, int in2, int* out){
    *out = in1 + in2;
    return;
}

void mul(int in1, int in2, int* out){
    *out = in1 * in2;
    return;
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
