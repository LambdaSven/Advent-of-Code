#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int* readFile(char argv[], int *len);
int  runProgram(int* program, int** ip_in, int len, int input1, int input2);
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
    int output = 0;
    int result = 0;
    
    int *arr1 = malloc(len*sizeof(int));
    int *arr2 = malloc(len*sizeof(int));
    int *arr3 = malloc(len*sizeof(int));
    int *arr4 = malloc(len*sizeof(int));
    int *arr5 = malloc(len*sizeof(int));

    memcpy(arr1, arr, len*sizeof(int));
    memcpy(arr2, arr, len*sizeof(int));
    memcpy(arr3, arr, len*sizeof(int));
    memcpy(arr4, arr, len*sizeof(int));
    memcpy(arr5, arr, len*sizeof(int));
    
    int *ip1 = arr1;
    int *ip2 = arr2;
    int *ip3 = arr3;
    int *ip4 = arr4;
    int *ip5 = arr5;
    
    
    for(i = 5; i < 10; i++){
        for(j = 5; j < 10; j++){
            for(k = 5; k < 10; k++){
                for(l = 5; l < 10; l++){
                    for(m = 5; m < 10; m++){
                        if(i != j && i != k && i != l && i != m
                        && j != k && j != l && j != m
                        && k != l && k != m
                        && l != m){
                            while(*ip5 != 99){
                                output = runProgram(arr1, &ip1, len, i, output);
                                output = runProgram(arr2, &ip2, len, j, output);
                                output = runProgram(arr3, &ip3, len, k, output);
                                output = runProgram(arr4, &ip4, len, l, output);
                                output = runProgram(arr5, &ip5, len, m, output);
                            }
                            if(output > result){result = output;}
                            
                            output = 0; 
                            memcpy(arr1, arr, len*sizeof(int));
                            memcpy(arr2, arr, len*sizeof(int));
                            memcpy(arr3, arr, len*sizeof(int));
                            memcpy(arr4, arr, len*sizeof(int));
                            memcpy(arr5, arr, len*sizeof(int));
    
                            ip1 = arr1;
                            ip2 = arr2;
                            ip3 = arr3;
                            ip4 = arr4;
                            ip5 = arr5;

                        }
                    }
                }
            }
        }
    }

    printf("%d\n", result);
}

int runProgram(int* program, int **ip_in, int len, int input1, int input2){
    int *ip = *ip_in;
    int output = input2;
    int i;
    int numInputs = 2; 
    for(;;){
        int opcode = (*ip)%100;
        if(opcode == 99){
            *ip_in = ip;
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
                if(ip == program){
                    input(program + *(ip+1), input1);            
                } else {
                    input(program + *(ip+1), input2);
                }
                ip += 2;
                break;
            case 4: //OUT
                read(op1, &output);
                ip += 2;
                *ip_in = ip;
                return output;
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
