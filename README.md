# SciCraftSeedFinder

Mr L64 started this library after he figured out an new way to find quad-witch
huts and monuments. I added two new parts for finding interesting worlds. L64
also added this video https://www.youtube.com/watch?v=97OdqeiUfHw  explaining
the principle behind the program.

QuadHutFinder: Finds quad witch huts, by L64.

HutandMonumentFinder: The first looks for a quad huts/monuments combination. So
the four is ether huts or monuments. What you most likely are looking for are 3
huts and one monument. But that's up to you. By EgU.

QuadHutMonFinder: This one first looks for a quad witch huts and if the monument
would spawn in x-chunk higher then 16 and z-chunk higher then 16 around top left
hut it will also check where and it the conditions are correct. If you are lucky
it will spawn higher then 24,24 and be in the middle of the quad huts. By EgU.

## To build

There are also a small build script using ant. To choose what job you want to
run change line 49 in build.xml.

## To run

```
    java -jar dist/seedfinder.jar > seeds.txt
```

You can run as many copies as you have cores in your computer, more is
pointless.

## Note on Minecraft versions

Witch hut (and other structure) generation changed in the 1.13 snapshot 18w06a.
There is a "magic" seed for seeding the RNG for structure generation; in
previous versions it 14357617 (and remains that for desert temples), but was
changed to 14357620 for witch huts for 1.13.

A new bug in 1.13, MC-131462, prevents some structures from generated in
negative coordinates. In the case of quad witch huts, some of the huts won't
generate unless the south-east hut has non-negative coordinates for both X and
Z. This makes quad seeds four times as rare, but the seedfinder isn't slower
because it can just search the positive coordinates.

## Disclaimer

I'm not a java developer but I know how you should write java code. So if you
are looking for nice well formed java-code. Please run away!! It you are looking
for a program that finds cool seeds, this is the place to be, and if you do know
how to write java code please do so and I will accept the pull request :)
