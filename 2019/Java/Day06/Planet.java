package Day06;

import java.util.ArrayList;

/**
 * A planet is a simply a node that has an identifier and a reference to it's 
 * parent Planet. This essentially creates a backwards tree where we can start 
 * at any node and work up to the root
 */
public class Planet {
    private Planet parent;
    private String id;

    /**
     * Constructor sets ID, we will set parent with a dedicated method
     * @param c The ID of the planet
     */
    public Planet(String c){
        id = c;
    }

    /**
     * Gets the Parent of the Planet
     * @return Planet - The parent
     */
    public Planet getParent(){
        return parent;
    }

    /**
     * Sets the parent of this Planet
     * @param p The parent Planet 
     */
    public void setParent(Planet p){
        parent = p;
    }

    /**
     * Returns the ID of this planet
     * @return The ID of this Planet
     */
    public String getID(){
        return id;
    }

    /**
     * Sets the ID of this planet
     * @param s The new ID of this planet
     */
    public void setID(String s){
        id = s;
    }

    /**
     * Returns and ArrayList<String> of all the identifiers up
     * the tree of Planets until it finds the Planet that matches the identifier
     * @param s - The planet to end the list at
     * @return ArrayList<String> - All the planets from this one → the input paramater
     */
    public ArrayList<String> getTree(String s){
        Planet c = this;
        ArrayList<String> out = new ArrayList<String>();
        //Just keep moving c up the tree until we find the planet we're looking for
        while(!c.getID().equals(s)){
            out.add(c.getID());
            c = c.getParent();
        }
        return out;
    }

    /**
     * Traverses up the tree counting until it reaches the destination Planet
     * @param s - The ID of the destination Planet
     * @return int - the number of nodes traversed to reach the destination Planet
     */
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