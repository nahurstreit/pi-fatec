package view.panels.pages.components.diet;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import models.Meal;
import view.panels.pages.components.GenericComponent;
import view.utils.VUtils;

public class DietDayPanel extends GenericComponent {
	private static final long serialVersionUID = 1L;
	
	
	public DietMainPanel dietMainPanel;
	public int weekDay;
	
	private final Color TEXT_COLOR = Color.white;
	private final Color BG_COLOR = new Color(85, 183, 254);
	@SuppressWarnings("unused")
	private final Font NORMAL_FONT = VUtils.loadFont("Montserrat-Regular", 15f);
	private final Font STRONG_FONT = VUtils.loadFont("Montserrat-Bold", 18f);

	public DietDayPanel(Meal[] meals, int weekDay, DietMainPanel dietMainPanel) {
		super();
		this.weekDay = weekDay;
		this.dietMainPanel = dietMainPanel;
		this.setBackground(this.BG_COLOR);
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
			JLabel lblX = new JLabel("x");
			lblX.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					dropFocus();
				}
				
				//Quando passar o mouse em cima, muda o cursor
				public void mouseEntered(MouseEvent e) {
					lblX.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				
				//Quando tirar o mouse de cima, volta o cursor ao normal
				public void mouseExited(MouseEvent e) {
					lblX.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	            }
			});
			lblX.setForeground(this.TEXT_COLOR);
			lblX.setFont(STRONG_FONT);
			this.add(lblX, gbc);
		}
		
		JLabel lblDayName = new JLabel(this.dietMainPanel.getDayName(this.weekDay), JLabel.CENTER);
		lblDayName.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				callFocus();
			}
			
			//Quando passar o mouse em cima, muda o cursor
			public void mouseEntered(MouseEvent e) {
				lblDayName.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			//Quando tirar o mouse de cima, volta o cursor ao normal
			public void mouseExited(MouseEvent e) {
				lblDayName.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
		});
		lblDayName.setForeground(this.TEXT_COLOR);
		lblDayName.setFont(STRONG_FONT);
		this.add(lblDayName, gbc);
		
		
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