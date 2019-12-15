#include <stdio.h>
#include <stdlib.h>
#define WIDTH 25
#define HEIGHT 6

int main(int argc, char* argv[]){
    FILE *fp = fopen(argv[1], "r");
    int layer[WIDTH*HEIGHT] = {0};
    int num0 = 0;
    int num1 = 0;
    int num2 = 0;
    int min0 = 9999;
    int result = 0;
    int i;
    while(!feof(fp)){
        for(i = 0; i < WIDTH*HEIGHT; i++){
            fscanf(fp, "%1d", layer + i);
            switch(layer[i]){
                case 0:
                    num0++;
                    break;
                case 1:
                    num1++;
                    break;
                case 2:
                    num2++;
                    break;
            } 
        }

        if(num0 < min0){
            result = num1*num2;
            min0 = num0;
        }
        num0 = 0;
        num1 = 0;
        num2 = 0;
    }

    printf("%d\n", result);
}
