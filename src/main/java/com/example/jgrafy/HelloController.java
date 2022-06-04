package com.example.jgrafy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    int rows;
    int cols;
    int pStart;
    int pEnd;
    @FXML
    private Label welcomeText;



    public void addPath(javafx.event.ActionEvent actionEvent)
    {
        try
        {
            int numOfVertices=Main.getGraph().getNumOfColumns()*Main.getGraph().getNumOfRows()-1;
            pStart = Integer.parseInt(PathStart.getText());
            pEnd = Integer.parseInt(PathEnd.getText());
            if(0>pStart || 0>pEnd || pStart>numOfVertices || pEnd>numOfVertices)
            {
                Status.setText("Use numbers from range\n0 to "+numOfVertices);
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
                    Status.setText("New Path Added \nfrom " + pStart + " to " + pEnd);
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
        catch (Exception exception)
        {
            System.out.println(exception);
        }
        //System.out.println();


    }

    public void checkCohesion(javafx.event.ActionEvent actionEvent) {
        System.out.println("Cohesion");
        System.out.println("Cohesive? "+GraphAnalyzer.checkCohesion(Main.getGraph(),0).cohesive);
    }

    public void openGraph(ActionEvent actionEvent) {
        System.out.println("OpenGraph");
        //setGraph(GraphGenerator.readGraphFromFile(""));
    }

    public void generateGraph(ActionEvent actionEvent) {
        System.out.println("Generacja");
        Main.setGraph(GraphGenerator.generateCohesiveGraph(10,10));
    }

    public void saveGraph(ActionEvent actionEvent) throws IOException {
        System.out.println("Zapis");
        Main.getGraph().saveToFile("nowyGraf.txt");
    }

}