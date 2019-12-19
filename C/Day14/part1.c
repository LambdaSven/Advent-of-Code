#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
struct element{
    char id[5];
    int numOre;
    int minProduced;
};

struct reaction{
    int inAmounts[10];
    int numElements;
    struct element input[10];
    struct element output;
    int outAmount;
};

int main(int argc, char* argv[]){
    FILE *fp = fopen(argv[1], "r");
    
    int elementBuffer = 128;
    int numEl = 0;

    int reactionBuffer = 128;
    int numReactions = 0;
    
    struct element* allElements = malloc(elementBuffer * sizeof(*allElements));
    struct reaction* allReactions = malloc(reactionBuffer * sizeof(*allReactions));
    int i, j, k, l, dupe; 
    int numElements = 0;
    //PARSE INPUT
    while(!feof(fp)){
        dupe = 0;
        int mag;
        char el[6];
        struct element *current;
        if(fscanf(fp, "%d %5[A-Z], ", &mag, el) == 2){
            if(el[5] == ','){
                el[5] = '\0';
            }
            for(i = 0; i < numEl; i++){
                if(strcmp(el, allElements[i].id) == 0){
                    dupe = 1;
                    current = &allElements[i];
                }
            }
            if(dupe != 1){
                strcpy(allElements[numEl].id, el);
                
                if(strcmp(el, "ORE") == 0){
                    allElements[numEl].numOre = 1;
                    allElements[numEl].minProduced = 1;
                } else {
                    allElements[numEl].numOre = 0;
                }
                current = &allElements[numEl];
                numEl++;
            }
        
            allReactions[numReactions].input[numElements] = *current;
            allReactions[numReactions].inAmounts[numElements] = mag;
            numElements++;
        
        } else if(fscanf(fp, "=> %d %5s\n)", &mag, el) == 2){
            strcpy(allElements[numEl++].id, el);
            allReactions[numReactions].outAmount = mag;
            strcpy(allReactions[numReactions].output.id, el);
            allReactions[numReactions].numElements = numElements;
            numReactions++;
            numElements = 0;
        }
    }

    
 
    int solvable;
    int finish = 0;
    int fuelAmount;
    int extra = 0;
    int sumExtra = 0;
    for(i = 0; finish == 0; i = (i+1)%numReactions){
        solvable = 1;
        //check if every element in the equation has a proper value
        for(j = 0; j < allReactions[i].numElements; j++){
            if(allReactions[i].input[j].numOre == 0){
                solvable = 0;
            }
        }
    //if solvable and not already solved
    if(solvable == 1 && allReactions[i].output.numOre == 0){
        printf("*** NEW ELEMENT ***\n");
        int numOres = 0;
        int temp = 0;
        //cycle through all inputs
        for(j = 0; j < allReactions[i].numElements; j++){
            for(k = 0; temp <= allReactions[i].input[j].numOre * allReactions[i].inAmounts[j];k++){
                temp = k * allReactions[i].input[j].minProduced + extra;
            }
            //If the extra was nessecary
            if(temp-extra < allReactions[i].input[j].numOre*allReactions[i].inAmounts[j]){
                //should subtract the extra used from extra
                extra += temp - extra - allReactions[i].input[j].numOre*allReactions[i].inAmounts[j];
                printf("LOST EXTRA\t");
            //didn't use any extra
            } else {
                extra += temp - allReactions[i].input[j].numOre*allReactions[i].inAmounts[j];
                printf("GAINED EXTRA\t");
            }
            temp = allReactions[i].input[j].numOre*allReactions[i].inAmounts[j];
            numOres += temp/allReactions[i].outAmount;
            printf("EXTRA: %d\n", extra);
        }
        sumExtra += extra;
        allReactions[i].output.numOre = numOres;
        allReactions[i].output.minProduced = allReactions[i].outAmount;
        printf("ID %s COSTS %d\n", allReactions[i].output.id, allReactions[i].output.numOre);

        for(j = 0; j < numReactions; j++){
            for(k = 0; k < allReactions[j].numElements; k++){
                if(strcmp(allReactions[i].output.id, allReactions[j].input[k].id)==0){
                    allReactions[j].input[k].numOre = numOres;
                    allReactions[j].input[k].minProduced = allReactions[i].outAmount;
                }
            }
        }
        if(strcmp(allReactions[i].output.id, "FUEL") == 0){
            fuelAmount = numOres;
            finish = 1;
        }
    }
}    
    printf("FUEL: %d\tEXTRA: %d SUMEXTRA: %d\n", fuelAmount, extra, sumExtra);
}





