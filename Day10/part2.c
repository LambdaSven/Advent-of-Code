#include <stdio.h>
#include <stdlib.h>
#include <math.h>

struct point{
    int x, y;
    struct line* lines;
    int numLines;
};

int main(int argc, char* argv[]){
    FILE *fp = fopen(argv[1], "r");
    int width = 34;
    int height = 34;
    //from part 1
    int stationX = 26;
    int stationY = 28;
    char points[height][width];
    char current;
    int index;
    for(index = 0; !feof(fp); index++){
        fscanf(fp, "%c", &current);
        if(current != '\n'){
            points[index/height][index%width] = current;
            if(index/height == stationY && index%width == stationX){
                points[index/height][index%width] = 'X';
            }
        } else {
            index--;
        }
    }
    int i, j;
    for(i = 0; i < height; i++){
        for(j = 0; j < width; j++){
            printf("%c", points[i][j]);
        }
        puts("");
    }
    
}


