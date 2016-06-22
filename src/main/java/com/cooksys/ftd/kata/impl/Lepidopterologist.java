package com.cooksys.ftd.kata.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import com.cooksys.ftd.kata.ILepidopterologist;
import com.cooksys.ftd.kata.model.GrowthModel;
import com.cooksys.ftd.kata.model.Sample;
import com.cooksys.ftd.kata.model.Species;

public class Lepidopterologist implements ILepidopterologist {

	private Set<Species> sList = new HashSet<Species>();
	private Map<Species, List<Sample>> dMap = new TreeMap<Species, List<Sample>>();
	@Override
	public boolean registerSpecies(Species species) {
		// TODO Auto-generated method stub
		if((!isSpeciesRegistered(species))){
			sList.add(species);
		
			return true;
		}
		
		return false;
	}

	@Override
	public boolean isSpeciesRegistered(Species species) {
		// TODO Auto-generated method stub
		if((sList.contains(species))){
		
			return true;
		}
		
		return false;
	}
	

	@Override
	public Optional<Species> findSpeciesForSample(Sample sample) {
		
		for(Species s : sList){
			if(s.isMember(sample.getGrowthModel())){
				return Optional.of(s);
			}
		}
		
		
		return Optional.empty();
		// TODO Auto-generated method stub
	
	}
	@Override
	public boolean recordSample(Sample sample) {
		Optional<Species> x = findSpeciesForSample(sample);
		List<Sample> y;
		Species s;
		if(x.isPresent()){
			s = x.get();
			if(!dMap.containsKey(s)){
				y = new ArrayList<Sample>();
				y.add(sample);
				dMap.put(s, y);
				
			}
			else {
				y = dMap.get(s);
				y.add(sample);
				dMap.put(s, y);
				
			}
			return true;
		}
		return false;
		
	}

	@Override
	public List<Sample> getSamplesForSpecies(Species species) {
		// TODO Auto-generated method stub
		return dMap.get(species);
		
	}

	@Override
	public List<Species> getRegisteredSpecies() {
		// TODO Auto-generated method stub
		return new ArrayList<Species> (sList);
	}

	@Override
	public Map<Species, List<Sample>> getTaxonomy() {
		// TODO Auto-generated method stub
		List<Sample> y;
		for(Species s : sList){
			y = dMap.get(s);
			Collections.sort(y);
		}
		return dMap;
	}

}
