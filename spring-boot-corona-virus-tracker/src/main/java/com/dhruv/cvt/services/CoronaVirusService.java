package com.dhruv.cvt.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dhruv.cvt.model.LocData;

public interface CoronaVirusService {
	
	public List<LocData> getAllData();
	
}
