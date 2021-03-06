package br.com.jave.fachada;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.jave.dao.GenericDao;
import br.com.jave.dao.UfDaoImpl;
import br.com.jave.excecoes.ExclusaoNaoPermitidaException;
import br.com.jave.modelo.Uf;

public class UfFachadaImpl implements Fachada<Uf>{

	GenericDao<Uf> UfDao = new UfDaoImpl();
	
	public void gravar(Uf entidade) throws Exception {
		this.UfDao.gravar(entidade);
	}

	@Override
	public void excluir(Uf uf) throws Exception , ExclusaoNaoPermitidaException{
		UfDao.excluir(uf);
	}

	@Override
	public List<Uf> listarTodos() throws Exception {
		return UfDao.listarTodos();
	}

	@Override
	public Uf pesquisarPorId(Long id) throws NoResultException, Exception {
		return UfDao.pesquisarPorId(id);
	}

	
}
