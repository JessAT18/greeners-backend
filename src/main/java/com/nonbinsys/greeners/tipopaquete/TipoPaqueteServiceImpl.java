package com.nonbinsys.greeners.tipopaquete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoPaqueteServiceImpl implements ITipoPaqueteService{
    @Autowired
    private TipoPaqueteRepository tipoPaqueteRepository;

    @Override
    public List<TipoPaquete> listarTiposPaquete() {
        return tipoPaqueteRepository.findAll();
    }

    @Override
    public Optional<TipoPaquete> unTipoPaquete(Long id) {
        return tipoPaqueteRepository.findById(id);
    }
}
