package stijgmachine.jti1a1.nl.objects.phys2d;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.forcesource.ForceSource;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

public class World {
	
	private net.phys2d.raw.World world;

	public World(Vector2f vector2f, int i, QuadSpaceStrategy quadSpaceStrategy) {
		// TODO Auto-generated constructor stub
		world = new net.phys2d.raw.World(vector2f, i, quadSpaceStrategy);
	}

	public void add(Body cirkel) {
		// TODO Auto-generated method stub
		world.add(cirkel.getBody());
		
	}

	public void step() {
		world.step();
		// TODO Auto-generated method stub
		
	}

	public void add(StaticBody line1) {
		world.add(line1.getBody());
		// TODO Auto-generated method stub
		
	}

	public void remove(Body body) {
		world.remove(body.getBody());
		// TODO Auto-generated method stub
		
	}

}
