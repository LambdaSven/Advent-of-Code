package Day17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Conway4 {
  HashSet<FourPoint> FourPoints = new HashSet<FourPoint>();
  HashSet<FourPoint> neighbours = new HashSet<FourPoint>();

  public Conway4(ArrayList<String> in){
    for(int i = 0; i < in.size(); i++){
      for(int j = 0; j < in.get(i).length(); j++){
        if(in.get(i).charAt(j) == '#'){
          FourPoints.add(new FourPoint(j, i, 0, 0));
        }
      }
    }
    calcNeighbours();
  }

  public void run(int n){
    for(int i = 0; i < n; i++){
      HashSet<FourPoint> Pcopy = FourPoints.stream().collect(Collectors.toCollection(HashSet::new));

      for(FourPoint p : FourPoints){
        int adj = getAdj(p);
        if(!(adj == 2 || adj == 3)){
          Pcopy.remove(p);
        }
      }
      for(FourPoint p : neighbours){
        int adj = getAdj(p);
        if(adj == 3){
          Pcopy.add(p);
        }
      }
      FourPoints = Pcopy.stream().collect(Collectors.toCollection(HashSet::new));
      neighbours.clear();
      calcNeighbours();
    }
  }

  private void calcNeighbours(){
    for(FourPoint p : FourPoints){
      for(int i = -1; i < 2; i++){
        for(int j = -1; j < 2; j++){
          for(int k = -1; k < 2; k++){
            for(int l = -1; l < 2; l++){
              if((i == 0 && j == 0 && k == 0 && l == 0) || FourPoints.contains(new FourPoint(p.x+i, p.y+j, p.z+k, p.w+l))){
                continue;
              } else {
                neighbours.add(new FourPoint(p.x+i, p.y+j, p.z+k, p.w+l));
              }
            }
          }
        }
      }
    }
  }

  private int getAdj(FourPoint p){
    int adj = 0;
    for(int i = -1; i < 2; i++){
      for(int j = -1; j < 2; j++){
        for(int k = -1; k < 2; k++){
          for(int l = -1; l < 2; l++){
          if(i == 0 && j == 0 && k == 0 && l == 0)
            continue;
          if(FourPoints.contains(new FourPoint(p.x + i, p.y + j, p.z + k, p.w + l)))
            adj++;
          }
        }
      }
    }
    return adj;
  }

  public int getLive(){
    return FourPoints.size();
  }
}
