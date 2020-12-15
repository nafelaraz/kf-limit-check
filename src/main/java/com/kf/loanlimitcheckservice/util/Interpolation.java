package com.kf.loanlimitcheckservice.util;

public class Interpolation {
    public static Double linearInterpolation(double x1, double y1, double x2, double y2, double x) {
        return (y1 + ((x - x1) / (x2 - x1)) * (y2 - y1));
    }
}
