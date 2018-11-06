import com.scicraft.seedfinder.*;
import java.util.*;

public class QuadHutExoticBiomesFinder extends QuadHutFinder {
	public QuadHutExoticBiomesFinder(long startSeed, int radius, int threadNumber, int threadCount) {
		super(startSeed, radius, threadNumber, threadCount);
	};

	private static final Biome[] EXOTIC_BIOMES = {
		Biome.mushroomIsland,
		Biome.megaTaiga,
		Biome.mesa,
		Biome.flowerForest,
		Biome.icePlainsSpikes,
	};

	protected boolean fullSeedWorks(
			long seed, BiomeGenerator generator, int radius,
			XZPair[] chunkLocations, XZPair worldSpawn) {
		if (!checker.quadHutsWillSpawn(hut, chunkLocations, generator)) {
			return false;
		}
		if (!checker.hasOceanSpawn(generator, radius, worldSpawn)) {
			return false;
		}

		Hashtable<Integer, Float> census = generator.biomeCensus(
				worldSpawn.getX(), worldSpawn.getZ(), radius*2*32*16, radius*2*32*16, true);
		for (Biome biome : EXOTIC_BIOMES) {
			if (census.get(biome.index) < 0.00025) {  // 0.025%, or about 4 chunks
				return false;
			}
		}
		return true;
	}
}
