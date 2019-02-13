import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class myCanvas extends JPanel implements MouseListener, MouseMotionListener {
    private int index = 0;
    private Point[] arr = new Point[100000];
    private final guiFrame guiFrame;
    ArrayList<Point> points = new ArrayList();
    Point oldpos = new Point();
    public myCanvas(guiFrame fr) {
        super();
        index = 0;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.guiFrame = fr;
       fr.add(this);
       fr.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        fr.setBackground(Color.white);
     fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
       
        
//        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
//        Graphics2D graphics2D = image.createGraphics();
//        fr.paint(graphics2D);
//        try {
//			ImageIO.write(image,"jpeg", new File("Practice.jpeg"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

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
	
	

    public void mouseDragged(MouseEvent e) {
    	
    	arr[index] = new Point(e.getX(), e.getY());
    	points.add(new Point(e.getX(), e.getY()));
        index++;
        repaint();
       
        
    }
    public void mousePressed(MouseEvent e) {
    	
    	
    	
    	arr[index] = new Point(e.getX(), e.getY());
    	points.add(new Point(e.getX(), e.getY()));
        index++;
        repaint();
        
        
       
        
        
    }
    public void mouseReleased(MouseEvent e)
    {
    	
       
    }
    
    public void clear() {
    	
    	index = 0;
    	points.clear();
    	repaint();
    }

    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
   
    public void mouseMoved(MouseEvent e) {}

	


    
} 