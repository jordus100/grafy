import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }


}

class Path {

    double pathValue;
    int verticesInOrder[];
    double stepsValue[];


}

class Vertex {

    double weightLeft;
    double weightRight;
    double weightUp;
    double weightDown;


}

class Graph {

    int numOfColumns;
    int numOfRows;
    Vertex vertices[];

    public int getNeighbour(int verticeNum, Direction direction) {
        if (direction.compareTo(Direction.Left)==1) {
            if ((verticeNum % numOfColumns)!=0)
                return verticeNum - 1;
            else
                return -1;
        }
        if (direction.compareTo(Direction.Right)==1) {
            if (((verticeNum+1) % numOfColumns)!=0)
                return verticeNum + 1;
            else
                return -1;
        }
        if (direction.compareTo(Direction.Up)==1) {
            if (verticeNum>=numOfColumns)
                return verticeNum + numOfColumns;
            else
                return -1;
        }
        if (direction.compareTo(Direction.Down)==1) {
            if ((verticeNum/numOfColumns)+1!=numOfRows)
                return verticeNum - numOfColumns;
            else
                return -1;
        }
        return -2;
    }


}

enum Direction {
    Left, Right, Up, Down
}

class GraphAnalyzer{

    public Path findShortestPath(int verticeTo, int verticeFrom,Graph graph) {
    Path path=new Path();


    return path;
    }

    public int checkCohesion(Graph graph){


        return 0;
    }
}

class GraphGenerator{


}

class MainUIController{
    JToggleButton toggleButtonSettings;
    JToggleButton toggleButtonGraphView;
    public void handleSwitchWindowPressed(ActionEvent event){

    }
    public void initializeUI(){

    }

}

class PathListUIController{
    Scrollbar scrollBarPathList;
    JCheckBox checkBoxesVisible[];
    Button deleteButtons[];
    public void handlePathListScrollBarMove(ActionEvent event){

    }
    public void initializeUI(){

    }
}

class GraphGeneratorUIController{
    Checkbox checkBoxFull;
    Checkbox checkBoxCohesive;
    Checkbox checkBoxRandom;
    public void initializeUI(){

    }
    public void graphGeneratorPressed(ActionEvent event){

    }
    public void handleFileChooseButtonPressed(ActionEvent event){

    }
    public void handleOpenGraphButtonPressed(ActionEvent event){

    }
}

class GraphViewUIController {
    Button graphVertices[];
    Scrollbar scrollBarVertical;
    Scrollbar scrollBarHorizontal;
    JToggleButton displayFull;
    JToggleButton displayLess;
    public void initializeUI(){

    }
    public void handleVerticalScrollBarMove(ActionEvent event){

    }
    public void handleHorizontalScrollBarMove(ActionEvent event){

    }
    public void handleEnlargeButtonPressed(ActionEvent event){

    }
    public void handleReduceButtonPressed(ActionEvent event){

    }

}
