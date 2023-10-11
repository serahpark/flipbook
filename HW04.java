import java.util.*;

class HW04 extends App {
    
    ArrayList<ArrayList<ArrayList<Vector2>>> animation;
    int currIndex;
    boolean playAnimation;
    int framesPerDrawing;
    
    void setup() {
        
        System.out.println("This is a flipbook app!");
        System.out.println("Draw strokes with your mouse, and once your frame has been completed, press S to start a new frame.");
        System.out.println("Press , to move backward along the sequence of drawings and . to move forward.");
        System.out.println("Press X to flip your drawing horizontally and Y to flip vertically.");
        System.out.println("Finally, press P to play and pause your animation");
        System.out.println("Press Q to quit.");
        System.out.println("Press R to restart.");
        
        // initialize a triple-nested ArrayList to store frames consisting of a collection of strokes
        animation = new ArrayList<ArrayList<ArrayList<Vector2>>>();
        animation.add(new ArrayList<ArrayList<Vector2>>());
        
        // keep track of the current frame displayed
        currIndex = 0;
        playAnimation = false;
        int framesPerDrawing = 0;
    }
    
    void loop() {
        
        // display the current drawing
        ArrayList<ArrayList<Vector2>> currDrawing = animation.get(currIndex);
        playAnimation = false;
        
        // mouse operations to add strokes
        if (mousePressed) {
            currDrawing.add(new ArrayList<Vector2>());
            
        } 
        
        if (mouseHeld) {
            int mostRecentStroke = currDrawing.size() - 1;
            currDrawing.get(mostRecentStroke).add(mousePosition);
        }
        
        // key operations
        if (keyPressed('S')) { 
            animation.add(new ArrayList<ArrayList<Vector2>>());
            currIndex += 1;
        }
        
        // controls speed at which the animation plays forward
        if (keyToggled('P')) { 
            if (framesPerDrawing++ > 5) {
                playAnimation = true;
                framesPerDrawing = 0;
            }  
        }
        
        
        if (keyPressed('X')) {
            for (int stroke = 0; stroke < currDrawing.size(); ++stroke) {
                for (int pt = 0; pt < currDrawing.get(stroke).size(); ++pt) {
                    Vector2 point = currDrawing.get(stroke).get(pt);
                    currDrawing.get(stroke).get(pt).x = -currDrawing.get(stroke).get(pt).x;
                }
            }
        }
        
        if (keyPressed('Y')) {
            for (int stroke = 0; stroke < currDrawing.size(); ++stroke) {
                for (int pt = 0; pt < currDrawing.get(stroke).size(); ++pt) {
                    Vector2 point = currDrawing.get(stroke).get(pt);
                    point.y = point.y * -1;
                    currDrawing.get(stroke).set(pt, point);
                }
            }
        }
        
        // allows the animation to cycle through the list in a loop
        if (keyPressed('.') || playAnimation) {
            currIndex = Math.floorMod(currIndex + 1, animation.size());
        }
        
        if (keyPressed(',')) {
            currIndex = Math.floorMod(currIndex - 1, animation.size());
            
        }
        
        // display animation
        for (int i = 0; i < currDrawing.size(); ++i) {
            for (int j = 0; j < currDrawing.get(i).size() - 1; ++j) {
                drawLine(currDrawing.get(i).get(j), currDrawing.get(i).get(j + 1), Vector3.blue);
            }
        }   
    }
    
    public static void main(String[] arguments) {
        App app = new HW04();
        app.setWindowBackgroundColor(1.0, 1.0, 1.0);
        app.setWindowSizeInWorldUnits(8.0, 8.0);
        app.setWindowCenterInWorldUnits(0.0, 0.0);
        app.setWindowHeightInPixels(512);
        app.setWindowTopLeftCornerInPixels(64, 64);
        app.run();
    }
    
}