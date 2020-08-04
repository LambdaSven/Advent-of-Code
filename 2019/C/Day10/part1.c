#include <stdio.h>
#include <stdlib.h>
#include <math.h>

struct line{
    double slope;
    int endX, endY;
};

struct point{
    int x, y;
    struct line* lines;
    int numLines;
};

int main(int argc, char* argv[]){
    FILE *fp = fopen(argv[1], "r");
    int width = 34;
    int height = 34;
    struct point* points = malloc(128*sizeof(*points));
    int pointArraySize = 128;
    int numPoints = 0;
    int index;
    char current = '.';
    for(index = 0; !feof(fp); index++){
        fscanf(fp, "%c", &current);
        
        if(numPoints >= pointArraySize-10){
            pointArraySize *= 2;
            points = realloc(points, pointArraySize*sizeof(*points));
        }

        if(current == '#'){
            points[numPoints].x = index % width;
            points[numPoints].y = index / height;
            points[numPoints].numLines = 0;
            points[numPoints].lines = calloc(512, sizeof(struct line));
            int i;
            for(i = 0; i < 512; i++){
                points[numPoints].lines[i].slope = 0.00000001;
                points[numPoints].lines[i].endX = 999999;
                points[numPoints].lines[i].endY = 9999999;
            }
            numPoints++;
        } else if(current == '\n'){index--;}
    }
    int stationX;
    int stationY;
    int result = 0;
    for(index = 0; index < numPoints; index++){
        struct point* currentPoint = points+index;
        int i, j;
        for(i = 0; i < numPoints; i++){
            struct point* currentDest = points+i;
            if((*currentDest).x == (*currentPoint).x && (*currentDest).y == (*currentPoint).y){
                continue;
            } else {
                // (y2 - y1)/ (x2 - x1)
                int deltaX = (*currentDest).x - (*currentPoint).x;
                int deltaY = (*currentDest).y - (*currentPoint).y;
                double slope;
                if(deltaX == 0){
                    if(currentDest->y > currentPoint->y){
                        slope = 99999;
                    } else if(currentDest->y < currentPoint->y){
                        slope = -99999;
                    }
                }else if(deltaY == 0){
                    if(currentDest->x > currentPoint->x){
                        slope = 123456;
                    } else if(currentDest->x < currentPoint->x){
                        slope = -123456;
                    }  
                } else {
                    slope = (double)deltaY / deltaX;
                }
                short dupe = 0;
                
                for(j = 0; j <= currentPoint->numLines; j++){
                    if(currentPoint->lines[j].slope == slope){
                        if(currentPoint->lines[j].endX > currentPoint->x){
                            if(currentDest->x > currentPoint->x){
                                dupe = 1;
                            }
                        }else if(currentPoint->lines[j].endY > currentPoint->y){
                            if(currentDest->y > currentPoint->y){
                                dupe = 1;
                            }
                        }else if(currentPoint->lines[j].endX < currentPoint->x){
                            if(currentDest->x < currentPoint->x){
                                dupe = 1;
                            }
                        }else if(currentPoint->lines[j].endY < currentPoint->y){
                            if(currentDest->y < currentPoint->y){
                                dupe = 1;
                            }
                        }
                    }
                }
                if(dupe != 1){
                    currentPoint->lines[currentPoint->numLines].slope = slope;
                    currentPoint->lines[currentPoint->numLines].endX = currentDest->x;
                    currentPoint->lines[currentPoint->numLines].endY = currentDest->y;
                    currentPoint->numLines++;
                }                
            }
        }
        if(result < currentPoint->numLines){
            result = currentPoint->numLines;
            stationX = currentPoint->x;
            stationY = currentPoint->y;
        }
    }
    printf("Station at (%d , %d) has %d asteroids\n", stationX, stationY, result);
}
