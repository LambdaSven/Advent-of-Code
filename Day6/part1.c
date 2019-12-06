#include <stdio.h>
#include <stdlib.h>
#include <string.h>
struct planet{
    char* id;
    char* parent;
      
};

int main(int argc, char* argv[]){
    FILE *fp = fopen(argv[1], "r");
    int size_alloc = 4;
    struct planet* map = malloc(size_alloc * sizeof(*map));
    int index = 0;
    // 4 to account for null terminator
    char parent[4];
    char child[4];
    while(fscanf(fp, ("%3s)%3s"), parent, child) == 2){
        printf("READ: %3s)%3s\n", parent, child);
        if(index >= size_alloc){
            size_alloc *= 2;
            map = realloc(map, size_alloc*sizeof(*map));
        }
        map[index].id = malloc(4*sizeof(char));
        map[index].parent = malloc(4*sizeof(char));
        memcpy(map[index].id, child, 4*sizeof(char));
        memcpy(map[index].parent, parent, 4*sizeof(char));      
        index++; 
    }
    printf("%d INDEX\n", index);
    int i;
    int numOrbits = 0;
    int searched = 0;
    struct planet* currentPlanet;
    while(searched < index){
        currentPlanet = map+searched;
        char* toFind = (*currentPlanet).parent;

        while(strcmp(toFind, "COM") != 0){
            printf("SEARCHING FOR %s\n", toFind);
            for(i = 0; i < index; i++){
               // printf("\t%s == %s ?\n", map[i].id, toFind);
                if(strcmp(toFind, map[i].id) == 0){
                    printf("\tFOUND\n");
                    currentPlanet = map+i;
                    toFind = (*currentPlanet).parent;
                    numOrbits++;
                    break;
                }
            }
        }
        numOrbits++;
        printf("REACHED COM");
        searched++;
    }

    fclose(fp);
    printf("%d\n", numOrbits);
    puts("end");   
    

}

