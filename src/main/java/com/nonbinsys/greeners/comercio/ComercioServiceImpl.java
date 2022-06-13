package com.nonbinsys.greeners.comercio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercioServiceImpl implements IComercioService {
    @Autowired
    private ComercioRepository comercioRepository;

    @Override
    public List<Comercio> listarComercios() {
        return comercioRepository.findAll();
    }

    @Override
    public Optional<Comercio> unComercio(Long id) {
        return comercioRepository.findById(id);
    }

    @Override
    public Comercio guardarComercio(Comercio comercio) {
        return comercioRepository.save(comercio);
    }

    @Override
    public void eliminarComercio(Long id) {
        comercioRepository.deleteById(id);
    }

    @Override
    public List<Comercio> encontrarComerciosPorNombre(String nombreComercio) {
        return comercioRepository.encontrarComerciosPorNombre(nombreComercio);
    }
}
