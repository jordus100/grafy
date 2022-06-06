package com.example.jgrafy;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class VertexFx extends Circle {
    public VertexFx(double x, double y, double r){
        super();
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(r);
        this.setFill(Paint.valueOf("Black"));
    }
}
