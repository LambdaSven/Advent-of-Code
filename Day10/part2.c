#include <stdio.h>
#include <stdlib.h>
#include <math.h>

struct line{
    int x, y;
    double angle;
    double length;
    int destroyed;
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
    int stationX = 26;
    int stationY = 28;
    struct point* station;
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
                points[numPoints].lines[i].destroyed = 0;
            }
            if(index%width == stationX && index/height == stationY){
                station = points+numPoints;
            }
            numPoints++;
        } else if(current == '\n'){index--;}
    }
    int finalX, finalY;
    for(index = 0; index < numPoints; index++){
        int X = points[index].x - stationX;
        int Y = points[index].y - stationY;
        double H = sqrt(X*X + Y*Y);
        double angle;
        if(X == 0 && Y == 0){
            continue;
        }else if(X > 0){
            if(Y < 0){
                //Q1
                angle = asin(abs(Y)/H);
            } else if (Y > 0){
                //Q4
                angle = 2*M_PI - asin(abs(Y)/H);
            } else {
                angle = 0;
            }
        } else if (X < 0){
            if(Y < 0){
                //Q2
                angle = M_PI - asin(abs(Y)/H);
            } else if(Y > 0){
                //Q3
                angle = M_PI + asin(abs(Y)/H);
            } else {
                angle = M_PI;
            }
        } else {
            if(Y < 0){
                angle = M_PI/2;
            } else if (Y > 0){
                angle = (3*M_PI)/2;
            } else {
                printf("UH OH");
                continue;
            }
        }
        station->lines[index].angle = angle;
        station->lines[index].length = H; 
        station->lines[index].x = X + stationX;
        station->lines[index].y = Y + stationY;
    }


    double i;
    int numDestroyed = 1;
    for(i = M_PI/2; numDestroyed < 200; i -= 0.001){
        double shortest = 9999999;
        int indexOfShortest = -1;
        for(index = 0; index < numPoints-1; index++){
            if(station->lines[index].angle >= i-0.0010 && station->lines[index].angle <= i
            && station->lines[index].destroyed != 1){
                if(shortest > station->lines[index].length && station->lines[index].length != 0){
                    shortest = station->lines[index].length;
                    indexOfShortest = index;
                }
            }
        }
        if(indexOfShortest >= 0){ 
            station->lines[indexOfShortest].destroyed = 1;
            numDestroyed++;
            finalY = station->lines[indexOfShortest].y;
            finalX = station->lines[indexOfShortest].x;
        }
        if(i-0.001 < 0){i = 2*M_PI;}
    }

    printf("%d\n", (finalX*100)+finalY);
}
