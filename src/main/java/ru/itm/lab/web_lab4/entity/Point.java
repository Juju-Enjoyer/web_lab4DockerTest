package ru.itm.lab.web_lab4.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Points")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pointId;
    private Long userId;
    private float xCoordination;
    private float yCoordination;
    private int rSizeGraph;
    private LocalDateTime creationDate;
    private Long executeTime;
    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public float getxCoordination() {
        return xCoordination;
    }

    public void setxCoordination(float xCoordination) {
        this.xCoordination = xCoordination;
    }

    public float getyCoordination() {
        return yCoordination;
    }

    public void setyCoordination(float yCoordination) {
        this.yCoordination = yCoordination;
    }

    public int getrSizeGraph() {
        return rSizeGraph;
    }

    public void setrSizeGraph(int rSizeGraph) {
        this.rSizeGraph = rSizeGraph;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }
}
