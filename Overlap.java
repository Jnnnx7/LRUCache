import java.util.InputMismatchException;
import java.util.Scanner;

public class Overlap {
    public static void main(String[] args) {
       Line line1 = new Line(1,7);
       Line line2 = new Line(1,3);

        System.out.println(intersect(line1,line2));
    }

    static class Line{
        float start;
        float end;

        Line(float pStart, float pEnd){
            start = pStart;
            end = pEnd;
        }
    }


    public static boolean intersect(Line line1, Line line2){
        if(line1.start <= line2.start){
            if(line1.end >= line2.start) return true;
            else return false;
        }else{
            if(line2.end >= line1.start) return true;
            else return false;
        }
    }


}
