package br.com.jave.teste;

import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.java.modelo.Email;
import br.com.java.modelo.Endereco;
import br.com.java.modelo.Pessoa;
import br.com.java.modelo.Telefone;
import br.com.java.modelo.Uf;
import br.com.jave.dao.GenericDao;
import br.com.jave.dao.PessoaDaoImpl;
import br.com.jave.dao.UfDaoImpl;
import br.com.jave.enums.Sexo;

public class TesteManterPessoa {
	
	//BasicConfigurator.configure();
	private Pessoa pessoa = new Pessoa();
	private List<Telefone> telefones = new ArrayList<Telefone>();
	private List<Email> emails = new ArrayList<Email>();
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	private GenericDao<Uf> uf = new UfDaoImpl();
	private GenericDao<Pessoa> pessoaDao = new PessoaDaoImpl();


	
	public static void main(String[] args) throws NoResultException, Exception {
		
		TesteManterPessoa teste = new TesteManterPessoa();
		
		try{
			teste.salvarPessoa();
			teste.listarTodos();
			
			
		}catch(NoResultException e){
			System.out.println(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public void salvarPessoa() throws NoResultException, Exception{
		
		//-------- Telefones ----------------------------
		Telefone tel1 = new Telefone();
		tel1.setCodigoDeArea("085");
		tel1.setNumero("87297857");
		tel1.setPessoa(pessoa);
		
		Telefone tel2 = new Telefone();
		tel2.setCodigoDeArea("088");
		tel2.setNumero("88110036");
		tel2.setPessoa(pessoa);
		
		telefones.add(tel1);
		telefones.add(tel2);
		//-----------------------------------------------
		
		//-------- Email ----------------------------
		Email email1 = new Email();
		email1.setEmail("adejanny.feitosa@gmail.com");

		Email email2 = new Email();
		email2.setEmail("adejanny.quixeramobim@gmail.com");

		emails.add(email1);
		emails.add(email2);
		//-----------------------------------------------
		
		//-------- Pessoa ----------------------------

		RandomAccessFile arquivoEntrada = new RandomAccessFile("/home/desenv/livre/carro.jpg", "r");
		byte[] arquivoFoto = new byte[(int)arquivoEntrada.length()];
		arquivoEntrada.read(arquivoFoto);
		arquivoEntrada.close();
		
		pessoa.setCnpj("02852963000109");
		pessoa.setContatos(telefones);
		pessoa.setCpf("00434926302");
		pessoa.setDataCadastro(null);
		pessoa.setEmails(emails);
		pessoa.setEnderecos(enderecos);
		pessoa.setNome("Paulo Gomes");
		pessoa.setSexo(Sexo.FEMINO);
		pessoa.setFoto(arquivoFoto);
		//-----------------------------------------------
		
		Endereco endereco1 = new Endereco();
		endereco1.setRua("Rua São Jose");
		endereco1.setNumero(567);
		endereco1.setBairro("Colonia");
		endereco1.setCep("60333-745");
		endereco1.setCidade("Fortaleza");
		endereco1.setUf(uf.pesquisarPorId((long)1));
		endereco1.setPontoDeReferencia("Perto da Praia dos Arpoadores");
		endereco1.setComplemento("Apartamento 10000");
		enderecos.add(endereco1);		
		
		pessoaDao.gravar(pessoa);
		
		byte[] byteFoto = pessoaDao.pesquisarPorId((long)1).getFoto();
		
		FileOutputStream fileOuputStream = new FileOutputStream("/home/desenv/livre/carroBanco.jpg");
		fileOuputStream.write(byteFoto);
		fileOuputStream.close();
	}
	
	public void listarTodos() throws Exception{
		for(Pessoa pessoas : pessoaDao.listarTodos()){
			System.out.println(pessoas.getNome());
		}		
	}

}