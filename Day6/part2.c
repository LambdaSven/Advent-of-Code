#include <stdio.h>
#include <stdlib.h>
#include <string.h>
struct planet{
    char* id;
    char* parent;
    int distYOU;
    int distSAN;  
};

int main(int argc, char* argv[]){
    FILE *fp = fopen(argv[1], "r");
    int size_alloc = 4;
    struct planet* map = malloc(size_alloc * sizeof(*map));
    int index = 0;
    // 4 to account for null terminator
    char parent[4];
    char child[4];
    int start, end;
    while(fscanf(fp, ("%3s)%3s"), parent, child) == 2){
        if(index >= size_alloc){
            size_alloc *= 2;
            map = realloc(map, size_alloc*sizeof(*map));
        }
        map[index].id = malloc(4*sizeof(char));
        map[index].parent = malloc(4*sizeof(char));
        memcpy(map[index].id, child, 4*sizeof(char));
        memcpy(map[index].parent, parent, 4*sizeof(char));      
        map[index].distYOU = 9999999;
        map[index].distSAN = 9999999;
        if(strcmp(map[index].id, "SAN") == 0){
            end = index;
            map[index].distSAN = 0;
        }
        if(strcmp(map[index].id, "YOU") == 0){
            start = index;
            map[index].distYOU = 0;
        }
        index++; 
    }
    int i;
    int numOrbits = 9999999;
    int numJumps = -1;
    struct planet *currentPlanet = map+start;

    char* toFind = (*currentPlanet).parent;
    while(strcmp(toFind, "COM") != 0){
        numJumps++;
        for(i = 0; i < index; i++){
            if(strcmp(toFind, map[i].id) == 0){
                currentPlanet = map+i;
                toFind = (*currentPlanet).parent;
                (*currentPlanet).distYOU = numJumps;
                break;
            }
        }
    }
   
    numJumps = -1;
    currentPlanet = map+end;
    toFind = (*currentPlanet).parent;
    while(strcmp(toFind, "COM") != 0){
        numJumps++;
        for(i = 0; i < index; i++){
            if(strcmp(toFind, map[i].id) == 0){
                currentPlanet = map+i;
                toFind = (*currentPlanet).parent;
                (*currentPlanet).distSAN = numJumps;
            }
        }
    }

    for(i = 0; i < index; i++){
        int temp = map[i].distSAN + map[i].distYOU;
        printf("%d\t%d\n", map[i].distSAN, map[i].distYOU);
        if(temp < numOrbits){
            numOrbits = temp;
        }
    }
    
    fclose(fp);
    printf("%d\n", numOrbits);
    puts("end");   
    

}

