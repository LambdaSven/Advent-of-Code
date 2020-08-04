#include <stdio.h>
#include <stdlib.h>

struct planet{
    int id, x, y, z, dx, dy, dz;
};

int main(int argc, char* argv[]){
    struct planet planets[4];
    FILE *fp = fopen(argv[1], "r");
    int i, j;
    int planet1[] = {0,0,0,1,1,2};
    int planet2[] = {1,2,3,2,3,3};
    for(i = 0; i < 4; i++){
        int x, y, z;
        fscanf(fp, "<x=%d, y=%d, z=%d>\n", &x, &y, &z);
        planets[i].id = i;
        planets[i].x = x;
        planets[i].y = y;
        planets[i].z = z;
        planets[i].dx = 0;
        planets[i].dy = 0;
        planets[i].dz = 0;
    }
    for(i = 0; i < 4; i++){
        printf("POS: <x=%d, y=%d, z=%d>\t", planets[i].x, planets[i].y, planets[i].z);
        printf("VEL: <x=%d, y=%d, z=%d>\n", planets[i].dx, planets[i].dy, planets[i].dz);
    }
    int cycle;
    for(cycle = 0; cycle < 1000; cycle++){
        for(i = 0; i < 6; i++){
            int p1 = planet1[i];
            int p2 = planet2[i];
            if(planets[p1].x > planets[p2].x){
                planets[p1].dx--;
                planets[p2].dx++;
            } else if(planets[p1].x < planets[p2].x){
                planets[p1].dx++;
                planets[p2].dx--;
            }
            if(planets[p1].y > planets[p2].y){
                planets[p1].dy--;
                planets[p2].dy++;
            } else if(planets[p1].y < planets[p2].y){
                planets[p1].dy++;
                planets[p2].dy--;
            }
            if(planets[p1].z > planets[p2].z){
                planets[p1].dz--;
                planets[p2].dz++;
            } else if(planets[p1].z < planets[p2].z){
                planets[p1].dz++;
                planets[p2].dz--;
            }
        }
        for(i = 0; i < 4; i++){
            planets[i].x += planets[i].dx;
            planets[i].y += planets[i].dy;
            planets[i].z += planets[i].dz;
        }
    
        printf("CYCLE %d\n", cycle); 
        for(i = 0; i < 4; i++){
            printf("\tPOS: <x=%d, y=%d, z=%d>\t", planets[i].x, planets[i].y, planets[i].z);
            printf("\tVEL: <x=%d, y=%d, z=%d>\n", planets[i].dx, planets[i].dy, planets[i].dz);
        }
    }
    int pot, kin;
    int energy = 0;
    for(i = 0; i < 4; i++){
        pot = abs(planets[i].x) + abs(planets[i].y) + abs(planets[i].z);
        kin = abs(planets[i].dx) + abs(planets[i].dy) + abs(planets[i].dz);
        energy += pot*kin;
    }

    printf("TOTAL ENERGY: %d\n", energy);

}
