package graphisme;

package view;

import java.awt.Graphics;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;






public class View extends JPanel implements UpdateEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ObserveurSwing> list;
	//private Model model;

	public View(ArrayList<ObserveurSwing> list) {
		this.list =list;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void manageUpdate() {
		
		SwingUtilities.invokeLater(new Runnable(){
		public void run(){
			
			repaint();
			System.out.println("i repaint");
			}
		});
	}
	
	public void paint(Graphics g){
		this.removeAll();
		super.paint(g);
		//la suite du code va dessiner ce que l'on veut.
		for(ObserveurSwing o:list)
			try {
				o.print(g);
				System.out.println("i call paint on my observers");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		revalidate();
	}
	
	public void add(ObserveurSwing os) {
		list.add(os);
	}
	
	public void remove(ObserveurSwing os){
		list.remove(os);
	}

	public ArrayList<ObserveurSwing> getList() {
		return list;
	}

	public void setList(ArrayList<ObserveurSwing> list) {
		this.list = list;
	}

	
	
}

