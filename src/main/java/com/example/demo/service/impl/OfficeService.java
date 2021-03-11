package com.example.demo.service.impl;

import com.example.demo.model.Office;
import com.example.demo.repository.OfficeRepository;
import com.example.demo.repository.PositionRepository;
import com.example.demo.service.IOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OfficeService implements IOfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Override
    public Iterable<Office> findAll() {
        return officeRepository.findAll();
    }

    @Override
    public Optional<Office> findById(Long id) {
        return officeRepository.findById(id);
    }

    @Override
    public Office save(Office office) {
        return officeRepository.save(office);
    }

    @Override
    public void remove(Long id) {
        officeRepository.deleteById(id);
    }
}
