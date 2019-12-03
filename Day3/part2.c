#include <stdio.h>
#include <stdlib.h>

struct point{
    int x;
    int y;
    int isInt;
    int stepsToPoint;
};
struct intersection{
    int x;
    int y;
    int stepsw1;
    int stepsw2;
};

int main(int argc, char* argv[]){
    FILE *fp = fopen(argv[1], "r");
    struct point* points = malloc(sizeof(*points));
    (*points).x = 0;
    (*points).y = 0;
    (*points).isInt = 1;

    struct intersection* ints = malloc(sizeof(*ints));
    char ins;
    int len;
    int numStructs = 1;
    int numInts = 0;
    //RUN
    int wire = 1;
    int x = 0;
    int y = 0;
    int currentSteps = 0;
    while(fscanf(fp, "%c", &ins) == 1){
        if (ins == '\n'){
            x = 0;
            y = 0;
            currentSteps = 0;
            wire++;
        }else{
            fscanf(fp, "%d,", &len);
            int i;
            for(i = 0; i < len; i++){
                currentSteps++;
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
                        if(points[j].isInt == 3){
                            numInts++;
                            ints = realloc(ints, numInts*sizeof(*ints));
                            ints[numInts-1].x = x;
                            ints[numInts-1].y = y;
                            ints[numInts-1].stepsw1 = points[j].stepsToPoint;
                            ints[numInts-1].stepsw2 = currentSteps;
                        }
                    }
                }
                if(isDupe == 0){
                    numStructs++;
                    points = realloc(points, numStructs * sizeof(*points));
                    points[numStructs-1].x = x;
                    points[numStructs-1].y = y;
                    points[numStructs-1].isInt = wire;
                    points[numStructs-1].stepsToPoint = currentSteps;
                }

            }

        }
    }
    int i;
    long steps = 99999999;
    for(i = 0; i < numInts; i++){
        int tempSteps = ints[i].stepsw1 + ints[i].stepsw2;
        if(steps > tempSteps){
            steps = tempSteps;
        }
    } 
    printf("SHORTEST PATH: %d\n", steps);
}
