// Dan Zapotoczny
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

class Target {				// I added color, which determines the color of the point, line, which determines whether the point is connected
	private int x,x1,x2;			// by a line, and pointsize, which determines the size of the point, as characteristics of the point object
	private int y,y1,y2;
	private int r,r1,r2;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	
	public int getR1() {
		return r1;
	}
	public void setR1(int r1) {
		this.r1 = r1;
	}
	public int getR2() {
		return r2;
	}
	public void setR2(int r2) {
		this.r2 = r2;
	}
	public boolean within(int x, int y) {
		if(x >= getX() && y >= getY() && x <= (getX()+2*getR()) && y <= (getY()+2*getR())){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean within1(int x, int y) {
		if(x >= getX1() && y >= getY1() && x <= (getX1()+2*getR1()) && y <= (getY1()+2*getR1())){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean within2(int x, int y) {
		if(x >= getX2() && y >= getY2() && x <= (getX2()+2*getR2()) && y <= (getY2()+2*getR2())){
			return true;
		}
		else {
			return false;
		}
	}
	public Target(int x, int y, int r, int x1, int y1, int r1, int x2, int y2, int r2) {
		setX(x);
		setY(y);
		setR(r);
		setX1(x1);
		setY1(y1);
		setX2(x2);
		setY2(y2);
		setR1(r1);
		setR2(r2);
	}
	@Override
	public String toString() {
		return String.format("%d %d %d %d %d %d %d %d %d",x,y,r,x1,y1,r1,x2,y2,r2);
	}
}
class LinePanel extends JPanel implements MouseMotionListener,MouseListener,KeyListener,ActionListener {
	private int cx,cy;					//variable which stores current location of the mouse
	private ArrayList<Target> targets;
	private String message;
	private Color color;
	private int r,score=0;	
	private LineFrame lfram;
	private Timer tim3;
	private int count = 0,timeRemaining=30;
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_R) {
			color = Color.RED;
		} else if (e.getKeyCode() == KeyEvent.VK_B) {
			color = Color.BLUE;
		}
		else if (e.getKeyCode() == KeyEvent.VK_L) {
			color = Color.BLACK;
		}
		else if (e.getKeyCode() == KeyEvent.VK_G) {
			color = Color.GREEN;
		}
		else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {	//detects if escape was pressed
												//disables the drawing of the line
		//setEsc(true);										//sets esc variable to true which shows that escape was pressed
		}
		repaint();
	}
	public void setTim3(Timer tim3) {
		this.tim3 = tim3;
	}
	public Timer getTim3() {
		return tim3;
	}
	public void startTim3() {
		setTim3(lfram.getTim3());
		getTim3().start();
		lfram.setTim3(getTim3());
	}
	public void stopTim3() {
		setTim3(lfram.getTim3());
		getTim3().stop();
		lfram.setTim3(getTim3());
	}
	public int getCx() {				
		return cx;
	}
	public void setCx(int cx) {
		this.cx = cx;
	}
	public int getCy() {
		return cy;
	}
	public void setCy(int cy) {
		this.cy = cy;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color col) {
		color = col;
	}
	public int getR() {
		return r;
	}
	public void setPointSize(int r) {
		this.r = r;
		
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTimeRemaining() {
		return timeRemaining;
	}
	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {	//gets current location
		int x = e.getX();
		int y = e.getY();
//		lfram.getTim3().stop();
		for(Target t : targets) {
			if(t.within2(x, y)) {
				targets.remove(t);
				setScore((1/t.getR2())*300);
				repaint();
			}else if(t.within1(x, y)) {
				targets.remove(t);
				setScore((1/t.getR1())*200);
				repaint();
			}else if(t.within(x, y)) {
				targets.remove(t);
				setScore((1/t.getR())*100);
				repaint();
			}
		}
//		lfram.getTim3().start();
		requestFocusInWindow();
		repaint();
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e) {
		setCx(e.getX());
		setCy(e.getY());
		message = String.format("Location: (%d, %d)", getCx(), getCy());
		requestFocusInWindow();
		repaint();
	}
	public void mouseDragged(MouseEvent e) {	//same description as mouseClicked() above
		/*Color c = getColor();
		boolean l = getLine();
		int p = getPointSize();
		points.add(new Point(e.getX(),e.getY(),c,l,p));
		if(getEsc()) {
			setLine(true);
			setEsc(false);
		}
		repaint();*/
	}
	public void actionPerformed(ActionEvent e) {
//		Timer();
		repaint();
	}
	
	public LinePanel(ArrayList<Target> targets) {
		this.targets = targets;
		setFocusable(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		message = "Hey there. Hi there. Ho there.";
		color = Color.BLACK;
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Target t : targets) {
			int x = t.getX();
			int y = t.getY();
			int d = 2*t.getR();
			int x1 = t.getX1();
			int y1 = t.getY1();
			int d1 = 2*t.getR1();
			int x2 = t.getX2();
			int y2 = t.getY2();
			int d2 = 2*t.getR2();
			
			g.setColor(Color.BLUE);
			g.fillOval(x, y, d, d);
			g.setColor(Color.WHITE);
			g.fillOval(x1, y1, d1, d1);
			g.setColor(Color.RED);
			g.fillOval(x2,y2,d2,d2);
		}
		g.setColor(Color.BLACK);
		g.drawString("Time Remaining: "+getTimeRemaining(),425,700);
		g.drawString("Score: "+getScore(), 425, 675);
	}
}	
class PointRandomizer {
	private Random rnd;
	public void randomize(ArrayList<Target> targets) {
		for (Target t : targets) {
			int moveX = 0 - rnd.nextInt(40) + rnd.nextInt(40);
			int moveY = 0 - rnd.nextInt(40) + rnd.nextInt(40);
			t.setX(t.getX() + moveX);
			t.setY(t.getY() + moveY);
			t.setX1(t.getX1() + moveX);
			t.setY1(t.getY1() + moveY);
			t.setX2(t.getX2() + moveX);
			t.setY2(t.getY2() + moveY);
		}
		
	}
	public PointRandomizer() {
		rnd = new Random();
	}
}
class LineFrame extends JFrame implements ActionListener,KeyListener {	//added various menus and menu items for each function
	private ArrayList<Target> targets;
	private PointRandomizer pr;
	private Timer timer, tim1, tim2, tim3;		//individual timer variables for each animation speed
	private LinePanel lpan;
	private int count=0,timeRemaining;
	

	public Timer getTim3() {
		return tim3;
	}
	public void setTim3(Timer tim3) {
		this.tim3 = tim3;
	}
	public int getScore() {
		return lpan.getScore();
	}
	public void setScore(int score) {
		lpan.setScore(score);
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTimeRemaining() {
		return timeRemaining;
	}
	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		/*if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			lpan.setPointSize(Integer.parseInt(txtPointSize.getText()));
			lpan.repaint();
		}*/
	}
	
	
	
	public void configureMenu() {
		JMenuBar bar = new JMenuBar();
		JMenu mnuFile = new JMenu("File");

		
	
		setJMenuBar(bar);
	
		JMenuItem miOpen = new JMenuItem("Open");
		miOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				String line;
				String[] parts;
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						File f = jfc.getSelectedFile();
						Scanner sc = new Scanner(f);
						targets.clear();
						while (sc.hasNextLine()) {
							line = sc.nextLine().trim();
							parts = line.split(" ");
							targets.add(new 
	Target(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]),Integer.parseInt(parts[3]),Integer.parseInt(parts[4]),
			Integer.parseInt(parts[5]),Integer.parseInt(parts[6]),Integer.parseInt(parts[7]),Integer.parseInt(parts[8])));
						}
						sc.close();
						repaint();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,"Could not open the file.");
					}
				}
			}
		});
		mnuFile.add(miOpen);
		JMenuItem miSave = new JMenuItem("Save");
		miSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						File f = jfc.getSelectedFile();
						PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
						for (Target t : targets) {
							pw.println(t);
						}
						pw.close();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,"Could not save file.");
					}
				}
			}
		});
		mnuFile.add(miSave);
		JMenuItem miExit = new JMenuItem("Exit");
		miExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnuFile.add(miExit);
		bar.add(mnuFile);
		JMenu mnuEdit = new JMenu("Edit");
		JMenuItem miClear = new JMenuItem("Clear");
		miClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				targets.clear();
				repaint();
			}
		});
		mnuEdit.add(miClear);
		bar.add(mnuEdit);
		setJMenuBar(bar);	
		JMenu mnuTimer = new JMenu("Speed");
		JMenuItem miStart = new JMenuItem("Resume");
		miStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tim3.start();
				timer.start();
			}
		});
		JMenuItem miStop = new JMenuItem("Stop");
		miStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tim3.stop();
				timer.stop();
			}
		});
		

		mnuTimer.add(miStart);
		mnuTimer.add(miStop);

		bar.add(mnuTimer);
	}

	public void actionPerformed(ActionEvent e) {
		pr.randomize(targets);
		repaint();
	}
	public void configureUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(50,50,800,800);
		setTitle("Shoot To Score V0.1");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		lpan = new LinePanel(targets);
		c.add(lpan,BorderLayout.CENTER);
		JPanel panSouth = new JPanel();
		panSouth.setLayout(new FlowLayout());
//		JLabel lblPointSize = new JLabel("Time Remaining: ");
//		panSouth.add(lblPointSize);
		c.add(panSouth,BorderLayout.SOUTH);
		
		configureMenu();
	}
	public void Timer() {
		
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCount(getCount()+1);
				
				if (count < 31) {
					setTimeRemaining((30 - getCount()));
					lpan.setTimeRemaining(getTimeRemaining());
					lpan.setScore(getScore());
					lpan.repaint();
				}
				else {
					timer.stop();
					getTim3().stop();
				}
			}
		});
		timer.start();
		}
	
	public LineFrame(ArrayList<Target> targets) {		//creates each timer for each animation speed
		this.targets = targets;
		pr = new PointRandomizer();
		tim1 = new Timer(300,this);
		tim2 = new Timer(150,this);
		setTim3(new Timer(50,this));
		getTim3().start();
		Timer();
		setFocusable(true);
		
		configureUI();
	}
}
@SuppressWarnings("unchecked")

public class ZapotocznyShoot {
	public static void main(String[] args) {
		Random rnd = new Random();
		ArrayList<Target> targets = new ArrayList<Target>();		
		for(int i = 0; i < 150;i++) {
			int x = 50 + rnd.nextInt(500);
			int y = 50 + rnd.nextInt(500);
			int r = 10 + rnd.nextInt(20);
			int x1 = x+5*r/12;
			int y1 = y+5*r/12;
			int r1 = 3*r/5;
			int x2 = x+3*r/4;
			int y2 = y+3*r/4;
			int r2 = r/4;
			targets.add(new Target(x,y,r,x1,y1,r1,x2,y2,r2));
		}try {
			XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream(new File("targets.xml"))));
				enc.writeObject(targets);
				enc.close();
			} catch (Exception ex) {

			}
			System.out.println("Will now read them back in: ");
			ArrayList<Target> readPoints;
			try {
				XMLDecoder dec = new XMLDecoder(new BufferedInputStream(
					new FileInputStream(new File("targets.xml"))));
				readPoints = (ArrayList<Target>)(dec.readObject());
				dec.close();
				for (Target p : readPoints) {
					System.out.println(p);
				}
			} catch (Exception ex) {

			}
		

		
		/*for (Point p : points) {
			System.out.println(p);
		}
		*/
		LineFrame lf = new LineFrame(targets);
		
		lf.setVisible(true);
	}
}




