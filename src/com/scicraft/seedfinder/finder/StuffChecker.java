import com.scicraft.seedfinder.*;
import java.util.*;

public class StuffChecker {
	private static final int SPAWN_SIZE = 256;
	private static final int SPAWN_AREA = 256*256;

	private static final float WATER_FRACTION = 0.80f;
	private static final Biome[] WATER_BIOMES = {
		Biome.ocean,
		Biome.deepOcean,
		Biome.frozenOcean,
		Biome.oceanM,
		Biome.deepOceanM,
		Biome.frozenOceanM,
	};

	public boolean quadHutsWillSpawn(
			WitchHutLocator hut, XZPair[] chunkLocations, BiomeGenerator generator) {
		// Only iterate through first four structures so that subclasses can
		// have additional ones more easily.
		for (int i=0; i<4; i++) {
			XZPair location = chunkLocations[i];
			if (!hut.structureWillSpawn(location, generator)) {
				return false;
			}
		}
		return true;
	}

	public boolean hasOceanSpawn(
			BiomeGenerator generator, int radius, XZPair worldSpawn) {
		Hashtable<Integer, Float> census = generator.biomeCensus(
				worldSpawn.getX(), worldSpawn.getZ(), SPAWN_SIZE, SPAWN_SIZE, true);
		float waterFraction = 0f;
		for (Biome biome : WATER_BIOMES) {
			waterFraction += census.get(biome.index);
		}
		return waterFraction >= WATER_FRACTION;
	}

	public int mansionCount(
			 WoodlandMansionLocator mansion, long seed, BiomeGenerator generator, int witchHutRadius) {
		int mansionRadius = (int)Math.ceil((float)witchHutRadius*32f / 80f);

		int mansionCount = 0;
		for (int regionX=-mansionRadius; regionX<mansionRadius; regionX++) {
			for (int regionZ=-mansionRadius; regionZ<mansionRadius; regionZ++) {
				if (mansion.structureWillSpawn(regionX, regionZ, seed, generator)) {
					mansionCount++;
				}
			}
		}

		return mansionCount;
	}

}
