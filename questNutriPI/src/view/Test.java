package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import controller.CustomerController;
import model.entities.Customer;

public class Test extends JFrame {

	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerController.openCustomerFrame(Customer.findByPK(1));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
