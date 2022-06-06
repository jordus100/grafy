package com.example.jgrafy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

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

    private void addArrow(AnchorPane graphPane, double circleX, double circleY, double radius, Direction direction){
        Line arrowBody = null;
        Polygon arrowPoint = null;
        arrowPoint = new Polygon();
        if(direction == Direction.Right){
            arrowBody = new Line(circleX + radius, circleY - 0.25*radius, circleX + 3*radius, circleY - 0.25*radius);
            arrowPoint.getPoints().addAll(new Double[]{
                    arrowBody.getEndX() - radius*0.5, arrowBody.getEndY() - 0.25*radius,
                    arrowBody.getEndX(), arrowBody.getEndY(),
                    arrowBody.getEndX() - radius*0.5, arrowBody.getEndY() + 0.25*radius
            });
        }
        if(direction == Direction.Left){
            arrowBody = new Line(circleX - radius, circleY + 0.25*radius, circleX - 3*radius, circleY + 0.25*radius);
            arrowPoint.getPoints().addAll(new Double[]{
                    arrowBody.getEndX() + radius*0.5, arrowBody.getEndY() - 0.25*radius,
                    arrowBody.getEndX(), arrowBody.getEndY(),
                    arrowBody.getEndX() + radius*0.5, arrowBody.getEndY() + 0.25*radius
            });
        }
        if(direction == Direction.Down){
            arrowBody = new Line(circleX + 0.25*radius, circleY + radius, circleX + 0.25*radius, circleY + 3*radius);
            arrowPoint.getPoints().addAll(new Double[]{
                    arrowBody.getEndX() - radius*0.25, arrowBody.getEndY() - 0.5*radius,
                    arrowBody.getEndX(), arrowBody.getEndY(),
                    arrowBody.getEndX() + radius*0.25, arrowBody.getEndY() - 0.5*radius
            });
        }
        if(direction == Direction.Up){
            arrowBody = new Line(circleX - 0.25*radius, circleY - radius, circleX - 0.25*radius, circleY - 3*radius);
            arrowPoint.getPoints().addAll(new Double[]{
                    arrowBody.getEndX() - radius*0.25, arrowBody.getEndY() + 0.5*radius,
                    arrowBody.getEndX(), arrowBody.getEndY(),
                    arrowBody.getEndX() + radius*0.25, arrowBody.getEndY() + 0.5*radius
            });
        }
        graphPane.getChildren().add(arrowBody);
        graphPane.getChildren().add(arrowPoint);
    }

    public void MinusClicked(ActionEvent actionEvent) {
        Graph graph = Main.getGraph();
        double vertexRadius;
        double scrollbarWidth = 15;
        double graphPaneWidth = graphPane.getWidth() - scrollbarWidth;
        double graphPaneHeight = graphPane.getHeight() - scrollbarWidth;
        if(graphPane.getWidth() / graphPane.getHeight() < graph.getNumOfColumns() / graph.getNumOfRows()){
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
                    if(neighbor >= 0){
                        if(direction == Direction.Right && graph.getVertices()[neighbor].weightRight >= 0)
                            addArrow(graphPane, circleX, circleY, vertexRadius, direction);
                        if(direction == Direction.Left && graph.getVertices()[neighbor].weightLeft >= 0)
                            addArrow(graphPane, circleX, circleY, vertexRadius, direction);
                        if(direction == Direction.Up && graph.getVertices()[neighbor].weightUp >= 0)
                            addArrow(graphPane, circleX, circleY, vertexRadius, direction);
                        if(direction == Direction.Down && graph.getVertices()[neighbor].weightDown >= 0)
                            addArrow(graphPane, circleX, circleY, vertexRadius, direction);
                    }
                }
                graphPane.getChildren().add(vertexCircle);
                graphPane.getChildren().add(vertexNumLabel);
            }
        }
    }

    public void PlusClicked(ActionEvent actionEvent) {

    }
}
