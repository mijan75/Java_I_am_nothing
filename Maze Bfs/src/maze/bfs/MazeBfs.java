/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze.bfs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */

class Node{
    private State state;
    private Node previousNode;
    private Action action;
    private int steps;

    public Node(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
    
}

enum Action{
    UP,
    DOWN,
    LEFT,
    RIGHT
}

class State{
    int row;
    int col;

    public State(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "State{" + "row=" + row + ", col=" + col + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.row;
        hash = 31 * hash + this.col;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.col != other.col) {
            return false;
        }
        return true;
    }
    
    public State geNextStage(Action action, char maze[][]){
        State nextState = null;
        
        switch (action){
            case UP:
                nextState = new State ((this.row - 1), this.col);
                break;
            case DOWN:
                nextState = new State ((this.row + 1), this.col);
                break;
            case LEFT:
                nextState = new State (this.row, (this.col - 1));
                break;
            case RIGHT:
                nextState = new State (this.row, this.col + 1);
                break;
        }
        if(nextState.getRow() < 0 || nextState.getRow() >= maze.length)
            return null;
        else if(nextState.getCol() < 0 || nextState.getCol() >= maze[0].length)
            return null;
        else if(maze[nextState.getRow()][nextState.getCol()] == '#')
            return null;
        return nextState;
    }
}

public class MazeBfs {
    
    public void printPath(Node currentNode){
        if(currentNode.getPreviousNode() != null){
            printPath(currentNode.getPreviousNode());
            System.out.println( currentNode.getAction() + " " +currentNode.getState().toString());
        }
    }
    
    public MazeBfs() {
        char maze[][];
        try {
            RandomAccessFile input = new RandomAccessFile("maze.in","r");
            String line = input.readLine();
            
            String tokens[] = line.split("\\ ");
            int row = Integer.parseInt(tokens[0]);
            int col = Integer.parseInt(tokens[1]);
            
            maze = new char[row][col];
            
            for(int r=0; r<row; r++){
                line = input.readLine();
                for(int c=0; c<col; c++){
                    maze[r][c] = line.charAt(c);
                }
            }
            
            int sRow = -1, sCol = -1;
            int gRow = -1, gCol = -1;
            
            for(int r=0; r<row; r++){
                for(int c=0; c<col; c++){
                    if(maze[r][c] == 'S'){
                        sRow = r;
                        sCol = c;
                    } 
                    
                    else if(maze[r][c] == 'E'){
                        gRow = r;
                        gCol = c;
                    }
                }
            }
            Queue<Node> frontier = new LinkedList<>();
            Set<State> exploredSet = new HashSet<>();
            
            State initialState = new State(sRow, sCol);
            State goalState = new State(gRow, gCol);
            boolean found = false;
            
            int forntierCount = 0;
            int stateCount = 0;
            
            if(initialState.equals(goalState)){
                System.out.println("Solution Found");
                System.out.println("Number of state in the explore set is " +exploredSet.size());
                System.out.println("Number of Node in the frontier is " + forntierCount);
                System.out.println("Number of state are generated " + ++stateCount );
                found = true;
            }
            

            
            frontier.add(new Node(initialState));
            forntierCount++;
            
            while(!frontier.isEmpty() && !found){
                Node currentNode = frontier.remove();
                State currentState = currentNode.getState();
                exploredSet.add(currentState);
                
                for(Action action: Action.values()){
                    State nextState = currentState.geNextStage(action, maze);
                    
                    if(!frontier.contains(nextState) && !exploredSet.contains(nextState) && nextState != null){
                        stateCount++;
                        Node nextNode = new Node(nextState);
                        nextNode.setPreviousNode(currentNode);
                        nextNode.setAction(action);
                        nextNode.setSteps(currentNode.getSteps() + 1);
                        
                        if(nextState.equals(goalState)){
                            System.out.println("Solution Found");
                            System.out.println("Solution takes " + nextNode.getSteps() + " steps");
                            System.out.println("Number of state in the explore set is " +exploredSet.size());
                            System.out.println("Number of Node in the frontier is " + forntierCount);
                            System.out.println("Number of state are generated " + ++stateCount);
                            
                            
                            printPath(nextNode);
                            found = true;
                            break;
                        }
                        frontier.add(nextNode);
                        forntierCount++;
                    }
                }
                
            }
            if(!found){
                System.out.println("Solution Doesn't exist");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MazeBfs.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MazeBfs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MazeBfs maze = new MazeBfs();
    }
    
}
