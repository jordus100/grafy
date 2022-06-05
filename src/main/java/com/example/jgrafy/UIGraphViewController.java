package com.example.jgrafy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class UIGraphViewController {

    @FXML
    private AnchorPane graphPane;
    @FXML
    private ScrollPane graphScroll;
    @FXML
    public void initialize(){
        graphPane.prefWidthProperty().bind(graphScroll.widthProperty());
        graphPane.prefHeightProperty().bind(graphScroll.heightProperty());
    }


    public void MinusClicked(ActionEvent actionEvent) {
        System.out.println(graphPane.prefWidthProperty());
        System.out.println(graphPane.getWidth());
        Graph graph = Main.getGraph();
        int biggerDimension = graph.getNumOfColumns() > graph.getNumOfRows() ? graph.getNumOfColumns() : graph.getNumOfRows();
        double vertexRadiusGuess = graphPane.getWidth() < graphPane.getHeight() ? graphPane.getWidth()/(2 * biggerDimension) : graphPane.getHeight()/(2*biggerDimension);
        double vertexRadius = graphPane.getWidth() - graph.getNumOfColumns() * vertexRadiusGuess * 2 > graphPane.getHeight() - graph.getNumOfRows() * vertexRadiusGuess * 2 ?
                vertexRadiusGuess * graphPane.getWidth() / (graph.getNumOfColumns() * vertexRadiusGuess * 2):
                vertexRadiusGuess * graphPane.getHeight() / (graph.getNumOfRows() * vertexRadiusGuess * 2);
        for(int i = 0; i<graph.getNumOfVertices(); i++){
            Circle vertexCircle = new Circle(vertexRadius/2);
            double x = graphPane.getLayoutX() + vertexRadius*0.5 + (i - graph.getNumOfColumns()*(i / graph.getNumOfColumns())) * vertexRadius*2;
            double y = graphPane.getLayoutY() + vertexRadius*0.5 + (i/ graph.getNumOfColumns()) * vertexRadius * 2;
            vertexCircle.setCenterX(x);
            vertexCircle.setCenterY(y);
            vertexCircle.setFill(Paint.valueOf("Black"));
            graphPane.getChildren().add(vertexCircle);
        }
    }

    public void PlusClicked(ActionEvent actionEvent) {

    }
}
