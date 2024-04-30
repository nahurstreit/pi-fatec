package view.panels.pages.components.diet;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import models.Meal;
import view.panels.pages.components.GenericComponent;

public class DietDayPanel extends GenericComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DietMainPanel dietMainPanel;
	public int weekDay;

	public DietDayPanel(Meal[] meals, int weekDay, DietMainPanel dietMainPanel) {
		super();
		this.weekDay = weekDay;
		this.dietMainPanel = dietMainPanel;
		this.setBackground(Color.black);
		this.populate(meals);
	}
	
	protected void initGbc() {
		super.initGbc();
		gbc.gridx = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
	}
	
	public void populate(Meal[] meals) {
		this.removeAll();
		this.initGbc();
		gbc.weighty = 0;
		gbc.insets = new Insets(0, 10, 0, 10);
		if(this.dietMainPanel.currentDayFocus == this.weekDay) {
			JLabel xLbl = new JLabel("x");
			xLbl.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					dropFocus();
				}
				
				//Quando passar o mouse em cima, muda o cursor
				public void mouseEntered(MouseEvent e) {
					xLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				
				//Quando tirar o mouse de cima, volta o cursor ao normal
				public void mouseExited(MouseEvent e) {
					xLbl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	            }
			});
			xLbl.setForeground(Color.white);
			this.add(xLbl, gbc);
		}
		JLabel dayName = new JLabel(this.dietMainPanel.getDayName(this.weekDay), JLabel.CENTER);
		dayName.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				callFocus();
			}
			
			//Quando passar o mouse em cima, muda o cursor
			public void mouseEntered(MouseEvent e) {
				dayName.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			//Quando tirar o mouse de cima, volta o cursor ao normal
			public void mouseExited(MouseEvent e) {
				dayName.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
		});
		dayName.setForeground(Color.white);
		this.add(dayName, gbc);
		this.initGbc();
		for(int i = 0; i < meals.length; i++) {
			gbc.gridy = i + 1;
			if(i == 1) gbc.insets = new Insets(0, 10, 10, 10);
			this.add(new DietMealPanel(meals[i], this), gbc);
		}
		this.refresh();
	}
	
	private void refresh() {
		this.revalidate();
		this.repaint();
	}
	
	public void callFocus() {
		this.dietMainPanel.swapFocus(this.weekDay);
	}
	
	public void dropFocus() {
		this.dietMainPanel.swapFocus(0);
	}
}