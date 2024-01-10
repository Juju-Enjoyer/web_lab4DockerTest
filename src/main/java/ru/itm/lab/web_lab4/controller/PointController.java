package ru.itm.lab.web_lab4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itm.lab.web_lab4.entity.Point;
import ru.itm.lab.web_lab4.security.errorMessage;
import ru.itm.lab.web_lab4.service.PointService;

@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
@CrossOrigin( origins = "http://localhost:3000")
public class PointController {
    private final PointService pointService;

    @GetMapping("/points")
    public ResponseEntity<?> getAllPointsByUserId(@RequestHeader("Authorization") String token) {
        try {
            return ResponseEntity.ok(pointService.getAllPointsById(token));
        } catch (NullPointerException e) {
            return ResponseEntity.ok().build();
            /*HttpStatus.NOT_FOUND).body(new problemResponse("Any points not found"))*/
        }
    }

    @PostMapping("/points")
    public ResponseEntity<?> createPoint(@RequestBody Point point, @RequestHeader("Authorization") String token) {
        if(point==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new errorMessage("Bad Request"));
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pointService.savePoint(token, point));
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new errorMessage("Incorrect coordination"));
        }
    }

    @DeleteMapping("/point")
    public ResponseEntity<?> deletePoint(@RequestBody Point point, @RequestHeader("Authorization") String token) {
        if(point==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Point null");
        };
        pointService.deletePoint(point);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deletion successful");
    }

    @DeleteMapping("/points")
    public ResponseEntity<?> deletePoints(@RequestHeader("Authorization") String token) {
        pointService.deletePoints(token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deletion successful");
    }

}
