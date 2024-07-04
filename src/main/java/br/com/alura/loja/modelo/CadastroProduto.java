package br.com.alura.loja.modelo;

import br.com.alura.loja.utils.JPAUtil;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroProduto {
    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);

        Produto p = produtoDao.buscarPorId(1L);
        List<Produto> todosProd = produtoDao.buscarTodos();
        todosProd.forEach(p2 -> System.out.println(p.getNome()));

        BigDecimal precoProduto = produtoDao.buscarPorNomePrecoProduto("Motorola");
        System.out.println("Preço Produto: " + precoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Motorola", "Top celular", new BigDecimal(800), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);

        em.getTransaction().begin();
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        em.getTransaction().commit();
        em.flush();
        em.clear();

        celulares = em.merge(celulares);
        celulares.setNome("Iphone");
        em.flush();
        em.remove(celulares);
        em.flush();
    }
}
