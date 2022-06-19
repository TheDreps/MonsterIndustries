package me.lewismercer.monsterIndustries.utils;

import java.util.Random;

import javax.annotation.Nonnull;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class WorldGen extends ChunkGenerator {

	@Override
	@Nonnull
	public ChunkData generateChunkData(@Nonnull World world, @Nonnull Random random, int x, int z,
			@Nonnull BiomeGrid biome) {
		return createChunkData(world);
	}
}