/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package listaenlazadaframe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Menu {
    private final JFrame frame;
    private ListaEnlazada lista;
    private final JTextField textFieldInput;
    private final JTextArea textAreaOutput;
    private final JComboBox<String> typeComboBox;

    public Menu() {
        // Crear el JFrame
        frame = new JFrame("Gestor de Lista Enlazada");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10)); // Espacio entre los bordes

        // Panel superior para seleccionar tipo de lista
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(64, 128, 128));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 

        JLabel labelTipo = new JLabel("Selecciona el tipo de lista que deseas crear:");
        labelTipo.setForeground(Color.WHITE); 
        topPanel.add(labelTipo);

        typeComboBox = new JComboBox<>(new String[]{"entero", "string", "caracter"});
        topPanel.add(typeComboBox);

        JButton createButton = new JButton("Crear Lista");
        createButton.addActionListener((ActionEvent e) -> {
            crearLista();
        });
        topPanel.add(createButton);
        frame.add(topPanel, BorderLayout.NORTH);

        // Panel de operaciones
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(224, 255, 255));
        centerPanel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 filas y 2 columnas

        centerPanel.add(new JLabel("Inserta los Datos:"));
        textFieldInput = new JTextField();
        centerPanel.add(textFieldInput);

        JButton insertButton = new JButton("Insertar");
        insertButton.setBackground(new Color(173, 216, 230));
        insertButton.addActionListener((ActionEvent e) -> {
            insertarElemento();
        });
        centerPanel.add(insertButton);

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.setBackground(new Color(240, 128, 128));
        deleteButton.addActionListener((ActionEvent e) -> {
            eliminarElemento();
        });
        centerPanel.add(deleteButton);

        JButton modifyButton = new JButton("Modificar");
        modifyButton.setBackground(new Color(144, 238, 144));
        modifyButton.addActionListener((ActionEvent e) -> {
            modificarElemento();
        });
        centerPanel.add(modifyButton);

        JButton showButton = new JButton("Mostrar");
        showButton.setBackground(new Color(255, 228, 181));
        showButton.addActionListener((ActionEvent e) -> {
            mostrarLista();
        });
        centerPanel.add(showButton);

        JButton clearButton = new JButton("Limpiar");
        clearButton.setBackground(new Color(255, 192, 203)); 
        clearButton.addActionListener((ActionEvent e) -> {
            limpiar();
        });
        centerPanel.add(clearButton);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Panel inferior para mostrar resultados
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(245, 245, 245));
        textAreaOutput = new JTextArea(6, 40);
        textAreaOutput.setLineWrap(true); // Ajustar líneas largas
        textAreaOutput.setWrapStyleWord(true); // Ajuste de palabras completas
        textAreaOutput.setEditable(false);
        bottomPanel.add(new JScrollPane(textAreaOutput));
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Pa' que se vea el jFrame
        frame.setVisible(true);
    }

    private void crearLista() {
        String tipo = (String) typeComboBox.getSelectedItem();
        lista = new ListaEnlazada(tipo);
        textAreaOutput.setText("Haz creado una lista de tipo " + tipo );
    }

    private void insertarElemento() {
        if (lista == null) {
            textAreaOutput.setText("Debes crear una lista primero.");
            return;
        }
        String tipo = (String) typeComboBox.getSelectedItem();
        String input = textFieldInput.getText();
        Object dato = convertirDato(input, tipo);
        if (dato != null) {
            lista.insertar(dato);
            textAreaOutput.setText("Se agregó el elemento: " + dato);
        } else {
            textAreaOutput.setText("Error: tipo de dato no válido.");
        }
    }

    private void eliminarElemento() {
        if (lista == null) {
            textAreaOutput.setText("Crea una lista primero.");
            return;
        }
        String tipo = (String) typeComboBox.getSelectedItem();
        String input = textFieldInput.getText();
        Object dato = convertirDato(input, tipo);
        if (dato != null) {
            lista.eliminar(dato);
            textAreaOutput.setText("Elemento eliminado: " + dato);
        } else {
            textAreaOutput.setText("Error: tipo de dato no válido.");
        }
    }

    private void modificarElemento() {
        if (lista == null) {
            textAreaOutput.setText("Crea una lista primero.");
            return;
        }
        String tipo = (String) typeComboBox.getSelectedItem();
        String[] inputs = textFieldInput.getText().split(",");
        if (inputs.length != 2) {
            textAreaOutput.setText("Error: Introduce un dato viejo y nuevo separados por coma.");
            return;
        }
        Object datoViejo = convertirDato(inputs[0].trim(), tipo);
        Object datoNuevo = convertirDato(inputs[1].trim(), tipo);
        if (datoViejo != null && datoNuevo != null) {
            lista.modificar(datoViejo, datoNuevo);
            textAreaOutput.setText("El elemento ha sido modificado de " + datoViejo + " a " + datoNuevo);
        } else {
            textAreaOutput.setText("Error: tipo de dato no válido.");
        }
    }

    private void mostrarLista() {
        if (lista == null) {
            textAreaOutput.setText("Crea una lista primero.");
            return;
        }
        textAreaOutput.setText("Lista: " + lista.mostrar());
    }

    private void limpiar() {
        textFieldInput.setText("");
        textAreaOutput.setText("");
    }

    private Object convertirDato(String input, String tipo) {
        try {
            switch (tipo) {
                case "entero" -> {
                    return Integer.valueOf(input);
                }
                case "string" -> {
                    return input;
                }
                case "caracter" -> {
                    if (input.length() == 1) {
                        return input.charAt(0);
                    }
                }
            }
        } catch (NumberFormatException e) {

        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Menu::new);
    }
}
