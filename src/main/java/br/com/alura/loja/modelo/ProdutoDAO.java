package br.com.alura.loja.modelo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDAO {
    private EntityManager em;

    public ProdutoDAO(EntityManager em) { this.em = em;}

    public void cadastrar(Produto prod) { this.em.persist(prod);}

    public void atualizar(Produto prod) { this.em.merge(prod);}

    public void remover(Produto prod) { prod = em.merge(prod); this.em.remove(prod);}

    public Produto buscarPorId(Long id) { return em.find(Produto.class, id);}

    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, Produto.class).setParameter("nome", nome) .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome) {
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
        return em.createQuery(jpql, Produto.class).setParameter("nome", nome) .getResultList();
    }

    public BigDecimal buscarPorNomePrecoProduto(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome) .getSingleResult();
    }
}
