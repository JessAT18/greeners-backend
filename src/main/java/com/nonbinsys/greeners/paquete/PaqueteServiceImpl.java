package com.nonbinsys.greeners.paquete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaqueteServiceImpl implements IPaqueteService {
    @Autowired
    private PaqueteRepository paqueteRepository;

    @Override
    public List<Paquete> listarPaquetes() {
        return paqueteRepository.findAll();
    }

    @Override
    public Optional<Paquete> unPaquete(Long id) {
        return paqueteRepository.findById(id);
    }

    @Override
    public Paquete guardarPaquete(Paquete paquete) {
        return paqueteRepository.save(paquete);
    }

    @Override
    public void eliminarPaquete(Long id) {
        paqueteRepository.deleteById(id);
    }

    @Override
    public List<Paquete> encontrarPaquetesPorComercio(Long id_comercio) {
        return paqueteRepository.encontrarPaquetesPorComercio(id_comercio);
    }
}
