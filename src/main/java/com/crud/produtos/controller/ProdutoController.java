package com.crud.produtos.controller;


import com.crud.produtos.dto.ProdutoDto;
import com.crud.produtos.entity.Produto;
import com.crud.produtos.service.ProdutoServicePost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = "/produtos")
@RestController
public class ProdutoController {

    private final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    private final ProdutoServicePost service;

    public ProdutoController(ProdutoServicePost service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody ProdutoDto produtoDto) {
        logger.info("Produto inserido, valor: " + produtoDto);
        ResponseEntity resposta = service.criarProduto(produtoDto);
        return resposta;
    }

    @GetMapping("/cache")
    public ResponseEntity getCache(){
        logger.info("Consultando cache");
        ResponseEntity response = this.service.getCache();
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPrduto(@PathVariable("id") Integer id){
        logger.info("Produto inserido, valor: " + id);
        ResponseEntity resposta = service.deletarPrduto(id);
        return resposta;

    }
}
