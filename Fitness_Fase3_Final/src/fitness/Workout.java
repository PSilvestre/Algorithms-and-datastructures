/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness;

import java.io.Serializable;

public class Workout implements Activity, Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private int MET;
	
	public Workout(String actId, int MET, String name){
		this.id = actId;
		this.MET = MET;
		this.name = name;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getMET() {
		return MET;
	}
}
