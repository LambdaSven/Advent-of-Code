#include <stdio.h>
#include <stdlib.h>

struct planet{
    int id, x, y, z, dx, dy, dz;
    int initX, initY, initZ;
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
        planets[i].initX = x;
        planets[i].y = y;
        planets[i].initY = y;
        planets[i].z = z;
        planets[i].initZ = z;
        planets[i].dx = 0;
        planets[i].dy = 0;
        planets[i].dz = 0;
    }
    
    long long cycle;
    long long rx, ry, rz;
    rx = 0;
    ry = 0;
    rz = 0;
    for(cycle = 1; rx == 0 || ry == 0 || rz == 0; cycle++){
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
    
        if(planets[0].x == planets[0].initX && planets[0].dx == 0 && rx == 0
        && planets[1].x == planets[1].initX && planets[1].dx == 0
        && planets[2].x == planets[2].initX && planets[2].dx == 0
        && planets[3].x == planets[3].initX && planets[3].dx == 0){
            rx = cycle;
        }
        if(planets[0].y == planets[0].initY && planets[0].dy == 0 && ry == 0
        && planets[1].y == planets[1].initY && planets[1].dy == 0
        && planets[2].y == planets[2].initY && planets[2].dy == 0
        && planets[3].y == planets[3].initY && planets[3].dy == 0){
            ry = cycle;
        }
        if(planets[0].z == planets[0].initZ && planets[0].dz == 0 && rz == 0
        && planets[1].z == planets[1].initZ && planets[1].dz == 0
        && planets[2].z == planets[2].initZ && planets[2].dz == 0
        && planets[3].z == planets[3].initZ && planets[3].dz == 0){
            rz = cycle;
        }
    }
    printf("rx: %lld\try: %lld\trz: %lld\ncalculate LCM with wolfram alpha :P\n", rx, ry, rz);
}
