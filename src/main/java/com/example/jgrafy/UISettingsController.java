package com.example.jgrafy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.text.DecimalFormat;

public class UISettingsController {
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
    @FXML
    private Pane generatorPane;
    @FXML
    private Pane openSavePane;
    @FXML
    private Pane cohesionPane;
    int root;
    int rows;
    int cols;
    int pStart;
    int pEnd;
    @FXML
    private RadioButton GenerateFull,GenerateRandom,GenerateCohesive;

    @FXML
    private AnchorPane PathlistPane;
    @FXML
    private ScrollPane PathlistScroll;
    public void initialize() {
        ((HBox) (generatorPane.getChildren().get(0))).prefWidthProperty().bind(generatorPane.widthProperty());
        ((HBox) (openSavePane.getChildren().get(0))).prefWidthProperty().bind(openSavePane.widthProperty());
        ((HBox) (cohesionPane.getChildren().get(0))).prefWidthProperty().bind(cohesionPane.widthProperty());
        PathlistPane.prefWidthProperty().bind(PathlistScroll.widthProperty());
        PathlistPane.prefHeightProperty().bind(PathlistScroll.heightProperty());
    }
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
                    path.color = Color.GREEN;
                    Main.addPath(path);
                    System.out.println("spadłem z ");
                    drawPathlist(Main.getGraph(),PathlistPane,new Path[0]);
                    System.out.println("rowerka :(");
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
            root=0;

                if (GraphAnalyzer.checkCohesion(Main.getGraph(), root).cohesive)
                    CohesiveOutput.setText("Your graph is cohesive");
                else
                    CohesiveOutput.setText("Your graph is not cohesive");

                Status.setText("Cohesion checked");


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
        //
        // Status.setText(generatorHbox.getWidth() + "");
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
    private void drawPath(AnchorPane PathlistPane, double blockX, double blockY, int verticeFrom,int verticeTo,double pathValue, Color color){
        Line separator = null;
        //Polygon arrowPoint = null;
        //arrowPoint = new Polygon();
        Label fromLabel = new Label(String.valueOf(pathValue));
        Label toLabel = new Label(String.valueOf(pathValue));
        Label valueLabel = new Label(new DecimalFormat("0.000000").format(pathValue));
        fromLabel.setFont(Font.font("System", 2));
        toLabel.setFont(Font.font("System", 2));
        valueLabel.setFont(Font.font("System", 2));

            separator = new Line(blockX , blockY-100, blockX + 100, blockY - 100);
            //arrowPoint.getPoints().addAll(separator.getEndX() - radius*0.5, separator.getEndY() - 0.25*radius,
                    //separator.getEndX(), separator.getEndY(),
                    //separator.getEndX() - radius*0.5, separator.getEndY() + 0.25*radius);

        fromLabel.setLayoutX(blockX+10);
        fromLabel.setLayoutY(blockY-50);
        toLabel.setLayoutX(blockX+60);
        toLabel.setLayoutY(blockY-50);
        valueLabel.setLayoutX(blockX+110);
        valueLabel.setLayoutY(blockY-50);

        fromLabel.setTextFill(Paint.valueOf("black"));
        fromLabel.setTextFill(Paint.valueOf("black"));
        fromLabel.setTextFill(Paint.valueOf("black"));
        separator.setFill(color);
        //arrowPoint.setFill(color);
        PathlistPane.getChildren().add(separator);
        //PathlistPane.getChildren().add(arrowPoint);
        PathlistPane.getChildren().add(fromLabel);
        PathlistPane.getChildren().add(toLabel);
        PathlistPane.getChildren().add(valueLabel);
    }


    public void drawPathlist(Graph graph, AnchorPane graphPane, Path[] paths) {
        double vertexRadius;
        double scrollbarWidth = 15;
        double graphPaneWidth = graphPane.getPrefWidth() - scrollbarWidth;
        double graphPaneHeight = graphPane.getPrefHeight() - scrollbarWidth;

        for (int i = 0; i < paths.length; i++) {
            double blockX = graphPane.getLayoutX();
            double blockY = graphPane.getLayoutY() - i * 100;

                drawPath(PathlistPane, blockX, blockY, paths[i].verticesInOrder[0], paths[i].verticesInOrder[-1], paths[i].pathValue, Color.valueOf("Black"));

            }
        }
    }

