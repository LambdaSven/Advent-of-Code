package Day17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Conway {
  HashSet<Point> points = new HashSet<Point>();
  HashSet<Point> neighbours = new HashSet<Point>();

  public Conway(ArrayList<String> in){
    for(int i = 0; i < in.size(); i++){
      for(int j = 0; j < in.get(i).length(); j++){
        if(in.get(i).charAt(j) == '#'){
          points.add(new Point(j, i, 0));
        }
      }
    }
    calcNeighbours();
  }

  public void run(int n){
    for(int i = 0; i < n; i++){
      HashSet<Point> Pcopy = points.stream().collect(Collectors.toCollection(HashSet::new));

      for(Point p : points){
        int adj = getAdj(p);
        if(!(adj == 2 || adj == 3)){
          Pcopy.remove(p);
        }
      }
      for(Point p : neighbours){
        int adj = getAdj(p);
        if(adj == 3){
          Pcopy.add(p);
        }
      }
      points = Pcopy.stream().collect(Collectors.toCollection(HashSet::new));
      neighbours.clear();
      calcNeighbours();
    }
  }

  private void calcNeighbours(){
    for(Point p : points){
      for(int i = -1; i < 2; i++){
        for(int j = -1; j < 2; j++){
          for(int k = -1; k < 2; k++){
            if((i == 0 && j == 0 && k == 0) || points.contains(new Point(p.x+i, p.y+j, p.z+k))){
              continue;
            } else {
              neighbours.add(new Point(p.x+i, p.y+j, p.z+k));
            }
          }
        }
      }
    }
  }

  private int getAdj(Point p){
    int adj = 0;
    for(int i = -1; i < 2; i++){
      for(int j = -1; j < 2; j++){
        for(int k = -1; k < 2; k++){
          if(i == 0 && j == 0 && k == 0)
            continue;
          if(points.contains(new Point(p.x + i, p.y + j, p.z + k)))
            adj++;
        }
      }
    }
    return adj;
  }

  public int getLive(){
    return points.size();
  }
}
