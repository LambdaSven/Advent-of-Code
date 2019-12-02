#include <stdio.h>
#include <stdlib.h>
int* readFile(char argv[]);

int main(int argc, char *argv[]){
    int *arr = readFile(argv[1]);
    int len = sizeof(arr) / sizeof(int);
    int i;
    puts("\n");
    for(i = 0; i < len; i++){
        printf("%d, ", arr[i]);
    }
}

int* readFile(char *argv){
    FILE *fp;
    fp = fopen(argv, "r");
    
    // big malloc so that I can ensure enough space
    int* ptr = (int*) malloc(500 * sizeof(int));
    int index = 0;
    while(fscanf(fp, "%d", ptr+index) == 1 || fscanf(fp, "%c", ptr+index) == 1){
        printf("%d, ", *(ptr + index));
        index++;
    }
        
    printf("\n INDEX: %d", index);    
    // don't need calloc since I should be setting every element successfully
    int* arr = (int*) malloc(index * sizeof(int));
    int len = index;
    for(index = 0; index < len/2; index++){
        *(arr + index) = *(ptr + index*2);
    }
    free(ptr);
    fclose(fp)
    return arr;
}
