package com.crud.produtos.service;

import com.crud.produtos.dto.ProdutoDto;
import com.crud.produtos.entity.Produto;
import com.crud.produtos.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProdutoServicePost {

    private final static Logger logger = LoggerFactory.getLogger(ProdutoServicePost.class);

    private final ProdutoRepository produtoRepository;
    private final Map<String, Object> cacheMap = new HashMap<>();

    @Autowired
    public ProdutoServicePost(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional //verifica a integridade dos dados do Db(proxy)
    public ResponseEntity criarProduto(ProdutoDto produtoDto) {
        logger.info("Criando produto, valor: " + produtoDto);
        ResponseEntity resposta;

        boolean validado = validarProduto(produtoDto);
        Produto entity = copyEntityForDto(produtoDto);


        if (validado == true) {
            this.produtoRepository.save(entity);
            resposta = new ResponseEntity("produto inserido", HttpStatus.ACCEPTED);
            return resposta;
        }

        logger.warn("Produto invalido, dados " + produtoDto);

        this.cacheMap.put("Produto invalido, verificar log", produtoDto);
        resposta = new ResponseEntity(cacheMap, HttpStatus.NOT_ACCEPTABLE);
        return resposta;
    }

    public ResponseEntity getCache() {
        return new ResponseEntity<>(cacheMap, HttpStatus.OK);
    }

    private boolean validarProduto(ProdutoDto produtoDto) {

        if (produtoDto.getDescricao().isEmpty()) {
            logger.error("Descriacao vazia ");
            return false;
        }
        if (produtoDto.getNome().isEmpty() && produtoDto.getNome() == null) {
            logger.error("Nome vazio ");
            return false;
        }
        if (produtoDto.getValor().isNaN() || produtoDto.getValor() <= 0) {
            logger.error("Valor vazio ");
            return false;
        }

        return true;
    }

    private Produto copyEntityForDto(ProdutoDto produtoDto) {
        logger.info("Copiando dto para entidade, valores: " + produtoDto);
        return new Produto(null, produtoDto.getNome(), produtoDto.getValor(), produtoDto.getDescricao());
    }

    public ResponseEntity deletarPrduto(Integer id) {
        produtoRepository.deleteById(id);
        return new ResponseEntity("produto deletado", HttpStatus.OK);
    }

}
