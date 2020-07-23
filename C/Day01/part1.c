#include <stdio.h>
#include <stdlib.h>
int main(int argc, char *argv[]){
    if(argc != 2){
        fprintf(stderr, "You must include exactly 1 input files.\n");
        exit(-1);
    }
    FILE *file;
    int i = 0;
    unsigned long sum = 0;
    
    file = fopen(argv[1], "r");
    fscanf(file, "%d", &i);
    
    while(!feof(file)){
        int temp = i;
        i = i/3 - 2;
        sum += i;
        fscanf(file, "%d", &i);
    }
    
    fclose (file);
    printf("%lu\n", sum);
}
