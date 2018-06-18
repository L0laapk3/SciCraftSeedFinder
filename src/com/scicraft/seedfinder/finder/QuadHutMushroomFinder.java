import com.scicraft.seedfinder.*;
import java.util.*;

public class QuadHutMushroomFinder extends QuadHutFinder {
	private static final float MUSHROOM_FRACTION = 0.02f;
	private static final Biome[] MUSHROOM_BIOMES = {
		Biome.mushroomIsland,
		Biome.mushroomIslandShore,
		Biome.mushroomIslandM,
		Biome.mushroomIslandShoreM,
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
		return checker.hasBiomes(
				generator, radius*2*32*16, worldSpawn.getX(), worldSpawn.getZ(),
				MUSHROOM_BIOMES, MUSHROOM_FRACTION);
	}
}
