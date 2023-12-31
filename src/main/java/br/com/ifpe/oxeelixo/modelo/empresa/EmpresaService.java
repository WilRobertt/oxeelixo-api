package br.com.ifpe.oxeelixo.modelo.empresa;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxeelixo.modelo.acesso.AutenticacaoService;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Transactional
    public Empresa save(Empresa empresa) {

        autenticacaoService.save(empresa.getAutenticacao());

        empresa.setHabilitado(Boolean.TRUE);
        empresa.setVersao(1L);
        empresa.setDataCriacao(LocalDate.now());
        Empresa empresaSalva=repository.save(empresa);

        return empresaSalva;
    }

    public List<Empresa> findAll() {

        return repository.findAll();
    }

    public Empresa findById(Long id) {

        return repository.findById(id).get();
    }

    @Transactional
    public void update(Long id, Empresa empresaAlterada) {

        Empresa empresa = repository.findById(id).get();
        empresa.setNomeEmpresa(empresaAlterada.getNomeEmpresa());
        empresa.setEmail(empresaAlterada.getEmail());
        empresa.setCnpj(empresaAlterada.getCnpj());
        empresa.setDataFundacao(empresaAlterada.getDataFundacao());
        empresa.setSenha(empresaAlterada.getSenha());
        empresa.setSite(empresaAlterada.getSite());

        empresa.setVersao(empresa.getVersao() + 1);
        repository.save(empresa);
    }

    @Transactional
    public void delete(Long id) {

        Empresa empresa = repository.findById(id).get();
        empresa.setHabilitado(Boolean.FALSE);
        empresa.setVersao(empresa.getVersao() + 1);

        repository.save(empresa);
    }

}
