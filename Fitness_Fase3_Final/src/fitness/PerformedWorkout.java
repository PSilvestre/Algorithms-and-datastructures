/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness;

import java.io.Serializable;

public class PerformedWorkout implements PerformedActivity, Serializable{
	
	private static final long serialVersionUID = 1L;
	private Activity activity;
	private int duration;
	
	private int caloriesBurned;
	
	public PerformedWorkout(Activity activity, int duration, int cal) {
		this.activity = activity;
		this.duration = duration;
		this.caloriesBurned = cal;
	}

	@Override
	public String getId() {
		return activity.getId();
	}

	@Override
	public String getName() {
		return activity.getName();
	}

	@Override
	public int getMET() {
		return activity.getMET();
	}

	@Override
	public int getDuration() {
		return duration;
	}
	
	public int getCalories(){
		return caloriesBurned;
	}
}
