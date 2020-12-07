package br.gov.sp.fatec.springbootapp.service;

import java.util.List;

import br.gov.sp.fatec.springbootapp.entity.Diretor;

public interface ProdutoraServiceProvider {
    
     //Diretor
    public Diretor criaDiretor(String nome, Long cpf);

    public List<Diretor> buscarTodosDiretores();

    public Diretor buscarDiretorPorId(Long id);

    public Diretor buscarDiretorPorNome(String nome);

    public List<Diretor> buscarDiretorPorLetra(String nome);

    public void deleteDiretor(Long id);

    public Diretor updateDiretor(Long id, String nome, Long cpf);
}