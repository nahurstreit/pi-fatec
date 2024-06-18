/**
 * Package que contém as classes de páginas genéricas do sistema
 */
package view.pages.generics;

import java.awt.Color;

import javax.swing.JScrollPane;

import view.components.forms.FormSection;
import view.components.generics.GenericJPanel;

/**
 * Página genérica para formulários que contém seções configuráveis.
 */
public abstract class GenericFormPage extends GenericPage {
    private static final long serialVersionUID = 1L;
    protected GenericJPanel contentPanel;
    
    /**
     * Construtor para inicializar a página genérica de formulário.
     * 
     * @param ownerPanel O painel proprietário onde esta página será exibida.
     */
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

    /**
     * Método abstrato para construir o formulário específico na subclasse.
     * 
     * @return A própria página de formulário após a construção.
     */
    protected abstract GenericFormPage buildForm();
    
    /**
     * Método para construir e adicionar seções ao formulário.
     * 
     * @param sections Array de seções de formulário para adicionar à página.
     */
    protected void build(FormSection ...sections) {
    	contentPanel.gbc.fill("BOTH").wgt(1.0).anchor("NORTHWEST");
    	for(FormSection section: sections) {
    		contentPanel.add(section, contentPanel.gbc);
    		contentPanel.gbc.yP();
    	}
    }

}
