package com.hta.vue.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hta.vue.domain.DTO;
import com.hta.vue.mapper.VueMapper;

@Service
public class ServiceImple implements VueService{
	private VueMapper mapper;
	
	@Autowired
	public ServiceImple(VueMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public List<DTO> getList(int page, int limit){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int startrow=(page-1)*limit+1;
		int endrow=startrow+limit-1;
		map.put("start", startrow);
		map.put("end", endrow); 
		return mapper.getList(map);
	}

	@Override
	public int getListCount() {
		return mapper.getListCount();
	}

	@Override
	public int insert(DTO dto) {
		return mapper.insert(dto);
	}

	@Override
	public void update(DTO dto) {
		mapper.update(dto);
		
	}

	@Override
	public void delete(int id) {
		mapper.delete(id);
		
	}
}
