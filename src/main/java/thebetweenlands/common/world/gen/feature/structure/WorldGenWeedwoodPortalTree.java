package thebetweenlands.common.world.gen.feature.structure;

import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import thebetweenlands.common.block.structure.BlockTreePortal;
import thebetweenlands.common.registries.BlockRegistry;

public class WorldGenWeedwoodPortalTree extends WorldGenerator {

	private IBlockState bark;
	private IBlockState wood;
	private IBlockState leaves;
	private IBlockState portal;

    @Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int radius = 4;
		int height = 16;
		int maxRadius = 9;

		this.bark = BlockRegistry.LOG_PORTAL.getDefaultState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.NONE);
		this.wood = BlockRegistry.LOG_WEEDWOOD.getDefaultState();
		this.leaves = BlockRegistry.LEAVES_WEEDWOOD_TREE.getDefaultState();
		this.portal = BlockRegistry.TREE_PORTAL.getDefaultState();

		for (int xx = - maxRadius; xx <= maxRadius; xx++)
			for (int zz = - maxRadius; zz <= maxRadius; zz++)
				for (int yy = 2; yy < height; yy++)
					if (!world.isAirBlock(pos.add(xx, yy, zz)) && world.getBlockState(pos.add(xx, yy, zz)).isNormalCube()) {
						//if(TheBetweenlands.proxy.getClientPlayer() != null)
						//	TheBetweenlands.proxy.getClientPlayer().addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("talisman.noplace")));
						return false;
					}

		createMainCanopy(world, rand, pos.add(0, height/2 + 4, 0), maxRadius);

		for (int yy = 0; yy < height; ++yy) {
			if (yy % 3 == 0 && radius > 1 && yy > 5)
				--radius;

			for (int i = radius * -1; i <= radius; ++i)
				for (int j = radius * -1; j <= radius; ++j) {
					double dSq = i * i + j * j;
					if (Math.round(Math.sqrt(dSq)) < radius && yy <= 1)
						world.setBlockState(pos.add(i, yy, j), wood, 2);
					if (Math.round(Math.sqrt(dSq)) == radius && yy == 0 || Math.round(Math.sqrt(dSq)) == radius && yy <= height - 1)
						world.setBlockState(pos.add(i, yy, j), bark, 2);
				}

			if(yy == 4) {
				BlockTreePortal.makePortalX(world, pos.add(radius, yy - 2, 0));
				BlockTreePortal.makePortalX(world, pos.add(- radius, yy - 2, 0));
				BlockTreePortal.makePortalZ(world, pos.add(0, yy - 2, radius));
				BlockTreePortal.makePortalZ(world, pos.add(0, yy - 2, - radius));
			}

			if (yy == height/2 + 2) {
				createBranch(world, rand, pos.add(radius + 1, yy - rand.nextInt(2), 0), 1, false, rand.nextInt(2) + 4);
				createBranch(world, rand, pos.add(- radius - 1, yy - rand.nextInt(2), 0), 2, false, rand.nextInt(2) + 4);
				createBranch(world, rand, pos.add(0, yy - rand.nextInt(2), radius + 1), 3, false, rand.nextInt(2) + 4);
				createBranch(world, rand, pos.add(0, yy - rand.nextInt(2), - radius - 1), 4, false, rand.nextInt(2) + 4);

				createBranch(world, rand, pos.add(radius + 1, yy - rand.nextInt(2), radius + 1), 5, false, rand.nextInt(2) + 3);
				createBranch(world, rand, pos.add(- radius - 1, yy - rand.nextInt(2), - radius - 1), 6, false, rand.nextInt(2) + 3);
				createBranch(world, rand, pos.add(- radius - 1, yy - rand.nextInt(2), radius + 1), 7, false, rand.nextInt(2) + 3);
				createBranch(world, rand, pos.add(radius + 1, yy - rand.nextInt(2), - radius - 1), 8, false, rand.nextInt(2) + 3);
			}

			if (yy == height/2 + 4) {
				createSmallBranch(world, rand, pos.add(radius + 1, yy - rand.nextInt(2), 0), 1, 4);
				createSmallBranch(world, rand, pos.add(- radius - 1, yy - rand.nextInt(2), 0), 2, 4);
				createSmallBranch(world, rand, pos.add(0, yy - rand.nextInt(2), radius + 1), 3, 4);
				createSmallBranch(world, rand, pos.add(0, yy - rand.nextInt(2), - radius - 1), 4, 4);

				createSmallBranch(world, rand, pos.add(radius + 1, yy - rand.nextInt(2), radius + 1), 5, 3);
				createSmallBranch(world, rand, pos.add(- radius - 1, yy - rand.nextInt(2), - radius - 1), 6, 3);
				createSmallBranch(world, rand, pos.add(- radius - 1, yy - rand.nextInt(2), radius + 1), 7, 3);
				createSmallBranch(world, rand, pos.add(radius + 1, yy - rand.nextInt(2), - radius - 1), 8, 3);
			}

			if (yy == height/2 + 7) {
				createSmallBranch(world, rand, pos.add(radius + 1, yy - rand.nextInt(2), 0), 1, 2);
				createSmallBranch(world, rand, pos.add(- radius - 1, yy - rand.nextInt(2), 0), 2, 2);
				createSmallBranch(world, rand, pos.add(0, yy - rand.nextInt(3), radius + 1), 3, 2);
				createSmallBranch(world, rand, pos.add(0, yy - rand.nextInt(3), - radius - 1), 4, 2);

				createSmallBranch(world, rand, pos.add(radius + 1, yy - rand.nextInt(2), radius + 1), 5, 2);
				createSmallBranch(world, rand, pos.add(- radius - 1, yy - rand.nextInt(2), - radius - 1), 6, 2);
				createSmallBranch(world, rand, pos.add(- radius - 1, yy - rand.nextInt(2), radius + 1), 7, 2);
				createSmallBranch(world, rand, pos.add(radius + 1, yy - rand.nextInt(2), - radius - 1), 8, 2);
			}

			if (yy == 0) {
				createBranch(world, rand, pos.add(radius + 1, yy - rand.nextInt(2), 0), 1, true, rand.nextInt(2) + 3);
				createBranch(world, rand, pos.add(- radius - 1, yy - rand.nextInt(2), 0), 2, true, rand.nextInt(2) + 3);
				createBranch(world, rand, pos.add(0, yy - rand.nextInt(2), radius + 1), 3, true, rand.nextInt(2) + 3);
				createBranch(world, rand, pos.add(0, yy - rand.nextInt(2), - radius - 1), 4, true, rand.nextInt(2) + 3);

				createBranch(world, rand, pos.add(radius + 1, yy - rand.nextInt(2), radius + 1), 5, true, rand.nextInt(2) + 3);
				createBranch(world, rand, pos.add(- radius - 1, yy - rand.nextInt(2), - radius - 1), 6, true, rand.nextInt(2) + 3);
				createBranch(world, rand, pos.add(- radius - 1, yy - rand.nextInt(2), radius + 1), 7, true, rand.nextInt(2) + 3);
				createBranch(world, rand, pos.add(radius + 1, yy - rand.nextInt(2), - radius - 1), 8, true, rand.nextInt(2) + 3);
			}
		}
		return true;
	}

	private void createSmallBranch(World world, Random rand, BlockPos pos, int dir, int branchLength) {
		int y = 0;
		for (int i = 0; i <= branchLength; ++i) {

			if (i >= 2)
				y++;

			switch (dir) {
			case 1:
				world.setBlockState(pos.east(i).up(y), bark);
				break;

			case 2:
				world.setBlockState(pos.west(i).up(y), bark);
				break;

			case 3:
				world.setBlockState(pos.south(i).up(y), bark);
				break;

			case 4:
				world.setBlockState(pos.north(i).up(y), bark);
				break;

			case 5:
				world.setBlockState(pos.east(i).up(y).south(i), bark);
				break;

			case 6:
				world.setBlockState(pos.west(i).up(y).north(i), bark);
				break;

			case 7:
				world.setBlockState(pos.west(i).up(y).south(i), bark);
				break;

			case 8:
				world.setBlockState(pos.east(i).up(y).north(i), bark);
				break;
			}
		}
	}

	private void createMainCanopy(World world, Random rand, BlockPos pos, int maxRadius) {
		for (int x1 = - maxRadius; x1 <= maxRadius; x1++)
			for (int z1 = - maxRadius; z1 <= maxRadius; z1++)
				for (int y1 = 0; y1 < maxRadius; y1++) {
					double dSq = Math.pow(x1, 2.0D) + Math.pow(z1, 2.0D) + Math.pow(y1, 2.5D);
					if (Math.round(Math.sqrt(dSq)) <= maxRadius)
						if (world.getBlockState(pos.add(x1, y1, z1)) != bark && rand.nextInt(5) != 0)
							world.setBlockState(pos.add(x1, y1, z1), leaves);
					if (Math.round(Math.sqrt(dSq)) < maxRadius - 1 && rand.nextInt(5) == 0 && y1 > 0)
						if (world.getBlockState(pos.add(x1, y1, z1)) != bark)
							world.setBlockState(pos.add(x1, y1, z1), bark);
					if (Math.round(Math.sqrt(dSq)) <= maxRadius && rand.nextInt(3) == 0 && y1 == 0)
						if (world.getBlockState(pos.add(x1, y1, z1)) != bark)
							for (int i = 1; i < 1 + rand.nextInt(3); i++)
								world.setBlockState(pos.add(x1, y1 - i, z1), leaves);
				}
	}

	private void createBranch(World world, Random rand, BlockPos pos, int dir, boolean root, int branchLength) {
		int y = 0;
		for (int i = 0; i <= branchLength; ++i) {

			if (i >= 3)
				y++;

			switch (dir) {
			case 1:
				if (!root)
					world.setBlockState(pos.east(i).up(y), bark);
				else {
					world.setBlockState(pos.east(i).down(y), bark);
					world.setBlockState(pos.east(i).down(y - 1), bark);
				}
				break;

			case 2:
				if (!root)
					world.setBlockState(pos.west(i).up(y), bark);
				else {
					world.setBlockState(pos.west(i).down(y), bark);
					world.setBlockState(pos.west(i).down(y - 1), bark);
				}
				break;

			case 3:
				if (!root)
					world.setBlockState(pos.south(i).up(y), bark);
				else {
					world.setBlockState(pos.south(i).down(y), bark);
					world.setBlockState(pos.south(i).down(y - 1), bark);
				}
				break;

			case 4:
				if (!root)
					world.setBlockState(pos.north(i).up(y), bark);
				else {
					world.setBlockState(pos.north(i).down(y), bark);
					world.setBlockState(pos.north(i).down(y - 1), bark);
				}
				break;

			case 5:
				if (!root)
					world.setBlockState(pos.east(i - 1).up(y).south(i - 1),
							bark);
				else {
					world.setBlockState(pos.east(i - 1).down(y).south(i - 1), bark);
					world.setBlockState(pos.east(i - 1).down(y - 1).south(i - 1), bark);
				}
				break;

			case 6:
				if (!root)
					world.setBlockState(pos.west(i - 1).up(y).north(i - 1), bark);
				else {
					world.setBlockState(pos.west(i - 1).down(y).north(i - 1), bark);
					world.setBlockState(pos.west(i - 1).down(y - 1).north(i - 1), bark);
				}
				break;

			case 7:
				if (!root)
					world.setBlockState(pos.west(i - 1).up(y).south(i - 1), bark);
				else {
					world.setBlockState(pos.west(i - 1).down(y).south(i - 1), bark);
					world.setBlockState(pos.west(i - 1).down(y - 1).south(i - 1), bark);
				}
				break;

			case 8:
				if (!root)
					world.setBlockState(pos.east(i - 1).up(y).north(i - 1), bark);
				else {
					world.setBlockState(pos.east(i - 1).down(y).north(i - 1), bark);
					world.setBlockState(pos.east(i - 1).down(y - 1).north(i - 1), bark);
				}
				break;
			}
		}
	}

}
