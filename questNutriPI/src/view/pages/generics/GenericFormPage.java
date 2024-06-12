package view.pages.generics;

import java.awt.Color;

import javax.swing.JScrollPane;

import view.components.forms.FormSection;
import view.components.generics.GenericJPanel;

public abstract class GenericFormPage extends GenericPage {
    private static final long serialVersionUID = 1L;
    protected GenericJPanel contentPanel;
    
    public GenericFormPage(GenericJPanel ownerPanel) {
    	super(ownerPanel);
        ltGridBag();
        setBackground(Color.white);

        //Criação de um JPanel para conter as seções do formulário
        contentPanel = new GenericJPanel(this).ltGridBag();
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

}
