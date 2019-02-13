import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class myCanvas extends JPanel {
    private int index = 0;
    private Point[] arr = new Point[100000];
   
    ArrayList<Point> points = new ArrayList();
    Point oldpos = new Point();
    
    
    public myCanvas(guiFrame fr) {
        super();
        index = 0;
        this.addMouseListener(new CanvasControl());
        this.addMouseMotionListener(new CanvasControl());
        
        setBackground(Color.white);
      
       fr.setExtendedState(JFrame.MAXIMIZED_BOTH); 
       fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       fr.setVisible(true);
        fr.add(this);
       

    }
  
	@Override
	public void paintComponent(Graphics g) {
		
		
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(20));
        g2.setColor(Color.black);
      
        for (int i = 0; i < index -1; i++) {
            //g2.drawLine(points.get(arr[i].x).x, points.get(arr[i].y).y, points.get(arr[i +1].x).x,points.get(arr[i +1].y).y);
       
     	///Point line = points.get(i); 
//        
      //g2.drawLine(line.x, line.y, line.x+1, line.y+1);
        	int linex = points.get(i).x; 
        	int liney = points.get(i).y;
        	int line2x = points.get(i ).x;
        	int line2y = points.get(i).y;
        
        g2.drawLine(linex, liney, line2x, line2y);
        
         
        	}	
        } 
	
	class CanvasControl extends MouseAdapter{
@Override
    public void mouseDragged(MouseEvent e) {
    	
    	arr[index] = new Point(e.getX(), e.getY());
    	points.add(new Point(e.getX(), e.getY()));
        index++;
        repaint();
       
        
    }
@Override
    public void mousePressed(MouseEvent e) {
    	
    	
    	
    	arr[index] = new Point(e.getX(), e.getY());
    	points.add(new Point(e.getX(), e.getY()));
        index++;
        repaint();
        
        
       
        
        
    }
    
  
	}
	 public void clear() {
	    	
	    	index = 0;
	    	points.clear();
	    	repaint();
	    }
} 