import com.scicraft.seedfinder.*;
import java.util.*;

public class QuadHutMonumentFinder extends QuadHutFinder {
	protected final OceanMonumentLocator monument;
	private final int monumentCloseness = 5;
	private final int monumentMinEdge;
	private final int monumentMaxEdge;

	public QuadHutMonumentFinder() {
		this.monument = new OceanMonumentLocator();
		this.monumentMinEdge = monumentCloseness - 1;
		this.monumentMaxEdge = monument.structurePosRange - monumentCloseness;
	}

	private int fullToRegion(int x, int regionSize) {
		if (x < 0) {
			return (x-8 - regionSize * 16 + 1) / (regionSize * 16);
		} else {
			return (int)(x-8) / 16 / regionSize;
		}
	}

	protected XZPair[] seedPotential(long baseSeed, int radius) {
		XZPair[] hutLocations = super.seedPotential(baseSeed, radius);
		if (hutLocations == null) {
			return null;
		}
		List<XZPair> locations = new ArrayList<XZPair>(Arrays.asList(hutLocations));

		int rX = fullToRegion(hutLocations[0].getX(), monument.structureRegionSize);
		int rZ = fullToRegion(hutLocations[0].getZ(), monument.structureRegionSize);
		XZPair topLeft = monument.structurePosInRegion(rX, rZ, baseSeed);
		XZPair topRight = monument.structurePosInRegion(rX+1, rZ, baseSeed);
		XZPair bottomLeft = monument.structurePosInRegion(rX, rZ+1, baseSeed);
		XZPair bottomRight = monument.structurePosInRegion(rX+1, rZ+1, baseSeed);

		if (topLeft != null && topLeft.getX() >= monumentMaxEdge && topLeft.getZ() >= monumentMaxEdge) {
			locations.add(monument.fullCoordinates(rX, rZ, topLeft));
		}
		if (topRight != null && topRight.getX() <= monumentMinEdge && topRight.getZ() >= monumentMaxEdge) {
			locations.add(monument.fullCoordinates(rX+1, rZ, topRight));
		}
		if (bottomLeft != null && bottomLeft.getX() >= monumentMaxEdge && bottomLeft.getZ() <= monumentMinEdge) {
			locations.add(monument.fullCoordinates(rX, rZ+1, bottomLeft));
		}
		if (bottomRight != null && bottomRight.getX() <= monumentMinEdge && bottomRight.getZ() <= monumentMinEdge) {
			locations.add(monument.fullCoordinates(rX+1, rZ+1, bottomRight));
		}

		if (locations.size() <= 4) {
			return null;
		}
		// TODO: Convert to just use damn lists so we don't to convert
		// constantly.
		return locations.toArray(new XZPair[locations.size()]);
	}

	protected boolean fullSeedWorks(
			long seed, BiomeGenerator generator, int radius,
			XZPair[] chunkLocations, XZPair worldSpawn) {
		if (!checker.quadHutsWillSpawn(hut, chunkLocations, generator)) {
			return false;
		}

		for (int i=4; i<chunkLocations.length; i++) {
			if (monument.structureWillSpawn(chunkLocations[i], generator)) {
				return true;
			}
		}
		return false;
	}

}
