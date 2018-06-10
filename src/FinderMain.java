import java.lang.*;
import java.util.Random;
import com.scicraft.seedfinder.*;
import com.scicraft.seedfinder.finder.*;

public class FinderMain {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("ERROR: Must specify a finder name.");
			System.out.println("Available finders:");
			System.out.println("	QuadHut");
			System.out.println("	QuadHutMon");
			System.out.println("	HutAndMonument");
			System.exit(1);
		}

		long startSeed;
		if (args.length >= 2) {
			startSeed = Long.parseLong(args[1]);
		} else {
			System.out.println("No start seed specified, picking randomly...");
			Random rnd = new Random();
			startSeed = ((long)rnd.nextInt(1<<16) << 32) + rnd.nextInt();
		}

		int radius;
		if (args.length >= 3) {
			radius = Integer.parseInt(args[2]);
		} else {
			System.out.println("No radius specified, using default...");
			radius = 4;
		}

		SeedFinder finder;
		switch (args[0]) {
			case "HutAndMonument":
				finder = new HutandMonumentFinder();
				break;
			case "QuadHut":
				finder = new QuadHutFinder();
				break;
			case "QuadHutMon":
				finder = new HutandMonumentFinder();
				break;
			default:
				System.out.println("Invalid finder name specified.");
				finder = new QuadHutFinder();  // Hack
				System.exit(1);
		}
		System.out.println("Finder: " + args[0]);
		System.out.println("Start seed: " + startSeed + ", Radius: " + radius);
		finder.findSeeds(startSeed, radius);
	}
}
//
//FinderMain.java
//HutandMonumentFinder.java
//QuadHutFinder.java
//QuadHutMonFinder.java
