package io.github.dougllasfps.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.h2.command.dml.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.dougllasfps.domain.entity.Produto;
import io.github.dougllasfps.domain.repository.Produtos;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private Produtos produtos;
	
	@PutMapping("{id}")
	public void Update(@PathVariable Integer id, 
							@RequestBody Produto produto) {
		 produtos.findById(id)
		.map(produtoExistente -> {
			produto.setId(produtoExistente.getId());
			produtos.save(produto);
			return produtoExistente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Produto save(@RequestBody Produto produto) {
		return produtos.save(produto);
		
	}
	
	@GetMapping
	public List<Produto> find(Produto filtro){
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		List<Produto> lista = produtos.findAll();
		return produtos.findAll();
	}
	
	@GetMapping("{id}")
	public Produto findProduto(@PathVariable Integer id){
		return produtos.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        produtos
                .findById(id)
                .map( produto -> {
                    produtos.delete(produto);
                    return Void.TYPE;
                }).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado."));
    }
	
}
