#include <stdio.h>

int calcFuel(int fuel);
int main(int argc, char *argv[]){
    FILE *file;
    int i = 0;
    unsigned long sum = 0;
    
    file = fopen(argv[1], "r");
    fscanf(file, "%d", &i);
    
    while(!feof(file)){
        int temp = i;
        i = i/3 - 2;
        sum += i;
        sum += calcFuel(i);
        fscanf(file, "%d", &i);
        
    }
    
    fclose (file);
    printf("%lu\n", sum);
}

//Recursion seemed the obvious choice for this problem
int calcFuel(int fuel){
    int tempFuel = fuel;
    tempFuel = tempFuel/3 - 2;
    if(tempFuel <= 0){
        return 0;
    } else {
        return tempFuel + calcFuel(tempFuel);
    }    
}

