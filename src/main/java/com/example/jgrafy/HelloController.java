package com.example.jgrafy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label Status;
    @FXML
    private Label CohesiveOutput;
    @FXML
    private TextField Rows;
    @FXML
    private TextField Columns;
    @FXML
    private TextField PathStart;
    @FXML
    private TextField PathEnd;
    @FXML
    private TextField Root;
    int root;
    int rows;
    int cols;
    int pStart;
    int pEnd;
    @FXML
    private RadioButton GenerateFull,GenerateRandom,GenerateCohesive;



    public void addPath(javafx.event.ActionEvent actionEvent)
    {
        try
        {
            int numOfVertices=Main.getGraph().getNumOfColumns()*Main.getGraph().getNumOfRows()-1;
            pStart = Integer.parseInt(PathStart.getText());
            pEnd = Integer.parseInt(PathEnd.getText());
            if(0>pStart || 0>pEnd || pStart>numOfVertices || pEnd>numOfVertices)
            {
                Status.setText("Enter numbers from \nrange 0 to "+numOfVertices);
            }
            else
            {
                Path path=GraphAnalyzer.findShortestPath(pEnd,pStart,Main.getGraph());
                if(path.pathValue==Double.POSITIVE_INFINITY)
                {
                    Status.setText("Sorry, this path\ndo not exist");
                }
                else
                {
                    Status.setText("New Path Added \nfrom " + pStart + " to " + pEnd+"\nvalue "+path.pathValue);
                    //System.out.println("TERaz");
                    //Main.addPath(path);

                }
            }

        }
        catch (NumberFormatException exception)
        {
            Status.setText("Use only numbers\nin start and end fields");
        }
        catch (NullPointerException exception)
        {
            Status.setText("Generate/Read graph first");
        }
        catch(Exception exception)
        {
            Status.setText("Unknown error");
            System.out.println(exception);
        }


    }

    public void checkCohesion(javafx.event.ActionEvent actionEvent) {
        try
        {
            root=Integer.parseInt(Root.getText());
            int numOfVertices=Main.getGraph().getNumOfColumns()*Main.getGraph().getNumOfRows()-1;
            if(root>-1 && root<numOfVertices+1) {
                if (GraphAnalyzer.checkCohesion(Main.getGraph(), root).cohesive)
                    CohesiveOutput.setText("Your graph is cohesive");
                else
                    CohesiveOutput.setText("Your graph is not cohesive");

                Status.setText("Cohesion checked");
            }
            else
            {
                Status.setText("Enter number from \nrange 0 to "+numOfVertices);
            }
        }
        catch(NumberFormatException exception)
        {
            Status.setText("Use only natural \nnumbers in root");
        }
        catch(NullPointerException exception)
        {
            Status.setText("Generate/Read graph first");
        }
        catch(Exception exception)
        {
            Status.setText("Unknown error");
            System.out.println(exception);
        }
    }

    public void openGraph(ActionEvent actionEvent) {
        //setGraph(GraphGenerator.readGraphFromFile(""));
        Status.setText("Graph opened");
    }

    public void generateGraph(ActionEvent actionEvent) {
        try {
            rows=Integer.parseInt(Rows.getText());
            cols=Integer.parseInt(Columns.getText());
            if(rows>0 && cols>0) {
                if (GenerateFull.isSelected()) {
                    Main.setGraph(GraphGenerator.generateCohesiveGraph(rows, cols));
                    Status.setText("Full graph generated");
                } else if (GenerateCohesive.isSelected()) {
                    Main.setGraph(GraphGenerator.generateCohesiveRandomGraph(rows, cols));
                    Status.setText("Cohesive graph generated");
                } else if (GenerateRandom.isSelected()) {
                    Main.setGraph(GraphGenerator.generateRandomGraph(rows, cols));
                    Status.setText("Random graph generated");
                } else {
                    Status.setText("Unknown error\nno radio button selected");
                }
            }
            else Status.setText("Use only natural numbers\nin columns and rows");
        }
        catch (NumberFormatException exception)
        {
            Status.setText("Use only natural numbers\nin columns and rows");
        }
        catch (Exception exception)
        {
            Status.setText("Unknown error");
            System.out.println(exception);
        }
    }

    public void saveGraph(ActionEvent actionEvent) throws IOException {
        try
        {
            Main.getGraph().saveToFile("nowyGraf.txt");
            Status.setText("Graph saved");
        }
        catch(NullPointerException exception)
        {
            Status.setText("Generate/Read graph first");
        }
        catch(Exception exception)
        {
            Status.setText("Unknown error");
            System.out.println(exception);
        }
    }

}