#include <stdio.h>
#include <stdlib.h>
#define WIDTH 25
#define HEIGHT 6

int main(int argc, char* argv[]){
    FILE *fp = fopen(argv[1], "r");
    int layer[WIDTH*HEIGHT] = {0};
    int final[WIDTH*HEIGHT];
    int i, j;
    for(i = 0; i < WIDTH*HEIGHT; i++){
        final[i] = 2;
    }

    while(!feof(fp)){
        for(i = 0; i < WIDTH*HEIGHT; i++){
            fscanf(fp, "%1d", layer + i);
            switch(layer[i]){
                case 0:
                    if(final[i] == 2){
                        final[i] = 0;
                    }
                    break;
                case 1:
                    if(final[i] == 2){
                        final[i] = 1;
                    }
                    break;
                case 2:
                    break;
            } 
        }
    }
    for(i = 0; i < HEIGHT; i++){
        for(j = 0; j < WIDTH; j++){
            printf("%d", final[i*WIDTH + j]);
        }
        puts("");
    }
}
