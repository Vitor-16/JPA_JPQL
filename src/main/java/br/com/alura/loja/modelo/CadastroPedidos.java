package br.com.alura.loja.modelo;

import br.com.alura.loja.utils.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroPedidos {
    public static void main(String[] args) {
        popularBancoDeDados();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO prodDao = new ProdutoDAO(em);
        ClienteDAO clienteDao = new ClienteDAO(em);

        Produto produto = prodDao.buscarPorId(1L);
        Cliente cliente = clienteDao.buscarPorId(1L);

        em.getTransaction().begin();

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));

        PedidoDAO pedidoDao = new PedidoDAO(em);
        pedidoDao.cadastrar(pedido);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("TOTAL VENDIDO: " + totalVendido);

        List<RelatorioDeVendasVO> relatorio = pedidoDao.relatorioVendas();
        relatorio.forEach(System.out::println);
    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Motorola", "Top celular", new BigDecimal(800), celulares);
        Cliente cliente = new Cliente("Vitor", "54854785495");

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);
        ClienteDAO clienteDao = new ClienteDAO(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }
}
