#include <stdio.h>
#include <stdlib.h>

struct point{
    int x;
    int y;
    int isInt;
};

int main(int argc, char* argv[]){
    FILE *fp = fopen(argv[1], "r");
    struct point* points = malloc(sizeof(*points));
    (*points).x = 0;
    (*points).y = 0;
    (*points).isInt = 1;

    char ins;
    int len;
    int numStructs = 1;
    //RUN
    int wire = 1;
    int x = 0;
    int y = 0;
    while(fscanf(fp, "%c", &ins) == 1){
        if (ins == '\n'){
            x = 0;
            y = 0;
            wire++;
        }else{
            fscanf(fp, "%d,", &len);
            int i;
            for(i = 0; i < len; i++){
                switch(ins){
                    case 'R':
                        x++;
                        break;
                    case 'L':
                        x--;
                        break;
                    case 'U':
                        y++;
                        break;
                    case 'D':
                        y--;
                        break;
                }
                int j;
                int isDupe = 0;
                for(j = 0; j < numStructs; j++){
                    if(points[j].x == x && points[j].y == y){
                        isDupe = 1;
                        points[j].isInt += wire;
                    }
                }
                if(isDupe == 0){
                    numStructs++;
                    points = realloc(points, numStructs * sizeof(*points));
                    points[numStructs-1].x = x;
                    points[numStructs-1].y = y;
                    points[numStructs-1].isInt = wire;
                }

            }

        }
    }
    int i;
    int distance = 999;
    int final_dist = 999;
    
    for(i = 0; i < numStructs; i++){
        if(points[i].isInt == 3){
            distance = abs(points[i].x) + abs(points[i].y);
            if(final_dist > distance && distance != 0){final_dist = distance;}
        }
    }
    printf("FINAL DISTANCE: %d\n", final_dist);
} 
