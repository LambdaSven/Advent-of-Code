#include <stdio.h>

int main(int argc, char *argv[]){
    FILE *file;
    int i = 0;
    unsigned long sum = 0;
    
    file = fopen(argv[1], "r");
    fscanf(file, "%d", &i);
    
    while(!feof(file)){
        int temp = i;
        i /= 3;         //Why round down when I can just use integer division
        i -= 2;
        sum += i;
        fscanf(file, "%d", &i);
    }
    
    fclose (file);
    printf("%lu\n", sum);
}
