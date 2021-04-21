package com.jrosselloj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrosselloj.model.Bolsa;
import com.jrosselloj.repository.IBolsaRepo;

@Service
public class BolsaServiceImpl implements IBolsaService {
	
	@Autowired
	private IBolsaRepo bolsaRepo;
	
	
	@Override
	public List<Bolsa> findAll() {
		return bolsaRepo.findAll();
	}
	
	
	@Override
	public void saveBolsa(Bolsa bolsa) {
		bolsaRepo.save(bolsa);
		
	}
	
	
	@Override
	public Bolsa findById(Integer id) {
		Optional<Bolsa> bolsa = bolsaRepo.findById(id);
		
		return bolsa.isPresent() ? bolsa.get() : null;
		
	}
	
}
