package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Aluno;
import model.bean.ValidaCPF;
import model.dao.AlunoDAO;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewAluno extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCPF;
	private JTextField txtNome;
	private JTextField txtCurso;
	private JTable jTProduto;
	private JTextField txtDataI;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAluno frame = new ViewAluno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewAluno() {
		setTitle("Tabela Aluno");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 806, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if (jTProduto.getSelectedRow() != 1) {

					jTProduto.setValueAt(txtCPF.getText(), jTProduto.getSelectedRow(), 0);
					jTProduto.setValueAt(txtNome.getText(), jTProduto.getSelectedRow(), 1);
					jTProduto.setValueAt(txtCurso.getText(), jTProduto.getSelectedRow(), 2);
					jTProduto.setValueAt(txtDataI.getText(), jTProduto.getSelectedRow(), 3);

				}

			}
		});
		panel.setBounds(10, 11, 770, 161);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Inserir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Aluno a = new Aluno();
				AlunoDAO dao = new AlunoDAO();
				
				String cpfAluno = txtCPF.getText();
				
				 if (ValidaCPF.isCPF(txtCPF.getText()) == true){
					 if (dao.buscarCPF(cpfAluno) != null){
						 JOptionPane.showMessageDialog(null, "CPF já cadastrado!");
						  return;
					 }else{
						 a.setCpf(txtCPF.getText());
					 }
					
				  }else{
					  JOptionPane.showMessageDialog(null, "CPF inválido!");
					  return;
				  }
			
				
				a.setNome(txtNome.getText());
				a.setCurso(txtCurso.getText());
				a.setDataInicio(txtDataI.getText());
				
				dao.create(a);
				
				txtCPF.setText("");
				txtNome.setText("");
				txtCurso.setText("");
				txtDataI.setText("");
				
				readJTable();
			}
		});
		btnNewButton.setBounds(33, 114, 117, 23);
		panel.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("CPF");
		lblNewLabel.setBounds(20, 11, 84, 14);
		panel.add(lblNewLabel);

		JLabel lblQtd = new JLabel("Nome");
		lblQtd.setBounds(162, 11, 46, 14);
		panel.add(lblQtd);

		JLabel lblNewLabel_1 = new JLabel("Curso");
		lblNewLabel_1.setBounds(304, 11, 46, 14);
		panel.add(lblNewLabel_1);

		txtCPF = new JTextField();
		txtCPF.setBounds(10, 36, 130, 20);
		panel.add(txtCPF);
		txtCPF.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(162, 36, 122, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);

		txtCurso = new JTextField();
		txtCurso.setBounds(304, 36, 130, 20);
		panel.add(txtCurso);
		txtCurso.setColumns(10);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (jTProduto.getSelectedRow() != -1) {
					Aluno a = new Aluno();
					AlunoDAO dao = new AlunoDAO();

					a.setCpf(txtCPF.getText());

					dao.delete(a);

					txtCPF.setText("");
					txtNome.setText("");
					txtCurso.setText("");
					txtDataI.setText("");

					readJTable();
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um aluno para excluir");
				}

			}
		});
		btnExcluir.setBounds(206, 114, 89, 23);
		panel.add(btnExcluir);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (jTProduto.getSelectedRow() != -1) {

					Aluno a = new Aluno();
					AlunoDAO dao = new AlunoDAO();

					a.setCpf(txtCPF.getText());
					a.setNome(txtNome.getText());
					a.setCurso(txtCurso.getText());
					a.setDataInicio(txtDataI.getText());

					dao.update(a);

					txtCPF.setText("");
					txtNome.setText("");
					txtCurso.setText("");
					txtDataI.setText("");

					readJTable();

				} else {
					JOptionPane.showMessageDialog(null, "Selecione um aluno para atualizar");
				}

			}
		});
		btnAtualizar.setBounds(364, 114, 117, 23);
		panel.add(btnAtualizar);

		JLabel lblDatai = new JLabel("Data Início");
		lblDatai.setBounds(467, 11, 46, 14);
		panel.add(lblDatai);
		
		txtDataI = new JTextField();
		txtDataI.setToolTipText("Insira no formato dia-mes-ano");
		txtDataI.setBounds(461, 36, 105, 20);
		panel.add(txtDataI);
		txtDataI.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 183, 770, 299);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 770, 299);
		panel_1.add(scrollPane);

		jTProduto = new JTable();
		jTProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtCPF.setText(jTProduto.getValueAt(jTProduto.getSelectedRow(), 0).toString());
				txtNome.setText(jTProduto.getValueAt(jTProduto.getSelectedRow(), 1).toString());
				txtCurso.setText(jTProduto.getValueAt(jTProduto.getSelectedRow(), 2).toString());
				txtDataI.setText(jTProduto.getValueAt(jTProduto.getSelectedRow(), 3).toString());
			}
		});
		scrollPane.setViewportView(jTProduto);
		jTProduto.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "CPF", "Nome", "Curso", "Datai" }) {
			
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, true, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		DefaultTableModel modelo = (DefaultTableModel) jTProduto.getModel();
		jTProduto.setRowSorter(new TableRowSorter<TableModel>(modelo));

		readJTable();
	}

	public void readJTable() {
		DefaultTableModel modelo = (DefaultTableModel) jTProduto.getModel();
		modelo.setNumRows(0);
		AlunoDAO adao = new AlunoDAO();

		for (Aluno a : adao.read()) {

			modelo.addRow(new Object[] { a.getCpf(), a.getNome(), a.getCurso(), a.getDataInicio() });

		}

	}
}
