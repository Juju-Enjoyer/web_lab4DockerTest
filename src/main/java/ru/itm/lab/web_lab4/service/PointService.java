package ru.itm.lab.web_lab4.service;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.itm.lab.web_lab4.entity.Point;
import ru.itm.lab.web_lab4.repository.PointRepository;
import ru.itm.lab.web_lab4.repository.UserRepository;

import javax.xml.crypto.Data;
import java.io.InvalidObjectException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PointService {
    private final Validation validation;
    private final JwtService jwtService;
    private final PointRepository pointRepository;
    private final UserRepository userRepository;

    public List<Point> getAllPointsById(String jwt) {
        Long id = Long.parseLong(jwtService.extractUserId(jwt.substring(7)));
        List<Point> points = pointRepository.findAllByUserId(id);
        if (points.isEmpty()) {
            throw new NullPointerException();
        } else {
            return points;
        }
    }

    public Point savePoint(String jwtToken, Point point){
        if(!validation.coordinationIsValid(point.getxCoordination(),point.getyCoordination(),point.getrSizeGraph())){
            throw new IllegalArgumentException();
        }
        Long userId = Long.parseLong(jwtService.extractUserId(jwtToken.substring(7)));
        Instant startTime = Instant.now();
        point.setUserId(userId);
        point.setCreationDate(LocalDateTime.now());
        Instant endTime = Instant.now();
        point.setExecuteTime(Duration.between(startTime, endTime).toMillis());
        point.setResult(Validation.areaCheck(point.getxCoordination(),point.getyCoordination(),point.getrSizeGraph()));
        pointRepository.save(point);
        return point;
    }
    public void deletePoint(Point point){
        pointRepository.deleteById(point.getPointId());
    }
    public void deletePoints(String token){
        Long id = Long.parseLong(jwtService.extractUserId(token));
        pointRepository.deleteAllByUserId(id);
    }

}
