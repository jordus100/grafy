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
    public void initialize(){
        ((HBox)(generatorPane.getChildren().get(0))).prefWidthProperty().bind(generatorPane.widthProperty());
        ((HBox)(openSavePane.getChildren().get(0))).prefWidthProperty().bind(openSavePane.widthProperty());
        ((HBox)(cohesionPane.getChildren().get(0))).prefWidthProperty().bind(cohesionPane.widthProperty());
        PathlistPane.prefWidthProperty().bind(PathlistScroll.widthProperty());
        PathlistPane.prefHeightProperty().bind(PathlistScroll.heightProperty());
    }
    public void PlusClicked(javafx.event.ActionEvent actionEvent)
    {
        System.out.println("Plus Clicked");
    }
    public void MinusClicked(javafx.event.ActionEvent actionEvent)
    {
        System.out.println("Minus Clicked");
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
    private void addArrow(AnchorPane graphPane, double circleX, double circleY, double radius, Direction direction, double weight, Color color){
        Line arrowBody = null;
        Polygon arrowPoint = null;
        arrowPoint = new Polygon();
        Label weightLabel = new Label(new DecimalFormat("0.000000").format(weight));
        weightLabel.setFont(Font.font("System", radius*0.35));
        if(direction == Direction.Right){
            arrowBody = new Line(circleX + radius, circleY - 0.25*radius, circleX + 3*radius, circleY - 0.25*radius);
            arrowPoint.getPoints().addAll(arrowBody.getEndX() - radius*0.5, arrowBody.getEndY() - 0.25*radius,
                    arrowBody.getEndX(), arrowBody.getEndY(),
                    arrowBody.getEndX() - radius*0.5, arrowBody.getEndY() + 0.25*radius);
            weightLabel.setLayoutX(arrowBody.getStartX());
            weightLabel.setLayoutY(arrowBody.getStartY() - weightLabel.getFont().getSize() * 1.5);
        }
        if(direction == Direction.Left){
            arrowBody = new Line(circleX - radius, circleY + 0.25*radius, circleX - 3*radius, circleY + 0.25*radius);
            arrowPoint.getPoints().addAll(arrowBody.getEndX() + radius*0.5, arrowBody.getEndY() - 0.25*radius,
                    arrowBody.getEndX(), arrowBody.getEndY(),
                    arrowBody.getEndX() + radius*0.5, arrowBody.getEndY() + 0.25*radius);
            weightLabel.setLayoutX(arrowBody.getEndX() + radius*0.5);
            weightLabel.setLayoutY(arrowBody.getEndY());
            weightLabel.setTextAlignment(TextAlignment.RIGHT);
        }
        if(direction == Direction.Down){
            arrowBody = new Line(circleX + 0.25*radius, circleY + radius, circleX + 0.25*radius, circleY + 3*radius);
            arrowPoint.getPoints().addAll(arrowBody.getEndX() - radius*0.25, arrowBody.getEndY() - 0.5*radius,
                    arrowBody.getEndX(), arrowBody.getEndY(),
                    arrowBody.getEndX() + radius*0.25, arrowBody.getEndY() - 0.5*radius);
            weightLabel.setLayoutX(arrowBody.getStartX() - weightLabel.getFont().getSize());
            weightLabel.setLayoutY(arrowBody.getStartY() + radius*0.5);
            weightLabel.setRotate(90);
        }
        if(direction == Direction.Up){
            arrowBody = new Line(circleX - 0.25*radius, circleY - radius, circleX - 0.25*radius, circleY - 3*radius);
            arrowPoint.getPoints().addAll(arrowBody.getEndX() - radius*0.25, arrowBody.getEndY() + 0.5*radius,
                    arrowBody.getEndX(), arrowBody.getEndY(),
                    arrowBody.getEndX() + radius*0.25, arrowBody.getEndY() + 0.5*radius);
            weightLabel.setLayoutX(arrowBody.getEndX()  - weightLabel.getFont().getSize() * 3);
            weightLabel.setLayoutY(arrowBody.getEndY() + radius);
            weightLabel.setRotate(-90);
            weightLabel.setTextAlignment(TextAlignment.RIGHT);
        }
        weightLabel.setTextFill(Paint.valueOf("red"));
        arrowBody.setFill(color);
        arrowPoint.setFill(color);
        graphPane.getChildren().add(arrowBody);
        graphPane.getChildren().add(arrowPoint);
        graphPane.getChildren().add(weightLabel);
    }


    public void drawPath(Graph graph, AnchorPane graphPane, Path[] paths){
        double vertexRadius;
        double scrollbarWidth = 15;
        double graphPaneWidth = graphPane.getPrefWidth() - scrollbarWidth;
        double graphPaneHeight = graphPane.getPrefHeight() - scrollbarWidth;
        if(graphPane.getPrefWidth() / graphPane.getPrefHeight() < graph.getNumOfColumns() / graph.getNumOfRows()){
            vertexRadius = graphPaneWidth / graph.getNumOfColumns() / 4;
        } else{
            vertexRadius = graphPaneHeight / graph.getNumOfRows() / 4;
        }
        for(int i = 0; i<graph.getNumOfRows(); i++){
            for(int n = 0; n< graph.getNumOfColumns(); n++){
                Circle vertexCircle = new Circle(vertexRadius);
                double circleX = graphPane.getLayoutX() + n * vertexRadius * 4 + vertexRadius;
                double circleY = graphPane.getLayoutY() + i * vertexRadius * 4 + vertexRadius;
                vertexCircle.setCenterX(circleX);
                vertexCircle.setCenterY(circleY);
                vertexCircle.setFill(Paint.valueOf("Black"));

                Label vertexNumLabel = new Label(i*graph.getNumOfColumns() + n + "");
                vertexNumLabel.setLayoutX(circleX - vertexRadius/4);
                vertexNumLabel.setLayoutY(circleY - vertexRadius/4);
                vertexNumLabel.setFont(Font.font("System", vertexRadius/1.5 / (vertexNumLabel.getText().length() + 1) * 2.5));
                vertexNumLabel.setTextFill(Paint.valueOf("White"));

                for(Direction direction : Direction.values()){
                    int neighbor = graph.getNeighbour((i*graph.getNumOfColumns() + n), direction);
                    Color pathColor = Color.BLACK;
                    for(Path path : paths) {
                        for(int x = 0; x<path.verticesInOrder.length; x++)
                            if(path.verticesInOrder[x] == i*graph.getNumOfColumns() + n && path.verticesInOrder[x+1] == neighbor)
                                pathColor = path.color;
                    }
                    if(neighbor >= 0){
                        if(direction == Direction.Right && graph.getVertices()[neighbor].weightRight >= 0)
                            addArrow(graphPane, circleX, circleY, vertexRadius, direction, graph.getVertices()[neighbor].weightRight, pathColor);
                        if(direction == Direction.Left && graph.getVertices()[neighbor].weightLeft >= 0)
                            addArrow(graphPane, circleX, circleY, vertexRadius, direction, graph.getVertices()[neighbor].weightLeft, pathColor);
                        if(direction == Direction.Up && graph.getVertices()[neighbor].weightUp >= 0)
                            addArrow(graphPane, circleX, circleY, vertexRadius, direction, graph.getVertices()[neighbor].weightUp, pathColor);
                        if(direction == Direction.Down && graph.getVertices()[neighbor].weightDown >= 0)
                            addArrow(graphPane, circleX, circleY, vertexRadius, direction, graph.getVertices()[neighbor].weightDown, pathColor);
                    }
                }
                graphPane.getChildren().add(vertexCircle);
                graphPane.getChildren().add(vertexNumLabel);
            }
        }
    }

}