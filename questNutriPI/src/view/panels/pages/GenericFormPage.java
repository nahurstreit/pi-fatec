package view.panels.pages;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import view.components.FormBoxInput;
import view.components.FormSection;
import view.components.StdButton;
import view.panels.components.GeneralJPanelSettings;
import view.panels.components.GenericJPanel;

public abstract class GenericFormPage extends GenericPage {
    private static final long serialVersionUID = 1L;
    protected GenericJPanel contentPanel;

    public GenericFormPage(GenericJPanel ownerPanel) {
        super(ownerPanel);
        ltGridBag();
        setBackground(Color.white);

        //Criação de um JPanel para conter as seções do formulário
        contentPanel = new GenericJPanel().ltGridBag();
        contentPanel.gbc.grid(0);

        // Configuração do JScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, gbc.fill("BOTH").wgt(1.0).grid(0));
    }

    protected abstract GenericFormPage buildForm();
    
    protected void build(FormSection ...sections) {
    	contentPanel.gbc.fill("BOTH").wgt(1.0).anchor("NORTHWEST");
    	for(FormSection section: sections) {
    		contentPanel.add(section, contentPanel.gbc);
    		contentPanel.gbc.yP();
    	}
    }
    
    protected StdButton stdBtnConfig(String text) {
    	return new StdButton(text).setUpFont(GeneralJPanelSettings.STD_BOLD_FONT.deriveFont(12f))
				   .setColors(Color.white, STD_BLUE_COLOR);
    }
    
    protected boolean validateFields(FormBoxInput ...fields) {
    	ArrayList<FormBoxInput> wrongFields = FormBoxInput.validateFields(fields);
    	
    	if(wrongFields.size() > 0) {
        	String errorMsg = "Não foi possível salvar pois existem campos inválidos.\n\n";
        	
        	for(FormBoxInput wrong: wrongFields) {
        		errorMsg += wrong.getErrorText()+"\n";
        	}
        	
        	JOptionPane.showMessageDialog(null, errorMsg);
        	return false;
    	}
    	
    	return true;

    }

}
