package Day06;

import java.util.ArrayList;

public class Planet {
    private Planet parent;
    private String id;

    public Planet(String c){
        id = c;
    }

    public Planet getParent(){
        return parent;
    }

    public void setParent(Planet p){
        parent = p;
    }
    public String getID(){
        return id;
    }

    public void setID(String s){
        id = s;
    }

    public ArrayList<String> getTree(String s){
        Planet c = this;
        ArrayList<String> out = new ArrayList<String>();
        while(!c.getID().equals(s)){
            out.add(c.getID());
            c = c.getParent();
        }
        return out;
    }

    public int distanceTo(String s){
        Planet c = this;
        int count = 0;
        while(!c.getID().equals(s)){
            c = c.getParent();
            count++;
        }
        return count;
    }
}