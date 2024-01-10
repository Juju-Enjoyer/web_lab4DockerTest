package ru.itm.lab.web_lab4.service;

import org.springframework.stereotype.Service;

import static java.lang.Math.abs;

@Service
public class Validation {
    public boolean coordinationIsValid(float x, float y, int r) {
        if (x < -5 || x > 3) {
            return false;
        } else if (y < -3 || y > 3) {
            return false;
        } else if (r < 0 || r > 3) {
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean areaCheck(float x, float y, int r) {
        return isOrangeZone(x, y, r) ||
                isBlueZone(x, y, r) ||
                isRedZone(x, y, r);
    }

    public static boolean isOrangeZone(float x, float y, float r) {
        return x >= 0 && x <= r && y >= 0 && y <= r && (x * x + y * y <= (r) * (r));
    }

    public static boolean isBlueZone(float x, float y, float r) {
        return x <= 0 && x >= -r && y >= 0 && y <= r;
    }

    public static boolean isRedZone(float x, float y, float r) {
        return x <= 0 && x >= -r / 2 && y <= 0 && y >= -r && x >= -(r - abs(y)) / 2;
    }
}
