import java.lang.Math;
import com.scicraft.seedfinder.*;

public class QuadHutMultiMansionFinder extends QuadHutOceanSpawnFinder {
	private final WoodlandMansionLocator mansion;

	public QuadHutMultiMansionFinder() {
		mansion = new WoodlandMansionLocator();
	}

	protected boolean fullSeedWorks(
			long seed, BiomeGenerator generator, int radius,
			XZPair[] chunkLocations, XZPair worldSpawn) {
		if (!super.fullSeedWorks(seed, generator, radius, chunkLocations, worldSpawn)) {
			return false;
		}
		int mansionRadius = (int)Math.ceil((float)radius*32f / 80f);

		int mansionCount = 0;
		for (int regionX=-mansionRadius; regionX<mansionRadius; regionX++) {
			for (int regionZ=-mansionRadius; regionZ<mansionRadius; regionZ++) {
				if (mansion.structureWillSpawn(regionX, regionZ, seed, generator)) {
					mansionCount++;
				}
			}
		}

		return mansionCount > 1;
	}
}
