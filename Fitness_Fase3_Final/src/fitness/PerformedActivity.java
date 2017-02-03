/**
 * @author Pedro Silvestre <48540> pm.silvestre@fct.unl.pt
 */
package fitness;

public interface PerformedActivity extends Activity{
	/**
	 * Gets the duration of this Workout in hours.
	 * @return the duration of the Workout
	 */
	int getDuration();
	
	/**
	 * Returns the amount of calories burned in this Workout.
	 * @return the amount of calories burned in this Workout
	 */
	int getCalories();
	
}
