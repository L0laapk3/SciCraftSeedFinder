package com.scicraft.seedfinder;

public class OceanMonumentLocator extends SizedStructureLocator {
	public OceanMonumentLocator() {
		// Ocean, Frozen Ocean, River, Frozen River, Deep Ocean
		super(10387313L, 27, 32, new Integer[]{24}, new Integer[]{0, 10, 7, 11, 24}, 29);
	}
}
