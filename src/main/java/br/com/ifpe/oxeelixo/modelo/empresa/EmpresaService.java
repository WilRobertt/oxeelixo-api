package br.com.ifpe.oxeelixo.modelo.empresa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    @Transactional
    public Empresa save(Empresa empresa) {

        empresa.setHabilitado(Boolean.TRUE);
        empresa.setVersao(1L);
        empresa.setDataCriacao(LocalDate.now());
        return repository.save(empresa);
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
