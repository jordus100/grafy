package com.example.jgrafy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.text.DecimalFormat;

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
        arrowBody.setStroke(color);
        arrowPoint.setFill(color);
        graphPane.getChildren().add(arrowBody);
        graphPane.getChildren().add(arrowPoint);
        graphPane.getChildren().add(weightLabel);
    }

    public void drawGraph(Graph graph, AnchorPane graphPane, Path[] paths){
        System.out.println(graph.getVertices()[61].weightLeft);
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
                    int vertex = i*graph.getNumOfColumns() + n;
                    Color pathColor = Color.BLACK;
                    for(Path path : paths) {
                        for(int x = 0; x<path.verticesInOrder.length - 1; x++)
                            if(path.verticesInOrder[x] == i*graph.getNumOfColumns() + n && path.verticesInOrder[x+1] == neighbor)
                                pathColor = path.color;
                    }
                    if(neighbor >= 0){
                        if(direction == Direction.Right && graph.getVertices()[vertex].weightRight >= 0)
                            addArrow(graphPane, circleX, circleY, vertexRadius, direction, graph.getVertices()[vertex].weightRight, pathColor);
                        if(direction == Direction.Left && graph.getVertices()[vertex].weightLeft >= 0)
                            addArrow(graphPane, circleX, circleY, vertexRadius, direction, graph.getVertices()[vertex].weightLeft, pathColor);
                        if(direction == Direction.Up && graph.getVertices()[vertex].weightUp >= 0)
                            addArrow(graphPane, circleX, circleY, vertexRadius, direction, graph.getVertices()[vertex].weightUp, pathColor);
                        if(direction == Direction.Down && graph.getVertices()[vertex].weightDown >= 0)
                            addArrow(graphPane, circleX, circleY, vertexRadius, direction, graph.getVertices()[vertex].weightDown, pathColor);
                    }
                }
                graphPane.getChildren().add(vertexCircle);
                graphPane.getChildren().add(vertexNumLabel);
            }
        }
    }



    public void MinusClicked(ActionEvent actionEvent) {
        graphPane.prefWidthProperty().unbind();
        graphPane.prefHeightProperty().unbind();
        graphPane.getChildren().clear();
        graphPane.setPrefWidth(graphPane.getPrefWidth() / 1.5);
        graphPane.setPrefHeight(graphPane.getPrefHeight() / 1.5);
        drawGraph(Main.getGraph(), graphPane, Main.getPath() == null ? new Path[0] : Main.getPath().toArray(new Path[0]));
    }

    public void PlusClicked(ActionEvent actionEvent) {
        graphPane.prefWidthProperty().unbind();
        graphPane.prefHeightProperty().unbind();
        graphPane.getChildren().clear();
        graphPane.setPrefWidth(graphPane.getPrefWidth() * 1.5);
        graphPane.setPrefHeight(graphPane.getPrefHeight() * 1.5);
        drawGraph(Main.getGraph(), graphPane, Main.getPath() == null ? new Path[0] : Main.getPath().toArray(new Path[0]));
    }

    public void DisplayClicked(ActionEvent actionEvent){
        graphPane.prefWidthProperty().bind(graphScroll.widthProperty());
        graphPane.prefHeightProperty().bind(graphScroll.heightProperty());
        graphPane.getChildren().clear();
        drawGraph(Main.getGraph(), graphPane, Main.getPath() == null ? new Path[0] : Main.getPath().toArray(new Path[0]));
    }
}
