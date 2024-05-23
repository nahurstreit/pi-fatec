	package controller;
	
	import java.awt.Color;
import java.awt.Component;
import java.text.MessageFormat;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import model.entities.Customer;
import view.panels.components.CustomTableModel;
import view.panels.components.GeneralJPanelSettings;
	
	public class InterfaceController {
		public static JScrollPane getCustomerList(List<Customer> originList) {
			// Criação da lista de itens
	        CustomTableModel<Customer> tableModel = new CustomTableModel<>(new Object[]{"Nome", "CPF", "Telefone"}, 0);
			for(Customer customer: originList) {
				tableModel.addObject(customer, 
						new Object[]{
								customer.name, 
								formatCPF(customer.getCPF()), 
								formatPhoneNumber(customer.phoneNumber)
								});
			}
			
			JTable table = new JTable(tableModel);
			table.setRowHeight(20);
			
			JTableHeader tableHeader = table.getTableHeader();
	        tableHeader.setFont(GeneralJPanelSettings.STD_BOLD_FONT.deriveFont(15f));
	        tableHeader.setBackground(GeneralJPanelSettings.STD_BLUE_COLOR);
	        tableHeader.setForeground(Color.white);
			
			// Criação do renderizador de célula diretamente
	        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
				private static final long serialVersionUID = 1L;

				@Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	                cellComponent.setFont(GeneralJPanelSettings.STD_REGULAR_FONT.deriveFont(15f));
	                return cellComponent;
	            }
	        };

	        // Aplicar o renderizador de célula personalizado a todas as colunas
	        for (int i = 0; i < table.getColumnCount(); i++) {
	            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
	        }

	        // Criação do JScrollPane e passagem da tabela
	        JScrollPane scrollPane = new JScrollPane(table);

	        // Adiciona um listener de clique duplo à tabela
	        table.addMouseListener(new java.awt.event.MouseAdapter() {
	            @Override
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                int row = table.rowAtPoint(evt.getPoint());
	                if (row >= 0 && evt.getClickCount() == 2) {
	                    // Obtém o Customer da linha selecionada
	                    Customer selectedCustomer = tableModel.getObjectAt(row);
	                    // Abre o CustomerFrame com o Customer selecionado
	                    CustomerController.openCustomerFrame(selectedCustomer);
	                }
	            }
	        });

	        return scrollPane;
		}
		
		public static JScrollPane getCustomerList() {
			return InterfaceController.getCustomerList(Customer.findAll());
		}
		
		private static String formatCPF(String cpf) {
	        // Formatar o CPF para o formato: 000.000.000-00
		    return MessageFormat.format("{0}.{1}.{2}-{3}", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9));
	    }
		
		private static String formatPhoneNumber(String phoneNumber) {
	        // Formatar o número de telefone
	        if (phoneNumber.length() == 10) {
	            // (00) 0000-0000
	            return MessageFormat.format("({0}) {1}-{2}", phoneNumber.substring(0, 2), phoneNumber.substring(2, 6), phoneNumber.substring(6));
	        } else if (phoneNumber.length() == 11) {
	            // (00) 0 0000-0000
	            return MessageFormat.format("({0}) {1} {2}-{3}", phoneNumber.substring(0, 2), phoneNumber.substring(2, 3), phoneNumber.substring(3, 7), phoneNumber.substring(7));
	        } else {
	            // Retornar o número de telefone sem formatação se não se encaixar nos padrões
	            return phoneNumber;
	        }
	    }
	}