#include <stdio.h>

int main(int argc, char* argv[]){
    FILE *fp = fopen(argv[1], "r");
    int start = 0;
    int end = 0;
    fscanf(fp, "%d-%d", &start, &end);
    int numPass = 0;
    int i;
    for(i = start; i <= end; i++){
        int num[6];
        int num_current = i;//+start;
        
        num[5] = num_current/100000;
        num[4] = num_current/10000 % 10; 
        num[3] = num_current/1000 % 10;
        num[2] = num_current/100 % 10;
        num[1] = num_current/10 % 10;
        num[0] = num_current % 10;
        int j;
        short valid = 1;
        for(j = 0; j < 5; j++){
            if(num[j] >= num[j+1]){
                continue;
            } else {
                valid = 0;
            }
        }
        int numDupes = 0;
        if(valid == 1){
            for(j = 0; j < 5; j++){
                if(num[j] == num[j+1]){
                    numDupes++;   
                }
            }
        }
        if(numDupes != 0){
            numPass++;
        }
    }
    printf("%d\n", numPass);
}
