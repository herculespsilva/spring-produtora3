package br.gov.sp.fatec.springbootapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.springbootapp.entity.Diretor;
import br.gov.sp.fatec.springbootapp.exception.RegistroNaoEncontradoException;
import br.gov.sp.fatec.springbootapp.repository.DiretorRepository;

@Service("ProdutoraServiceProvider") 
public class ProdutoraServiceProviderImpl implements ProdutoraServiceProvider {

    @Autowired
    private DiretorRepository diretorRepo;

    @PreAuthorize("hasAnyRole('ADMIN, DIRETOR')")
    public Diretor criaDiretor(String nome, Long cpf) {
        Diretor diretor = new Diretor();
        diretor.setNome(nome);
        diretor.setCpf(cpf);
        diretorRepo.save(diretor);
        return diretor;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @Override
    public List<Diretor> buscarTodosDiretores(){
        return diretorRepo.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    @Override
    public Diretor buscarDiretorPorId(Long id)
    {
        Optional<Diretor> diretorOp= diretorRepo.findById(id);
        if(diretorOp.isPresent())
        {
            return diretorOp.get();
        }
         throw new RegistroNaoEncontradoException("diretor nao encontrado!");
    }
    
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Diretor buscarDiretorPorNome(String nome)
    {
        Diretor diretor = diretorRepo.findByNome(nome);
        if(diretor!=null)
        {
            return diretor;
        }
        throw new RegistroNaoEncontradoException("diretor nao encontrado!");
    }

    
    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Diretor> buscarDiretorPorLetra(String nome){
         List<Diretor> diretor = diretorRepo.findByNomeContainsIgnoreCase(nome);
        if(diretor!=null)
        {
            return diretor;
        }
        throw new RegistroNaoEncontradoException("diretor nao encontrado!");
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDiretor(Long id){
        diretorRepo.deleteById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USUARIO')")
    public Diretor updateDiretor(Long id, String nome, Long cpf){
        Optional<Diretor> oldDiretor = diretorRepo.findById(id);

        if(oldDiretor.isPresent()){
            Diretor diretor = oldDiretor.get();
            diretor.setNome(nome);
            diretor.setCpf(cpf);
            diretorRepo.save(diretor);

            return diretor;
        }
        throw new RegistroNaoEncontradoException("diretor nao encontrado!");
    }
}